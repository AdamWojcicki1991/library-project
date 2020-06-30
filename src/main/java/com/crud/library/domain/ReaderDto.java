package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class ReaderDto {
    private Long id;
    private String name;
    private String surname;
    private Date createAccountDate;
    private List<Borrow> borrows;
}
