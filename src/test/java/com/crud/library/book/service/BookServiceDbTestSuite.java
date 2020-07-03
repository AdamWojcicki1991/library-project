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

import static com.crud.library.book.BookStatus.IN_LIBRARY;
import static org.junit.Assert.assertNotEquals;

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
        titleServiceDb.saveTitle(title);
        long id = title.getId();
        Book book = Book.builder()
                .title(title)
                .bookStatus(IN_LIBRARY)
                .build();
        //WHEN
        bookServiceDb.saveBook(book);
        //THEN
        assertNotEquals(0, id);
        //CLEANUP
        titleServiceDb.deleteTitleById(id);
    }

    @Test
    public void testGetBookById() {
        //GIVEN

        //WHEN

        //THEN

        //CLEANUP
    }

    @Test
    public void getAllBooks() {
        //GIVEN

        //WHEN

        //THEN

        //CLEANUP
    }

    @Test
    public void deleteBookById() {
        //GIVEN

        //WHEN

        //THEN

        //CLEANUP
    }
}
