package com.crud.library.reader.mapper;

import com.crud.library.fixture.DataFixture;
import com.crud.library.reader.domain.Reader;
import com.crud.library.reader.domain.ReaderDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReaderMapperTestSuite {
    private DataFixture dataFixture;
    @Autowired
    private ReaderMapper readerMapper;

    @Before
    public void setUp() {
        dataFixture = new DataFixture();
    }

    @Test
    public void shouldMapToReader() {
        //GIVEN
        ReaderDto readerDto = dataFixture.getReaderDto();
        long readerDtoId = readerDto.getId();
        String readerNameDto = readerDto.getName();
        String readerSurnameDto = readerDto.getSurname();
        //WHEN
        Reader reader = readerMapper.mapToReader(readerDto);
        long readerId = reader.getId();
        String readerName = reader.getName();
        String readerSurname = reader.getSurname();
        //THEN
        assertEquals(readerDtoId, readerId);
        assertEquals(readerNameDto, readerName);
        assertEquals(readerSurnameDto, readerSurname);
    }

    @Test
    public void shouldMapToReaderDto() {
        //GIVEN
        Reader reader = dataFixture.getReader();
        Long readerId = reader.getId();
        String readerName = reader.getName();
        String readerSurname = reader.getSurname();
        Date readerCreateAccountDate = reader.getCreateAccountDate();
        //WHEN
        ReaderDto readerDto = readerMapper.mapToReaderDto(reader);
        Long readerDtoId = readerDto.getId();
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
    public void shouldMapToReadersDto() {
        //GIVEN
        Reader reader = dataFixture.getReader();
        Date createAccountDate = reader.getCreateAccountDate();
        List<Reader> readers = new ArrayList<>();
        readers.add(reader);
        //WHEN
        List<ReaderDto> readersDto = readerMapper.mapToReadersDto(readers);
        //THEN
        assertEquals(1, readersDto.size());
        assertNull(readersDto.get(0).getId());
        assertEquals("Name", readersDto.get(0).getName());
        assertEquals("Surname", readersDto.get(0).getSurname());
        assertEquals(createAccountDate, readersDto.get(0).getCreateAccountDate());
    }
}
