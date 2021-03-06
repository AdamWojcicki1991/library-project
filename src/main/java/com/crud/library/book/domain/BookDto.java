package com.crud.library.book.domain;

import com.crud.library.book.BookStatus;
import com.crud.library.title.domain.TitleDto;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public final class BookDto {
    private final Long id;
    private final TitleDto title;
    private final BookStatus bookStatus;
}
