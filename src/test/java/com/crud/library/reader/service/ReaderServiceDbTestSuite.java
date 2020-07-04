package com.crud.library.reader.service;

import com.crud.library.reader.domain.Reader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReaderServiceDbTestSuite {
    @Autowired
    private ReaderServiceDb readerServiceDb;

    @Test
    public void testSaveReader() {
        //GIVEN
        Reader reader = Reader.builder()
                .name("Name")
                .surname("Surname")
                .createAccountDate(new Date())
                .build();
        //WHEN
        long readerSavedInDatabaseId = readerServiceDb.saveReader(reader).getId();
        //THEN
        assertNotEquals(0, readerSavedInDatabaseId);
        //CLEANUP
        readerServiceDb.deleteReaderById(readerSavedInDatabaseId);
    }

    @Test
    public void testGetReaderById() {
        //GIVEN
        Reader reader = Reader.builder()
                .name("Name")
                .surname("Surname")
                .createAccountDate(new Date())
                .build();
        long readerSavedInDatabaseId = readerServiceDb.saveReader(reader).getId();
        //WHEN
        long readerFromDatabaseId = readerServiceDb.getReaderById(readerSavedInDatabaseId).get().getId();
        //THEN
        assertNotEquals(0, readerFromDatabaseId);
        //CLEANUP
        readerServiceDb.deleteReaderById(readerFromDatabaseId);
    }

    @Test
    public void getAllReaders() {
        //GIVEN
        Reader reader = Reader.builder()
                .name("Name")
                .surname("Surname")
                .createAccountDate(new Date())
                .build();
        long readerSavedInDatabaseId = readerServiceDb.saveReader(reader).getId();
        //WHEN
        List<Reader> readers = readerServiceDb.getAllReaders();
        //THEN
        assertEquals(1, readers.size());
        //CLEANUP
        readerServiceDb.deleteReaderById(readerSavedInDatabaseId);
    }

    @Test
    public void deleteReaderById() {
        //GIVEN
        Reader reader = Reader.builder()
                .name("Name")
                .surname("Surname")
                .createAccountDate(new Date())
                .build();
        long readerSavedInDatabaseId = readerServiceDb.saveReader(reader).getId();
        //WHEN
        readerServiceDb.deleteReaderById(readerSavedInDatabaseId);
        //THEN
        assertFalse(readerServiceDb.getReaderById(readerSavedInDatabaseId).isPresent());
    }
}
