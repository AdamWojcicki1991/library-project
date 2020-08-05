package com.crud.library.title.mapper;

import com.crud.library.fixture.DataFixture;
import com.crud.library.title.domain.Title;
import com.crud.library.title.domain.TitleDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TitleMapperTestSuite {
    private DataFixture dataFixture;
    @Autowired
    private TitleMapper titleMapper;

    @Before
    public void setUp() {
        dataFixture = new DataFixture();
    }

    @Test
    public void shouldMapToTitle() {
        //GIVEN
        TitleDto titleDto = dataFixture.getTitleDto();
        long titleDtoId = titleDto.getId();
        String titleFromDto = titleDto.getTitle();
        String authorDto = titleDto.getAuthor();
        LocalDate publishedYearDto = titleDto.getPublishedYear();
        //WHEN
        Title title = titleMapper.mapToTitle(titleDto);
        long titleId = title.getId();
        String titleFromMapper = title.getTitle();
        String author = title.getAuthor();
        LocalDate publishedYear = title.getPublishedYear();
        //THEN
        assertEquals(titleDtoId, titleId);
        assertEquals(titleFromDto, titleFromMapper);
        assertEquals(authorDto, author);
        assertEquals(publishedYearDto, publishedYear);
    }

    @Test
    public void shouldMapToTitleDto() {
        //GIVEN
        Title title = dataFixture.getTitle();
        Long titleId = title.getId();
        String titleFromEntity = title.getTitle();
        String author = title.getAuthor();
        LocalDate publishedYear = title.getPublishedYear();
        //WHEN
        TitleDto titleDto = titleMapper.mapToTitleDto(title);
        Long titleDtoId = titleDto.getId();
        String titleFromMapper = titleDto.getTitle();
        String authorDto = titleDto.getAuthor();
        LocalDate publishedYearDto = titleDto.getPublishedYear();
        //THEN
        assertEquals(titleId, titleDtoId);
        assertEquals(titleFromEntity, titleFromMapper);
        assertEquals(author, authorDto);
        assertEquals(publishedYear, publishedYearDto);
    }

    @Test
    public void shouldMapToTitlesDto() {
        //GIVEN
        Title title = dataFixture.getTitle();
        List<Title> titles = new ArrayList<>();
        titles.add(title);
        //WHEN
        List<TitleDto> titlesDto = titleMapper.mapToTitlesDto(titles);
        //THEN
        assertEquals(1, titlesDto.size());
        assertNull(titlesDto.get(0).getId());
        assertEquals("Author", titlesDto.get(0).getAuthor());
        assertEquals("Title", titlesDto.get(0).getTitle());
        assertEquals(LocalDate.now(), titlesDto.get(0).getPublishedYear());
    }
}
