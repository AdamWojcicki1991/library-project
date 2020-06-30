package com.crud.library.service;

import com.crud.library.repository.BookRepository;
import com.crud.library.repository.BorrowRepository;
import com.crud.library.repository.ReaderRepository;
import com.crud.library.repository.TitleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DbService {
    private BookRepository bookRepository;
    private ReaderRepository readerRepository;
    private BorrowRepository borrowRepository;
    private TitleRepository titleRepository;
}
