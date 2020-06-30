package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookDto {
    private Long id;
    private Title title;
    private BookStatus bookStatus;
}
