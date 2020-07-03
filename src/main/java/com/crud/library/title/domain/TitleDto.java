package com.crud.library.title.domain;

import com.crud.library.book.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public final class TitleDto {
    private final long id;
    private final String title;
    private final String author;
    private final LocalDate publishedYear;
    private final List<Book> books;
}
