package com.crud.library.title.service;

import com.crud.library.title.domain.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TitleServiceDbTestSuite {
    @Autowired
    private TitleServiceDb titleServiceDb;

    @Test
    public void testSaveTitle() {
        //GIVEN
        Title title = Title.builder()
                .title("Title")
                .author("Author")
                .publishedYear(LocalDate.now())
                .build();
        //WHEN
        long titleSavedInDatabaseId = titleServiceDb.saveTitle(title).getId();
        //THEN
        assertNotEquals(0, titleSavedInDatabaseId);
        //CLEANUP
        titleServiceDb.deleteTitleById(titleSavedInDatabaseId);
    }

    @Test
    public void testGetTitleById() {
        //GIVEN
        Title title = Title.builder()
                .title("Title")
                .author("Author")
                .publishedYear(LocalDate.now())
                .build();
        long titleSavedInDatabaseId = titleServiceDb.saveTitle(title).getId();
        //WHEN
        long titleFromDatabaseId = titleServiceDb.getTitleById(titleSavedInDatabaseId).get().getId();
        //THEN
        assertNotEquals(0, titleFromDatabaseId);
        //CLEANUP
        titleServiceDb.deleteTitleById(titleFromDatabaseId);
    }

    @Test
    public void getAllTitles() {
        //GIVEN
        Title title = Title.builder()
                .title("Title")
                .author("Author")
                .publishedYear(LocalDate.now())
                .build();
        long titleSavedInDatabaseId = titleServiceDb.saveTitle(title).getId();
        //WHEN
        List<Title> titles = titleServiceDb.getAllTitles();
        //THEN
        assertEquals(1, titles.size());
        //CLEANUP
        titleServiceDb.deleteTitleById(titleSavedInDatabaseId);
    }

    @Test
    public void deleteTitleById() {
        //GIVEN
        Title title = Title.builder()
                .title("Title")
                .author("Author")
                .publishedYear(LocalDate.now())
                .build();
        long titleSavedInDatabaseId = titleServiceDb.saveTitle(title).getId();
        //WHEN
        titleServiceDb.deleteTitleById(titleSavedInDatabaseId);
        //THEN
        assertFalse(titleServiceDb.getTitleById(titleSavedInDatabaseId).isPresent());
    }
}
