package com.crud.library.book.service;

import com.crud.library.book.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BookServiceDb {
    private BookRepository bookRepository;
}
