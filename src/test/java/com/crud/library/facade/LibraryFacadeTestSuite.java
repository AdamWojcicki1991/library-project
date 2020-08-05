package com.crud.library.facade;

import com.crud.library.book.domain.Book;
import com.crud.library.book.service.BookServiceDb;
import com.crud.library.borrow.controller.ProcessCanNotBeExecutedException;
import com.crud.library.borrow.domain.Borrow;
import com.crud.library.borrow.service.BorrowServiceDb;
import com.crud.library.fixture.DataFixture;
import com.crud.library.reader.domain.Reader;
import com.crud.library.reader.service.ReaderServiceDb;
import com.crud.library.title.domain.Title;
import com.crud.library.title.service.TitleServiceDb;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static com.crud.library.book.BookStatus.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryFacadeTestSuite {
    private DataFixture dataFixture;
    @Autowired
    private ReaderServiceDb readerServiceDb;
    @Autowired
    private BookServiceDb bookServiceDb;
    @Autowired
    private BorrowServiceDb borrowServiceDb;
    @Autowired
    private TitleServiceDb titleServiceDb;
    @Autowired
    private LibraryFacade libraryFacade;

    @Before
    public void setUp() {
        dataFixture = new DataFixture();
    }

    @After
    public void tearDown() {
        //CLEANUP
        borrowServiceDb.deleteAllBorrows();
        bookServiceDb.deleteAllBooks();
        titleServiceDb.deleteAllTitles();
        readerServiceDb.deleteAllReaders();
    }

    @Test
    public void shouldFindAvailableBooks() {
        //GIVEN
        Title title = dataFixture.getTitle();
        titleServiceDb.saveTitle(title);
        bookServiceDb.saveBook(dataFixture.getBook(title, IN_LIBRARY));
        bookServiceDb.saveBook(dataFixture.getBook(title, IN_CIRCULATION));
        bookServiceDb.saveBook(dataFixture.getBook(title, LOST));
        //WHEN
        List<Book> books = libraryFacade.findAvailableBooks("Title");
        //THEN
        assertEquals(1, books.size());
        assertEquals(IN_LIBRARY, books.get(0).getBookStatus());
    }

    @Test
    public void shouldFindAllBorrowedBookByReader() {
        //GIVEN
        Title title = dataFixture.getTitle();
        Book firstBook = dataFixture.getBook(title, IN_LIBRARY);
        Book secondBook = dataFixture.getBook(title, IN_CIRCULATION);
        Book thirdBook = dataFixture.getBook(title, LOST);
        Reader reader = dataFixture.getReader();
        titleServiceDb.saveTitle(title);
        bookServiceDb.saveBook(firstBook);
        bookServiceDb.saveBook(secondBook);
        bookServiceDb.saveBook(thirdBook);
        long readerId = readerServiceDb.saveReader(reader).getId();
        borrowServiceDb.saveBorrow(dataFixture.getBorrow(firstBook, reader));
        borrowServiceDb.saveBorrow(dataFixture.getBorrow(secondBook, reader));
        borrowServiceDb.saveBorrow(dataFixture.getBorrow(thirdBook, reader));
        //WHEN
        List<Borrow> borrowsByReader = libraryFacade.findBorrowedBookByReader(readerId);
        //THEN
        assertEquals(2, borrowsByReader.size());
        assertEquals(IN_CIRCULATION, borrowsByReader.get(0).getBook().getBookStatus());
        assertEquals(LOST, borrowsByReader.get(1).getBook().getBookStatus());
    }

    @Test
    public void shouldExecuteBorrow() {
        //GIVEN
        Title title = dataFixture.getTitle();
        Book firstBook = dataFixture.getBook(title, IN_LIBRARY);
        Book secondBook = dataFixture.getBook(title, IN_CIRCULATION);
        Book thirdBook = dataFixture.getBook(title, LOST);
        Reader reader = dataFixture.getReader();
        titleServiceDb.saveTitle(title);
        long readerId = readerServiceDb.saveReader(reader).getId();
        long firstBookId = bookServiceDb.saveBook(firstBook).getId();
        bookServiceDb.saveBook(secondBook);
        bookServiceDb.saveBook(thirdBook);
        Book bookInDatabaseBeforeBorrow = bookServiceDb.getBookById(firstBookId).get();
        List<Borrow> borrowsBeforeExecuteBorrow = borrowServiceDb.getAllBorrows();
        //WHEN
        try {
            libraryFacade.executeBorrow("Title", readerId);
        } catch (ProcessCanNotBeExecutedException e) {
            e.printStackTrace();
        }
        Book bookInDatabaseAfterBorrow = bookServiceDb.getBookById(firstBookId).get();
        List<Borrow> borrowsAfterExecuteBorrow = borrowServiceDb.getAllBorrows();
        //THEN
        assertEquals(0, borrowsBeforeExecuteBorrow.size());
        assertEquals(IN_LIBRARY, bookInDatabaseBeforeBorrow.getBookStatus());
        assertEquals(1, borrowsAfterExecuteBorrow.size());
        assertEquals(IN_CIRCULATION, bookInDatabaseAfterBorrow.getBookStatus());
        assertEquals(IN_CIRCULATION, borrowsAfterExecuteBorrow.get(0).getBook().getBookStatus());
        assertEquals(LocalDate.now(), borrowsAfterExecuteBorrow.get(0).getBorrowDate());
        assertEquals(LocalDate.now().plusMonths(1), borrowsAfterExecuteBorrow.get(0).getReturnDate());
    }

    @Test
    public void shouldExecuteReturn() {
        //GIVEN
        Title title = dataFixture.getTitle();
        Book book = dataFixture.getBook(title, IN_CIRCULATION);
        Reader reader = dataFixture.getReader();
        Borrow borrow = dataFixture.getBorrow(book, reader);
        titleServiceDb.saveTitle(title);
        long bookId = bookServiceDb.saveBook(book).getId();
        long readerId = readerServiceDb.saveReader(reader).getId();
        borrowServiceDb.saveBorrow(borrow);
        List<Borrow> borrowsBeforeExecuteReturn = borrowServiceDb.getAllBorrows();
        //WHEN
        try {
            libraryFacade.executeReturn(bookId, readerId);
        } catch (ProcessCanNotBeExecutedException e) {
            e.printStackTrace();
        }
        List<Borrow> borrowsAfterExecuteReturn = borrowServiceDb.getAllBorrows();
        //THEN
        assertEquals(1, borrowsBeforeExecuteReturn.size());
        assertEquals(IN_CIRCULATION, borrowsBeforeExecuteReturn.get(0).getBook().getBookStatus());
        assertEquals(1, borrowsAfterExecuteReturn.size());
        assertEquals(IN_LIBRARY, borrowsAfterExecuteReturn.get(0).getBook().getBookStatus());
        assertEquals(borrow.getId(), borrowsAfterExecuteReturn.get(0).getId());
        assertEquals(LocalDate.now().minusMonths(1), borrowsAfterExecuteReturn.get(0).getBorrowDate());
        assertEquals(LocalDate.now(), borrowsAfterExecuteReturn.get(0).getReturnDate());
    }
}
