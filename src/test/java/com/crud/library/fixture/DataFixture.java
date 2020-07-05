package com.crud.library.fixture;

import com.crud.library.book.BookStatus;
import com.crud.library.book.domain.Book;
import com.crud.library.book.domain.BookDto;
import com.crud.library.borrow.domain.Borrow;
import com.crud.library.borrow.domain.BorrowDto;
import com.crud.library.reader.domain.Reader;
import com.crud.library.reader.domain.ReaderDto;
import com.crud.library.title.domain.Title;
import com.crud.library.title.domain.TitleDto;

import java.time.LocalDate;
import java.util.Date;

public class DataFixture {
    public Title createTitle() {
        return Title.builder()
                .title("Title")
                .author("Author")
                .publishedYear(LocalDate.now())
                .build();
    }

    public Book createBook(Title title, BookStatus bookStatus) {
        return Book.builder()
                .title(title)
                .bookStatus(bookStatus)
                .build();
    }

    public Reader createReader() {
        return Reader.builder()
                .name("Name")
                .surname("Surname")
                .createAccountDate(new Date())
                .build();
    }

    public Borrow createBorrow(Book book, Reader reader) {
        return Borrow.builder()
                .borrowDate(LocalDate.now().minusMonths(1))
                .returnDate(LocalDate.now().plusMonths(1))
                .book(book)
                .reader(reader)
                .build();
    }

    public TitleDto createTitleDto() {
        return TitleDto.builder()
                .id(1L)
                .title("Title")
                .author("Author")
                .publishedYear(LocalDate.now())
                .build();
    }

    public BookDto createBookDto(TitleDto title, BookStatus bookStatus) {
        return BookDto.builder()
                .id(1L)
                .title(title)
                .bookStatus(bookStatus)
                .build();
    }

    public ReaderDto createReaderDto() {
        return ReaderDto.builder()
                .id(1L)
                .name("Name")
                .surname("Surname")
                .createAccountDate(new Date())
                .build();
    }

    public BorrowDto createBorrowDto(BookDto book, ReaderDto reader) {
        return BorrowDto.builder()
                .id(1L)
                .borrowDate(LocalDate.now().minusMonths(1))
                .returnDate(LocalDate.now().plusMonths(1))
                .book(book)
                .reader(reader)
                .build();
    }
}
