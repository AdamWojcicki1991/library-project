package com.crud.library.title.mapper;

import com.crud.library.title.domain.Title;
import com.crud.library.title.domain.TitleDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public final class TitleMapper {
    public Title mapToTitle(final TitleDto titleDto) {
        return new Title(
                titleDto.getId(),
                titleDto.getTitle(),
                titleDto.getAuthor(),
                titleDto.getPublishedYear(),
                titleDto.getBooks());
    }

    public TitleDto mapToTitleDto(final Title title) {
        return new TitleDto(
                title.getId(),
                title.getTitle(),
                title.getAuthor(),
                title.getPublishedYear(),
                title.getBooks());
    }

    public List<TitleDto> mapToTitlesDto(final List<Title> titles) {
        return titles.stream()
                .map(title -> new TitleDto(
                        title.getId(),
                        title.getTitle(),
                        title.getAuthor(),
                        title.getPublishedYear(),
                        title.getBooks()))
                .collect(Collectors.toList());
    }
}
