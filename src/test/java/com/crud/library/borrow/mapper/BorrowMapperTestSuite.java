package com.crud.library.borrow.mapper;

import com.crud.library.book.BookStatus;
import com.crud.library.book.domain.Book;
import com.crud.library.borrow.domain.Borrow;
import com.crud.library.borrow.domain.BorrowDto;
import com.crud.library.reader.domain.Reader;
import com.crud.library.title.domain.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BorrowMapperTestSuite {
    @Autowired
    private BorrowMapper borrowMapper;

    @Test
    public void testMapToBorrow() {
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
        Reader reader = Reader.builder()
                .id(1L)
                .name("Name")
                .surname("Surname")
                .createAccountDate(new Date())
                .build();
        BorrowDto borrowDto = BorrowDto.builder()
                .id(1L)
                .borrowDate(LocalDate.now())
                .returnDate(LocalDate.now())
                .book(book)
                .reader(reader)
                .build();
        long borrowDtoId = borrowDto.getId();
        LocalDate borrowDateDto = borrowDto.getBorrowDate();
        LocalDate returnDateDto = borrowDto.getReturnDate();
        Book borrowBookDto = borrowDto.getBook();
        Reader borrowReaderDto = borrowDto.getReader();
        //WHEN
        Borrow borrow = borrowMapper.mapToBorrow(borrowDto);
        long borrowId = borrow.getId();
        LocalDate borrowDate = borrow.getBorrowDate();
        LocalDate returnDate = borrow.getReturnDate();
        Book borrowBook = borrow.getBook();
        Reader borrowReader = borrow.getReader();
        //THEN
        assertEquals(borrowDtoId, borrowId);
        assertEquals(borrowDateDto, borrowDate);
        assertEquals(returnDateDto, returnDate);
        assertEquals(borrowBookDto, borrowBook);
        assertEquals(borrowReaderDto, borrowReader);
    }

    @Test
    public void testMapToBorrowDto() {
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
        Reader reader = Reader.builder()
                .id(1L)
                .name("Name")
                .surname("Surname")
                .createAccountDate(new Date())
                .build();
        Borrow borrow = Borrow.builder()
                .id(1L)
                .borrowDate(LocalDate.now())
                .returnDate(LocalDate.now())
                .book(book)
                .reader(reader)
                .build();
        long borrowId = borrow.getId();
        LocalDate borrowDate = borrow.getBorrowDate();
        LocalDate returnDate = borrow.getReturnDate();
        Book borrowBook = borrow.getBook();
        Reader borrowReader = borrow.getReader();
        //WHEN
        BorrowDto borrowDto = borrowMapper.mapToBorrowDto(borrow);
        long borrowDtoId = borrowDto.getId();
        LocalDate borrowDateDto = borrowDto.getBorrowDate();
        LocalDate returnDateDto = borrowDto.getReturnDate();
        Book borrowBookDto = borrowDto.getBook();
        Reader borrowReaderDto = borrowDto.getReader();
        //THEN
        assertEquals(borrowId, borrowDtoId);
        assertEquals(borrowDate, borrowDateDto);
        assertEquals(returnDate, returnDateDto);
        assertEquals(borrowBook, borrowBookDto);
        assertEquals(borrowReader, borrowReaderDto);
    }

    @Test
    public void testMapToBorrowsDto() {
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
        Reader reader = Reader.builder()
                .id(1L)
                .name("Name")
                .surname("Surname")
                .createAccountDate(new Date())
                .build();
        Borrow borrow = Borrow.builder()
                .id(1L)
                .borrowDate(LocalDate.now())
                .returnDate(LocalDate.now())
                .book(book)
                .reader(reader)
                .build();
        List<Borrow> borrows = new ArrayList<>();
        borrows.add(borrow);
        //WHEN
        List<BorrowDto> borrowsDto = borrowMapper.mapToBorrowsDto(borrows);
        //THEN
        assertEquals(1, borrowsDto.size());
    }
}
