package com.crud.library.reader.mapper;

import com.crud.library.reader.domain.Reader;
import com.crud.library.reader.domain.ReaderDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReaderMapperTestSuite {
    @Autowired
    private ReaderMapper readerMapper;

    @Test
    public void testMapToReader() {
        //GIVEN
        ReaderDto readerDto = ReaderDto.builder()
                .id(1L)
                .name("Name")
                .surname("Surname")
                .createAccountDate(new Date())
                .build();
        long readerDtoId = readerDto.getId();
        String readerNameDto = readerDto.getName();
        String readerSurnameDto = readerDto.getSurname();
        Date readerCreateAccountDateDto = readerDto.getCreateAccountDate();
        //WHEN
        Reader reader = readerMapper.mapToReader(readerDto);
        long readerId = reader.getId();
        String readerName = reader.getName();
        String readerSurname = reader.getSurname();
        Date readerCreateAccountDate = reader.getCreateAccountDate();
        //THEN
        assertEquals(readerDtoId, readerId);
        assertEquals(readerNameDto, readerName);
        assertEquals(readerSurnameDto, readerSurname);
        assertEquals(readerCreateAccountDateDto, readerCreateAccountDate);
    }

    @Test
    public void testMapToReaderDto() {
        //GIVEN
        Reader reader = Reader.builder()
                .id(1L)
                .name("Name")
                .surname("Surname")
                .createAccountDate(new Date())
                .build();
        long readerId = reader.getId();
        String readerName = reader.getName();
        String readerSurname = reader.getSurname();
        Date readerCreateAccountDate = reader.getCreateAccountDate();
        //WHEN
        ReaderDto readerDto = readerMapper.mapToReaderDto(reader);
        long readerDtoId = readerDto.getId();
        String readerNameDto = readerDto.getName();
        String readerSurnameDto = readerDto.getSurname();
        Date readerCreateAccountDateDto = readerDto.getCreateAccountDate();
        //THEN
        assertEquals(readerId, readerDtoId);
        assertEquals(readerName, readerNameDto);
        assertEquals(readerSurname, readerSurnameDto);
        assertEquals(readerCreateAccountDate, readerCreateAccountDateDto);
    }

    @Test
    public void testMapToReadersDto() {
        //GIVEN
        Reader reader = Reader.builder()
                .id(1L)
                .name("Name")
                .surname("Surname")
                .createAccountDate(new Date())
                .build();
        List<Reader> readers = new ArrayList<>();
        readers.add(reader);
        //WHEN
        List<ReaderDto> readersDto = readerMapper.mapToReadersDto(readers);
        //THEN
        assertEquals(1, readersDto.size());
    }
}
