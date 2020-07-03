package com.crud.library.book.controller;

import com.crud.library.book.domain.BookDto;
import com.crud.library.book.mapper.BookMapper;
import com.crud.library.book.service.BookServiceDb;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.crud.library.book.BookStatus.IN_LIBRARY;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/v1/book", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public final class BookController {
    private final BookServiceDb bookServiceDb;
    private final BookMapper bookMapper;

    @GetMapping
    public List<BookDto> getBooks() {
        return bookMapper.mapToBooksDto(bookServiceDb.getAllBooks());
    }

    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable final Long id) throws BookNotFoundException {
        return bookMapper.mapToBookDto(bookServiceDb.getBookById(id)
                .orElseThrow(() -> new BookNotFoundException("Book doesn't exist in database!")));
    }

    @PostMapping
    public void createBook(@RequestBody final BookDto bookDto) {
        bookServiceDb.saveBook(bookMapper.mapToBook(bookDto));
    }

    @PutMapping
    public BookDto updateBookStatus(@RequestBody final BookDto bookDto) {
        return bookMapper.mapToBookDto(bookServiceDb.saveBook(bookMapper.mapToBook(bookDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable final Long id) {
        bookServiceDb.deleteBookById(id);
    }

    @GetMapping("/search/title")
    public List<BookDto> getAvailableBooks(@RequestParam final String title) {
        return bookMapper.mapToBooksDto(bookServiceDb.getAllBooks()).stream()
                .filter(bookDto -> bookDto.getTitle().equals(title))
                .filter(bookDto -> bookDto.getBookStatus() == IN_LIBRARY)
                .collect(Collectors.toList());
    }
}
