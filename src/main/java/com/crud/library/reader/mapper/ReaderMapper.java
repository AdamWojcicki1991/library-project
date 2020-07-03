package com.crud.library.reader.mapper;

import com.crud.library.reader.domain.Reader;
import com.crud.library.reader.domain.ReaderDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public final class ReaderMapper {
    public Reader mapToReader(final ReaderDto readerDto) {
        return new Reader(
                readerDto.getId(),
                readerDto.getName(), readerDto.getSurname(),
                readerDto.getCreateAccountDate(),
                readerDto.getBorrows());
    }

    public ReaderDto mapToReaderDto(final Reader reader) {
        return new ReaderDto(
                reader.getId(),
                reader.getName(),
                reader.getSurname(),
                reader.getCreateAccountDate(),
                reader.getBorrows());
    }

    public List<ReaderDto> mapToReadersDto(final List<Reader> readers) {
        return readers.stream()
                .map(reader -> new ReaderDto(
                        reader.getId(),
                        reader.getName(),
                        reader.getSurname(),
                        reader.getCreateAccountDate(),
                        reader.getBorrows()))
                .collect(Collectors.toList());
    }
}
