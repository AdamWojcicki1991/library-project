package com.crud.library.book.controller;

import com.crud.library.book.domain.Book;
import com.crud.library.book.domain.BookDto;
import com.crud.library.book.domain.BookStatusDto;
import com.crud.library.book.mapper.BookMapper;
import com.crud.library.book.service.BookServiceDb;
import com.crud.library.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/book")
public final class BookController {
    private final BookMapper bookMapper;
    private final BookServiceDb bookServiceDb;
    private final LibraryService libraryService;

    @GetMapping
    public List<BookDto> getBooks() {
        return bookMapper.mapToBooksDto(bookServiceDb.getAllBooks());
    }

    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable final Long id) throws BookNotFoundException {
        return bookMapper.mapToBookDto(bookServiceDb.getBookById(id)
                .orElseThrow(() -> new BookNotFoundException("Book doesn't exist in database!")));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void createBook(@RequestBody final BookDto bookDto) {
        bookServiceDb.saveBook(bookMapper.mapToBook(bookDto));
    }

    @PutMapping
    public BookDto updateBook(@RequestBody final BookDto bookDto) {
        return bookMapper.mapToBookDto(bookServiceDb.saveBook(bookMapper.mapToBook(bookDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable final Long id) {
        bookServiceDb.deleteBookById(id);
    }

    @PatchMapping("/{id}")
    public BookDto updateBookStatus(@PathVariable final Long id, @RequestBody final BookStatusDto bookStatusDto) throws BookNotFoundException {
        Book book = bookServiceDb.getBookById(id).orElseThrow(() -> new BookNotFoundException("Book doesn't exist in database!"));
        return bookMapper.mapToBookDto(bookServiceDb.saveBook(
                Book.builder()
                        .id(book.getId())
                        .title(book.getTitle())
                        .bookStatus(bookStatusDto.getBookStatus())
                        .borrows(book.getBorrows())
                        .build()));
    }

    @GetMapping("/search/title")
    public List<Book> getAvailableBooks(@RequestParam final String title) {
        return libraryService.findAvailableBooks(title);
    }
}
