package com.crud.library.borrow.domain;

import com.crud.library.book.domain.Book;
import com.crud.library.reader.domain.Reader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
public final class BorrowDto {
    private final Long id;
    private final Reader reader;
    private final Book book;
    private final LocalDate borrowDate;
    private final LocalDate returnDate;
}
