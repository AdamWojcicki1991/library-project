package com.crud.library.title.domain;

import com.crud.library.book.domain.Book;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Entity
@Table(name = "TITLES")
public final class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "AUTHOR")
    private String author;

    @NotNull
    @Column(name = "PUBLISHED_YEAR")
    private LocalDate publishedYear;

    @OneToMany(
            targetEntity = Book.class,
            mappedBy = "title",
            fetch = FetchType.LAZY
    )
    private List<Book> books;
}
