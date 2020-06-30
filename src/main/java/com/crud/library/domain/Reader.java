package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "READERS")
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @NotNull
    @Column(name = "CREATE_ACCOUNT_DATE")
    private Date createAccountDate;

    @OneToMany(
            targetEntity = Borrow.class,
            mappedBy = "reader",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Borrow> borrows;
}
