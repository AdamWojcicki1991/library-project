package com.crud.library.book.domain;

import com.crud.library.book.BookStatus;
import com.crud.library.title.domain.Title;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookDto {
    private Long id;
    private Title title;
    private BookStatus bookStatus;
}
