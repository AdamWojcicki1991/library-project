package com.crud.library.reader.mapper;

import com.crud.library.reader.domain.Reader;
import com.crud.library.reader.domain.ReaderDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public final class ReaderMapper {
    public Reader mapToReader(final ReaderDto readerDto) {
        return Reader.builder()
                .id(readerDto.getId())
                .name(readerDto.getName())
                .surname(readerDto.getSurname())
                .createAccountDate(readerDto.getCreateAccountDate())
                .build();
    }

    public ReaderDto mapToReaderDto(final Reader reader) {
        return ReaderDto.builder()
                .id(reader.getId())
                .name(reader.getName())
                .surname(reader.getSurname())
                .createAccountDate(reader.getCreateAccountDate())
                .build();
    }

    public List<ReaderDto> mapToReadersDto(final List<Reader> readers) {
        return readers.stream()
                .map(reader -> new ReaderDto(
                        reader.getId(),
                        reader.getName(),
                        reader.getSurname(),
                        reader.getCreateAccountDate()))
                .collect(Collectors.toList());
    }
}
