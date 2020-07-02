package com.crud.library.borrow.domain;

import com.crud.library.book.domain.Book;
import com.crud.library.reader.domain.Reader;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class BorrowDto {
    private Long id;
    private Reader reader;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate returnDate;
}
