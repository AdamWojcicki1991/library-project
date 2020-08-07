package com.crud.library.borrow.mapper;

import com.crud.library.book.domain.Book;
import com.crud.library.book.domain.BookDto;
import com.crud.library.borrow.domain.Borrow;
import com.crud.library.borrow.domain.BorrowDto;
import com.crud.library.fixture.DataFixture;
import com.crud.library.reader.domain.Reader;
import com.crud.library.reader.domain.ReaderDto;
import com.crud.library.title.domain.Title;
import com.crud.library.title.domain.TitleDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.crud.library.book.BookStatus.IN_LIBRARY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BorrowMapperTestSuite {
    private DataFixture dataFixture;
    @Autowired
    private BorrowMapper borrowMapper;

    @Before
    public void setUp() {
        dataFixture = new DataFixture();
    }

    @Test
    public void shouldMapToBorrow() {
        //GIVEN
        TitleDto title = dataFixture.getTitleDto();
        BookDto book = dataFixture.getBookDto(title, IN_LIBRARY);
        ReaderDto reader = dataFixture.getReaderDto();
        BorrowDto borrowDto = dataFixture.getBorrowDto(book, reader);
        //WHEN
        Borrow borrow = borrowMapper.mapToBorrow(borrowDto);
        //THEN
        assertEquals(borrowDto.getId(), borrow.getId());
        assertEquals(borrowDto.getBook().getId(), borrow.getBook().getId());
        assertEquals(borrowDto.getBook().getTitle().getId(), borrow.getBook().getTitle().getId());
        assertEquals(borrowDto.getBook().getTitle().getTitle(), borrow.getBook().getTitle().getTitle());
        assertEquals(borrowDto.getBook().getTitle().getAuthor(), borrow.getBook().getTitle().getAuthor());
        assertEquals(borrowDto.getBook().getTitle().getPublishedYear(), borrow.getBook().getTitle().getPublishedYear());
        assertEquals(borrowDto.getBook().getBookStatus(), borrow.getBook().getBookStatus());
        assertEquals(borrowDto.getReader().getId(), borrow.getReader().getId());
        assertEquals(borrowDto.getReader().getName(), borrow.getReader().getName());
        assertEquals(borrowDto.getReader().getSurname(), borrow.getReader().getSurname());
        assertEquals(borrowDto.getReader().getCreateAccountDate(), borrow.getReader().getCreateAccountDate());
        assertEquals(borrowDto.getBorrowDate(), borrow.getBorrowDate());
        assertEquals(borrowDto.getReturnDate(), borrow.getReturnDate());
    }

    @Test
    public void shouldMapToBorrowDto() {
        //GIVEN
        Title title = dataFixture.getTitle();
        Book book = dataFixture.getBook(title, IN_LIBRARY);
        Reader reader = dataFixture.getReader();
        Borrow borrow = dataFixture.getBorrow(book, reader);
        //WHEN
        BorrowDto borrowDto = borrowMapper.mapToBorrowDto(borrow);
        //THEN
        assertEquals(borrow.getId(), borrowDto.getId());
        assertEquals(borrow.getBook().getId(), borrowDto.getBook().getId());
        assertEquals(borrow.getBook().getTitle().getId(), borrowDto.getBook().getTitle().getId());
        assertEquals(borrow.getBook().getTitle().getTitle(), borrowDto.getBook().getTitle().getTitle());
        assertEquals(borrow.getBook().getTitle().getAuthor(), borrowDto.getBook().getTitle().getAuthor());
        assertEquals(borrow.getBook().getTitle().getPublishedYear(), borrowDto.getBook().getTitle().getPublishedYear());
        assertEquals(borrow.getBook().getBookStatus(), borrowDto.getBook().getBookStatus());
        assertEquals(borrow.getReader().getId(), borrowDto.getReader().getId());
        assertEquals(borrow.getReader().getName(), borrowDto.getReader().getName());
        assertEquals(borrow.getReader().getSurname(), borrowDto.getReader().getSurname());
        assertEquals(borrow.getReader().getCreateAccountDate(), borrowDto.getReader().getCreateAccountDate());
        assertEquals(borrow.getBorrowDate(), borrowDto.getBorrowDate());
        assertEquals(borrow.getReturnDate(), borrowDto.getReturnDate());
    }

    @Test
    public void shouldMapToBorrowsDto() {
        //GIVEN
        Title title = dataFixture.getTitle();
        Book book = dataFixture.getBook(title, IN_LIBRARY);
        Reader reader = dataFixture.getReader();
        Borrow borrow = dataFixture.getBorrow(book, reader);
        Date createAccountDate = reader.getCreateAccountDate();
        LocalDate borrowDate = borrow.getBorrowDate();
        LocalDate returnDate = borrow.getReturnDate();
        List<Borrow> borrows = new ArrayList<>();
        borrows.add(borrow);
        //WHEN
        List<BorrowDto> borrowsDto = borrowMapper.mapToBorrowsDto(borrows);
        //THEN
        assertEquals(1, borrowsDto.size());
        assertNull(borrowsDto.get(0).getId());
        assertEquals(borrowDate, borrowsDto.get(0).getBorrowDate());
        assertEquals(returnDate, borrowsDto.get(0).getReturnDate());
        assertNull(borrowsDto.get(0).getBook().getId());
        assertEquals(IN_LIBRARY, borrowsDto.get(0).getBook().getBookStatus());
        assertNull(borrowsDto.get(0).getBook().getTitle().getId());
        assertEquals("Author", borrowsDto.get(0).getBook().getTitle().getAuthor());
        assertEquals("Title", borrowsDto.get(0).getBook().getTitle().getTitle());
        assertEquals(LocalDate.now(), borrowsDto.get(0).getBook().getTitle().getPublishedYear());
        assertNull(borrowsDto.get(0).getReader().getId());
        assertEquals("Name", borrowsDto.get(0).getReader().getName());
        assertEquals("Surname", borrowsDto.get(0).getReader().getSurname());
        assertEquals(createAccountDate, borrowsDto.get(0).getReader().getCreateAccountDate());
    }
}
