package com.crud.library.borrow.domain;

import com.crud.library.book.domain.Book;
import com.crud.library.reader.domain.Reader;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Entity
@Table(name = "BORROWS")
public final class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "READER_ID")
    private Reader reader;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @NotNull
    @Column(name = "BORROW_DATE")
    private LocalDate borrowDate;

    @Column(name = "RETURN_DATE")
    private LocalDate returnDate;
}
