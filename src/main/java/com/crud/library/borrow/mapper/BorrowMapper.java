package com.crud.library.borrow.mapper;

import com.crud.library.borrow.domain.Borrow;
import com.crud.library.borrow.domain.BorrowDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public final class BorrowMapper {
    public Borrow mapToBorrow(final BorrowDto borrowDto) {
        return new Borrow(
                borrowDto.getId(),
                borrowDto.getReader(),
                borrowDto.getBook(),
                borrowDto.getBorrowDate(),
                borrowDto.getReturnDate());
    }

    public BorrowDto mapToBorrowDto(final Borrow borrow) {
        return new BorrowDto(
                borrow.getId(),
                borrow.getReader(),
                borrow.getBook(),
                borrow.getBorrowDate(),
                borrow.getReturnDate());
    }

    public List<BorrowDto> mapToBorrowsDto(final List<Borrow> borrows) {
        return borrows.stream()
                .map(borrow -> new BorrowDto(
                        borrow.getId(),
                        borrow.getReader(),
                        borrow.getBook(),
                        borrow.getBorrowDate(),
                        borrow.getReturnDate()))
                .collect(Collectors.toList());
    }
}
