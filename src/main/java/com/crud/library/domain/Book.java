package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "BOOKS")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TITLE_ID")
    private Title title;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "BOOK_STATUS")
    private BookStatus bookStatus;
}
