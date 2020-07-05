package com.crud.library.title.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Builder
@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public final class TitleDto {
    private final Long id;
    private final String title;
    private final String author;
    private final LocalDate publishedYear;
}
