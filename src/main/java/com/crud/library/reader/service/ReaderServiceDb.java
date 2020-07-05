package com.crud.library.reader.service;

import com.crud.library.reader.domain.Reader;
import com.crud.library.reader.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class ReaderServiceDb {
    private final ReaderRepository readerRepository;

    public Reader saveReader(final Reader reader) {
        return readerRepository.save(reader);
    }

    public Optional<Reader> getReaderById(final Long id) {
        return readerRepository.findById(id);
    }

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public void deleteReaderById(final Long id) {
        readerRepository.deleteById(id);
    }

    public void deleteAllReaders() {
        readerRepository.deleteAll();
    }
}
