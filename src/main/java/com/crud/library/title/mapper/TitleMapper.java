package com.crud.library.title.mapper;

import com.crud.library.title.domain.Title;
import com.crud.library.title.domain.TitleDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public final class TitleMapper {
    public Title mapToTitle(final TitleDto titleDto) {
        return Title.builder()
                .id(titleDto.getId())
                .title(titleDto.getTitle())
                .author(titleDto.getAuthor())
                .publishedYear(titleDto.getPublishedYear())
                .build();
    }

    public TitleDto mapToTitleDto(final Title title) {
        return TitleDto.builder()
                .id(title.getId())
                .title(title.getTitle())
                .author(title.getAuthor())
                .publishedYear(title.getPublishedYear())
                .build();
    }

    public List<TitleDto> mapToTitlesDto(final List<Title> titles) {
        return titles.stream()
                .map(title -> TitleDto.builder()
                        .id(title.getId())
                        .title(title.getTitle())
                        .author(title.getAuthor())
                        .publishedYear(title.getPublishedYear())
                        .build())
                .collect(Collectors.toList());
    }
}
