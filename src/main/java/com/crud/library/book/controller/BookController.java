package com.crud.library.book.controller;

import com.crud.library.book.domain.BookDto;
import com.crud.library.book.mapper.BookMapper;
import com.crud.library.book.service.BookServiceDb;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/books")
public final class BookController {
    private final BookMapper bookMapper;
    private final BookServiceDb bookServiceDb;

    @GetMapping
    public List<BookDto> getBooks() {
        log.info("Get books");
        return bookMapper.mapToBooksDto(bookServiceDb.getAllBooks());
    }

    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable final Long id) throws BookNotFoundException {
        log.info("Get book by ID: " + id);
        return bookMapper.mapToBookDto(bookServiceDb.getBookById(id)
                .orElseThrow(() -> new BookNotFoundException("Book doesn't exist in database!")));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void createBook(@RequestBody final BookDto bookDto) {
        log.info("Create book by: " + bookDto);
        bookServiceDb.saveBook(bookMapper.mapToBook(bookDto));
    }

    @PutMapping
    public BookDto updateBook(@RequestBody final BookDto bookDto) {
        log.info("Update book by: " + bookDto);
        return bookMapper.mapToBookDto(bookServiceDb.saveBook(bookMapper.mapToBook(bookDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable final Long id) {
        log.info("Delete book by ID: " + id);
        bookServiceDb.deleteBookById(id);
    }
}
