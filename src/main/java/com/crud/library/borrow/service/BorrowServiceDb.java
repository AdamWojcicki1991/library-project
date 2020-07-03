package com.crud.library.borrow.service;

import com.crud.library.borrow.domain.Borrow;
import com.crud.library.borrow.repository.BorrowRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public final class BorrowServiceDb {
    private final BorrowRepository borrowRepository;

    public Borrow saveBorrow(final Borrow borrow) {
        return borrowRepository.save(borrow);
    }

    public Optional<Borrow> getBorrowById(final Long id) {
        return borrowRepository.findById(id);
    }

    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    public void deleteBorrowById(final Long id) {
        borrowRepository.deleteById(id);
    }
}
