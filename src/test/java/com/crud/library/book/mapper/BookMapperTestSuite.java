package com.crud.library.book.mapper;

import com.crud.library.book.BookStatus;
import com.crud.library.book.domain.Book;
import com.crud.library.book.domain.BookDto;
import com.crud.library.fixture.DataFixture;
import com.crud.library.title.domain.Title;
import com.crud.library.title.domain.TitleDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static com.crud.library.book.BookStatus.IN_LIBRARY;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookMapperTestSuite {
    private DataFixture dataFixture;
    @Autowired
    private BookMapper bookMapper;

    @Before
    public void setUp() {
        dataFixture = new DataFixture();
    }

    @Test
    public void shouldMapToBook() {
        //GIVEN
        BookDto bookDto = dataFixture.createBookDto(dataFixture.createTitleDto(), IN_LIBRARY);
        long bookDtoId = bookDto.getId();
        TitleDto titleDto = bookDto.getTitle();
        BookStatus bookStatusDto = bookDto.getBookStatus();
        //WHEN
        Book book = bookMapper.mapToBook(bookDto);
        long bookIdAfterMapping = book.getId();
        Title titleAfterMapping = book.getTitle();
        BookStatus bookStatusAfterMapping = book.getBookStatus();
        //THEN
        assertEquals(bookDtoId, bookIdAfterMapping);
        assertEquals(titleDto.getId(), titleAfterMapping.getId());
        assertEquals(titleDto.getAuthor(), titleAfterMapping.getAuthor());
        assertEquals(titleDto.getTitle(), titleAfterMapping.getTitle());
        assertEquals(titleDto.getPublishedYear(), titleAfterMapping.getPublishedYear());
        assertEquals(bookStatusDto, bookStatusAfterMapping);
    }

    @Test
    public void shouldMapToBookDto() {
        //GIVEN
        Book book = dataFixture.createBook(dataFixture.createTitle(), IN_LIBRARY);
        Long bookId = book.getId();
        Title title = book.getTitle();
        BookStatus bookStatus = book.getBookStatus();
        //WHEN
        BookDto bookDto = bookMapper.mapToBookDto(book);
        Long bookDtoIdAfterMapping = bookDto.getId();
        TitleDto titleDtoAfterMapping = bookDto.getTitle();
        BookStatus bookStatusDtoAfterMapping = bookDto.getBookStatus();
        //THEN
        assertEquals(bookId, bookDtoIdAfterMapping);
        assertEquals(title.getId(), titleDtoAfterMapping.getId());
        assertEquals(title.getAuthor(), titleDtoAfterMapping.getAuthor());
        assertEquals(title.getTitle(), titleDtoAfterMapping.getTitle());
        assertEquals(title.getPublishedYear(), titleDtoAfterMapping.getPublishedYear());
        assertEquals(bookStatus, bookStatusDtoAfterMapping);
    }

    @Test
    public void shouldMapToBooksDto() {
        //GIVEN
        Book book = dataFixture.createBook(dataFixture.createTitle(), IN_LIBRARY);
        List<Book> books = new ArrayList<>();
        books.add(book);
        //WHEN
        List<BookDto> booksDto = bookMapper.mapToBooksDto(books);
        //THEN
        assertEquals(1, booksDto.size());
    }
}
