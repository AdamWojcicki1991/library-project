package com.crud.library.book.mapper;

import com.crud.library.book.BookStatus;
import com.crud.library.book.domain.Book;
import com.crud.library.book.domain.BookDto;
import com.crud.library.title.domain.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookMapperTestSuite {
    @Autowired
    private BookMapper bookMapper;

    @Test
    public void testMapToBook() {
        //GIVEN
        Title title = Title.builder()
                .id(1L)
                .title("Title")
                .author("Author")
                .publishedYear(LocalDate.now())
                .build();
        BookDto bookDto = BookDto.builder()
                .id(1L)
                .title(title)
                .bookStatus(BookStatus.IN_LIBRARY)
                .build();
        long bookDtoId = bookDto.getId();
        Title titleDto = bookDto.getTitle();
        BookStatus bookStatusDto = bookDto.getBookStatus();
        //WHEN
        Book book = bookMapper.mapToBook(bookDto);
        long bookId = book.getId();
        Title titleFromMapper = book.getTitle();
        BookStatus bookStatus = book.getBookStatus();
        //THEN
        assertEquals(bookDtoId, bookId);
        assertEquals(titleDto, titleFromMapper);
        assertEquals(bookStatusDto, bookStatus);
    }

    @Test
    public void testMapToBookDto() {
        //GIVEN
        Title title = Title.builder()
                .id(1L)
                .title("Title")
                .author("Author")
                .publishedYear(LocalDate.now())
                .build();
        Book book = Book.builder()
                .id(1L)
                .title(title)
                .bookStatus(BookStatus.IN_LIBRARY)
                .build();
        long bookId = book.getId();
        Title bookTitleFromEntity = book.getTitle();
        BookStatus bookStatus = book.getBookStatus();
        //WHEN
        BookDto bookDto = bookMapper.mapToBookDto(book);
        long bookDtoId = bookDto.getId();
        Title bookTitleDto = bookDto.getTitle();
        BookStatus bookStatusDto = bookDto.getBookStatus();
        //THEN
        assertEquals(bookId, bookDtoId);
        assertEquals(bookTitleFromEntity, bookTitleDto);
        assertEquals(bookStatus, bookStatusDto);
    }

    @Test
    public void testMapToBooksDto() {
        //GIVEN
        Title title = Title.builder()
                .id(1L)
                .title("Title")
                .author("Author")
                .publishedYear(LocalDate.now())
                .build();
        Book book = Book.builder()
                .id(1L)
                .title(title)
                .bookStatus(BookStatus.IN_LIBRARY)
                .build();
        List<Book> books = new ArrayList<>();
        books.add(book);
        //WHEN
        List<BookDto> booksDto = bookMapper.mapToBooksDto(books);
        //THEN
        assertEquals(1, booksDto.size());
    }
}
