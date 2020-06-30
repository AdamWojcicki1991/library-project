package com.crud.library.domain;

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
