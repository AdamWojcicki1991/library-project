package com.crud.library.title.mapper;

import com.crud.library.title.domain.Title;
import com.crud.library.title.domain.TitleDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TitleMapperTestSuite {
    @Autowired
    private TitleMapper titleMapper;

    @Test
    public void testMapToTitle() {
        //GIVEN
        TitleDto titleDto = TitleDto.builder()
                .id(1L)
                .title("Title")
                .author("Author")
                .publishedYear(LocalDate.now())
                .build();
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
    public void testMapToTitleDto() {
        //GIVEN
        Title title = Title.builder()
                .id(1L)
                .title("Title")
                .author("Author")
                .publishedYear(LocalDate.now())
                .build();
        long titleId = title.getId();
        String titleFromEntity = title.getTitle();
        String author = title.getAuthor();
        LocalDate publishedYear = title.getPublishedYear();
        //WHEN
        TitleDto titleDto = titleMapper.mapToTitleDto(title);
        long titleDtoId = titleDto.getId();
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
    public void testMapToTitlesDto() {
        //GIVEN
        Title title = Title.builder()
                .id(1L)
                .title("Title")
                .author("Author")
                .publishedYear(LocalDate.now())
                .build();
        List<Title> titles = new ArrayList<>();
        titles.add(title);
        //WHEN
        List<TitleDto> titlesDto = titleMapper.mapToTitlesDto(titles);
        //THEN
        assertEquals(1, titlesDto.size());
    }
}
