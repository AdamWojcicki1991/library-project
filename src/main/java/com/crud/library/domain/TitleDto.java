package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class TitleDto {
    private long id;
    private String title;
    private String author;
    private LocalDate publishedYear;
    private List<Book> books;
}
