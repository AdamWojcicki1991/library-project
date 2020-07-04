package com.crud.library.borrow.domain;

import com.crud.library.book.domain.Book;
import com.crud.library.reader.domain.Reader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "BORROWS")
public final class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "READER_ID")
    private Reader reader;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @NotNull
    @Column(name = "BORROW_DATE")
    private LocalDate borrowDate;

    @NotNull
    @Column(name = "RETURN_DATE")
    private LocalDate returnDate;
}
