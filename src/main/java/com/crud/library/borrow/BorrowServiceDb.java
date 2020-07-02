package com.crud.library.borrow;

import com.crud.library.borrow.repository.BorrowRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BorrowServiceDb {
    private BorrowRepository borrowRepository;
}
