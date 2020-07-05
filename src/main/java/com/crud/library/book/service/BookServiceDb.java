package com.crud.library.book.service;

import com.crud.library.book.domain.Book;
import com.crud.library.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class BookServiceDb {
    private final BookRepository bookRepository;

    public Book saveBook(final Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> getBookById(final Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void deleteBookById(final Long id) {
        bookRepository.deleteById(id);
    }

    public void deleteAllBooks() {
        bookRepository.deleteAll();
    }
}
