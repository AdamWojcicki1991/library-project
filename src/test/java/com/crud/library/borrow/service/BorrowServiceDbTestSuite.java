package com.crud.library.borrow.service;

import com.crud.library.book.BookStatus;
import com.crud.library.book.domain.Book;
import com.crud.library.book.service.BookServiceDb;
import com.crud.library.borrow.domain.Borrow;
import com.crud.library.reader.domain.Reader;
import com.crud.library.reader.service.ReaderServiceDb;
import com.crud.library.title.domain.Title;
import com.crud.library.title.service.TitleServiceDb;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BorrowServiceDbTestSuite {
    @Autowired
    private TitleServiceDb titleServiceDb;
    @Autowired
    private BookServiceDb bookServiceDb;
    @Autowired
    private ReaderServiceDb readerServiceDb;
    @Autowired
    private BorrowServiceDb borrowServiceDb;

    @Test
    public void testSaveBorrow() {
        //GIVEN
        Title title = Title.builder()
                .title("Title")
                .author("Author")
                .publishedYear(LocalDate.now())
                .build();
        long titleId = titleServiceDb.saveTitle(title).getId();
        Book book = Book.builder()
                .title(title)
                .bookStatus(BookStatus.IN_LIBRARY)
                .build();
        bookServiceDb.saveBook(book);
        Reader reader = Reader.builder()
                .name("Name")
                .surname("Surname")
                .createAccountDate(new Date())
                .build();
        long readerId = readerServiceDb.saveReader(reader).getId();
        Borrow borrow = Borrow.builder()
                .borrowDate(LocalDate.now())
                .returnDate(LocalDate.now())
                .book(book)
                .reader(reader)
                .build();
        //WHEN
        long borrowSavedInDatabaseId = borrowServiceDb.saveBorrow(borrow).getId();
        //THEN
        assertNotEquals(0, borrowSavedInDatabaseId);
        //CLEANUP
        borrowServiceDb.deleteBorrowById(borrowSavedInDatabaseId);
        titleServiceDb.deleteTitleById(titleId);
        readerServiceDb.deleteReaderById(readerId);
    }

    @Test
    public void testGetBorrowById() {
        //GIVEN
        Title title = Title.builder()
                .title("Title")
                .author("Author")
                .publishedYear(LocalDate.now())
                .build();
        long titleId = titleServiceDb.saveTitle(title).getId();
        Book book = Book.builder()
                .title(title)
                .bookStatus(BookStatus.IN_LIBRARY)
                .build();
        bookServiceDb.saveBook(book);
        Reader reader = Reader.builder()
                .name("Name")
                .surname("Surname")
                .createAccountDate(new Date())
                .build();
        long readerId = readerServiceDb.saveReader(reader).getId();
        Borrow borrow = Borrow.builder()
                .borrowDate(LocalDate.now())
                .returnDate(LocalDate.now())
                .book(book)
                .reader(reader)
                .build();
        long borrowSavedInDatabaseId = borrowServiceDb.saveBorrow(borrow).getId();
        //WHEN
        long borrowFromDatabaseId = borrowServiceDb.getBorrowById(borrowSavedInDatabaseId).get().getId();
        //THEN
        assertNotEquals(0, borrowFromDatabaseId);
        //CLEANUP
        borrowServiceDb.deleteBorrowById(borrowSavedInDatabaseId);
        titleServiceDb.deleteTitleById(titleId);
        readerServiceDb.deleteReaderById(readerId);
    }

    @Test
    public void getAllBorrows() {
        //GIVEN
        Title title = Title.builder()
                .title("Title")
                .author("Author")
                .publishedYear(LocalDate.now())
                .build();
        long titleId = titleServiceDb.saveTitle(title).getId();
        Book book = Book.builder()
                .title(title)
                .bookStatus(BookStatus.IN_LIBRARY)
                .build();
        bookServiceDb.saveBook(book);
        Reader reader = Reader.builder()
                .name("Name")
                .surname("Surname")
                .createAccountDate(new Date())
                .build();
        long readerId = readerServiceDb.saveReader(reader).getId();
        Borrow borrow = Borrow.builder()
                .borrowDate(LocalDate.now())
                .returnDate(LocalDate.now())
                .book(book)
                .reader(reader)
                .build();
        long borrowSavedInDatabaseId = borrowServiceDb.saveBorrow(borrow).getId();
        //WHEN
        List<Borrow> borrows = borrowServiceDb.getAllBorrows();
        //THEN
        assertEquals(1, borrows.size());
        //CLEANUP
        borrowServiceDb.deleteBorrowById(borrowSavedInDatabaseId);
        titleServiceDb.deleteTitleById(titleId);
        readerServiceDb.deleteReaderById(readerId);
    }

    @Test
    public void deleteBorrowById() {
        //GIVEN
        Title title = Title.builder()
                .title("Title")
                .author("Author")
                .publishedYear(LocalDate.now())
                .build();
        long titleId = titleServiceDb.saveTitle(title).getId();
        Book book = Book.builder()
                .title(title)
                .bookStatus(BookStatus.IN_LIBRARY)
                .build();
        bookServiceDb.saveBook(book);
        Reader reader = Reader.builder()
                .name("Name")
                .surname("Surname")
                .createAccountDate(new Date())
                .build();
        long readerId = readerServiceDb.saveReader(reader).getId();
        Borrow borrow = Borrow.builder()
                .borrowDate(LocalDate.now())
                .returnDate(LocalDate.now())
                .book(book)
                .reader(reader)
                .build();
        long borrowSavedInDatabaseId = borrowServiceDb.saveBorrow(borrow).getId();
        //WHEN
        borrowServiceDb.deleteBorrowById(borrowSavedInDatabaseId);
        //THEN
        assertFalse(borrowServiceDb.getBorrowById(borrowSavedInDatabaseId).isPresent());
        //CLEANUP
        titleServiceDb.deleteTitleById(titleId);
        readerServiceDb.deleteReaderById(readerId);
    }
}
