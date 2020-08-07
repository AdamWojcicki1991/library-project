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

public final class DataFixture {
    public Title getTitle() {
        return Title.builder()
                .title("Title")
                .author("Author")
                .publishedYear(LocalDate.now())
                .build();
    }

    public Book getBook(Title title, BookStatus bookStatus) {
        return Book.builder()
                .title(title)
                .bookStatus(bookStatus)
                .build();
    }

    public Reader getReader() {
        return Reader.builder()
                .name("Name")
                .surname("Surname")
                .build();
    }

    public Borrow getBorrow(Book book, Reader reader) {
        return Borrow.builder()
                .borrowDate(LocalDate.now().minusMonths(1))
                .book(book)
                .reader(reader)
                .build();
    }

    public TitleDto getTitleDto() {
        return TitleDto.builder()
                .id(1L)
                .title("Title")
                .author("Author")
                .publishedYear(LocalDate.now())
                .build();
    }

    public BookDto getBookDto(TitleDto title, BookStatus bookStatus) {
        return BookDto.builder()
                .id(1L)
                .title(title)
                .bookStatus(bookStatus)
                .build();
    }

    public ReaderDto getReaderDto() {
        return ReaderDto.builder()
                .id(1L)
                .name("Name")
                .surname("Surname")
                .createAccountDate(new Date())
                .build();
    }

    public BorrowDto getBorrowDto(BookDto book, ReaderDto reader) {
        return BorrowDto.builder()
                .id(1L)
                .borrowDate(LocalDate.now().minusMonths(1))
                .book(book)
                .reader(reader)
                .build();
    }
}
