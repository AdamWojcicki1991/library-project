package com.crud.library.reader.service;

import com.crud.library.reader.repository.ReaderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ReaderServiceDb {
    private ReaderRepository readerRepository;
}
