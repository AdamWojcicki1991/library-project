package com.crud.library.book.controller;

import com.crud.library.book.domain.BookDto;
import com.crud.library.title.domain.TitleDto;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class BookController {
    public void createBook(BookDto bookDto) {

    }

    public BookDto updateBookStatus(BookDto bookDto) {
        return new BookDto(bookDto.getId(), bookDto.getTitle(), bookDto.getBookStatus());
    }

    public List<BookDto> checkAvailableBooks(TitleDto titleDto) {
        return Collections.EMPTY_LIST;
    }

    public BookDto borrowBook(BookDto bookDto) {
        return new BookDto(bookDto.getId(), bookDto.getTitle(), bookDto.getBookStatus());
    }

    public BookDto returnBook(BookDto bookDto) {
        return new BookDto(bookDto.getId(), bookDto.getTitle(), bookDto.getBookStatus());
    }
}
