package com.crud.library.borrow.domain;

import com.crud.library.book.domain.BookDto;
import com.crud.library.reader.domain.ReaderDto;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Builder
@Getter
@RequiredArgsConstructor
public final class BorrowDto {
    private final Long id;
    private final ReaderDto reader;
    private final BookDto book;
    private final LocalDate borrowDate;
    private final LocalDate returnDate;
}
