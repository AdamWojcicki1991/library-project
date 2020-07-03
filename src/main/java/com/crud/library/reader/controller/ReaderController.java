package com.crud.library.reader.controller;

import com.crud.library.reader.domain.ReaderDto;
import com.crud.library.reader.mapper.ReaderMapper;
import com.crud.library.reader.service.ReaderServiceDb;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/v1/reader", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public final class ReaderController {
    private final ReaderServiceDb readerServiceDb;
    private final ReaderMapper readerMapper;

    @GetMapping
    public List<ReaderDto> getBooks() {
        return readerMapper.mapToReadersDto(readerServiceDb.getAllReaders());
    }

    @GetMapping("/{id}")
    public ReaderDto getReader(@PathVariable final Long id) throws ReaderNotFoundException {
        return readerMapper.mapToReaderDto(readerServiceDb.getReaderById(id)
                .orElseThrow(() -> new ReaderNotFoundException("Reader doesn't exist in database!")));
    }

    @PostMapping
    public void createReader(@RequestBody final ReaderDto readerDto) {
        readerServiceDb.saveReader(readerMapper.mapToReader(readerDto));
    }

    @PutMapping
    public ReaderDto updateReader(@RequestBody final ReaderDto readerDto) {
        return readerMapper.mapToReaderDto(readerServiceDb.saveReader(readerMapper.mapToReader(readerDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteReader(@PathVariable final Long id) {
        readerServiceDb.deleteReaderById(id);
    }
}
