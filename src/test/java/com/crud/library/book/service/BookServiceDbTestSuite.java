package com.crud.library.book.service;

import com.crud.library.book.domain.Book;
import com.crud.library.title.domain.Title;
import com.crud.library.title.service.TitleServiceDb;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static com.crud.library.book.BookStatus.IN_LIBRARY;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceDbTestSuite {
    @Autowired
    private BookServiceDb bookServiceDb;
    @Autowired
    private TitleServiceDb titleServiceDb;

    @Test
    public void testSaveBook() {
        //GIVEN
        Title title = Title.builder()
                .title("Title")
                .author("Author")
                .publishedYear(LocalDate.now())
                .build();
        long titleId = titleServiceDb.saveTitle(title).getId();
        Book book = Book.builder()
                .title(title)
                .bookStatus(IN_LIBRARY)
                .build();
        //WHEN
        long bookSavedInDatabaseId = bookServiceDb.saveBook(book).getId();
        //THEN
        assertNotEquals(0, bookSavedInDatabaseId);
        //CLEANUP
        titleServiceDb.deleteTitleById(titleId);
    }

    @Test
    public void testGetBookById() {
        //GIVEN
        Title title = Title.builder()
                .title("Title")
                .author("Author")
                .publishedYear(LocalDate.now())
                .build();
        long titleId = titleServiceDb.saveTitle(title).getId();
        Book book = Book.builder()
                .title(title)
                .bookStatus(IN_LIBRARY)
                .build();
        long bookId = bookServiceDb.saveBook(book).getId();
        //WHEN
        long bookFromDatabaseId = bookServiceDb.getBookById(bookId).get().getId();
        //THEN
        assertNotEquals(0, bookFromDatabaseId);
        //CLEANUP
        titleServiceDb.deleteTitleById(titleId);
    }

    @Test
    public void getAllBooks() {
        //GIVEN
        Title title = Title.builder()
                .title("Title")
                .author("Author")
                .publishedYear(LocalDate.now())
                .build();
        long titleId = titleServiceDb.saveTitle(title).getId();
        Book book = Book.builder()
                .title(title)
                .bookStatus(IN_LIBRARY)
                .build();
        bookServiceDb.saveBook(book);
        //WHEN
        List<Book> books = bookServiceDb.getAllBooks();
        //THEN
        assertEquals(1, books.size());
        //CLEANUP
        titleServiceDb.deleteTitleById(titleId);
    }

    @Test
    public void deleteBookById() {
        //GIVEN
        Title title = Title.builder()
                .title("Title")
                .author("Author")
                .publishedYear(LocalDate.now())
                .build();
        long titleId = titleServiceDb.saveTitle(title).getId();
        Book book = Book.builder()
                .title(title)
                .bookStatus(IN_LIBRARY)
                .build();
        long bookId = bookServiceDb.saveBook(book).getId();
        //WHEN
        bookServiceDb.deleteBookById(bookId);
        //THEN
        assertFalse(bookServiceDb.getBookById(bookId).isPresent());
        //CLEANUP
        titleServiceDb.deleteTitleById(titleId);
    }
}
