package com.crud.library.book.domain;

import com.crud.library.book.BookStatus;
import com.crud.library.title.domain.Title;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public final class BookDto {
    private final Long id;
    private final Title title;
    private final BookStatus bookStatus;
}
