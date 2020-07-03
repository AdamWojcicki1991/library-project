package com.crud.library.reader.domain;

import com.crud.library.borrow.domain.Borrow;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public final class ReaderDto {
    private final Long id;
    private final String name;
    private final String surname;
    private final Date createAccountDate;
    private final List<Borrow> borrows;
}
