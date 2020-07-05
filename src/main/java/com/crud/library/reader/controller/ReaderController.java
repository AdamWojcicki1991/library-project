package com.crud.library.reader.controller;

import com.crud.library.reader.domain.ReaderDto;
import com.crud.library.reader.mapper.ReaderMapper;
import com.crud.library.reader.service.ReaderServiceDb;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/reader")
public final class ReaderController {
    private final ReaderMapper readerMapper;
    private final ReaderServiceDb readerServiceDb;

    @GetMapping
    public List<ReaderDto> getReaders() {
        return readerMapper.mapToReadersDto(readerServiceDb.getAllReaders());
    }

    @GetMapping("/{id}")
    public ReaderDto getReader(@PathVariable final Long id) throws ReaderNotFoundException {
        return readerMapper.mapToReaderDto(readerServiceDb.getReaderById(id)
                .orElseThrow(() -> new ReaderNotFoundException("Reader doesn't exist in database!")));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
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
