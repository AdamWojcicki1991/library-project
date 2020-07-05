package com.crud.library.book.domain;

import com.crud.library.book.BookStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public final class BookStatusDto {
    private final Long id;
    private final BookStatus bookStatus;
}
