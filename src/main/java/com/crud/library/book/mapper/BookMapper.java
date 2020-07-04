package com.crud.library.book.mapper;

import com.crud.library.book.domain.Book;
import com.crud.library.book.domain.BookDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public final class BookMapper {
    public Book mapToBook(final BookDto bookDto) {
        return new Book(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getBookStatus());
    }

    public BookDto mapToBookDto(final Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getBookStatus());
    }

    public List<BookDto> mapToBooksDto(final List<Book> books) {
        return books.stream()
                .map(book -> new BookDto(
                        book.getId(),
                        book.getTitle(),
                        book.getBookStatus()))
                .collect(Collectors.toList());
    }
}
