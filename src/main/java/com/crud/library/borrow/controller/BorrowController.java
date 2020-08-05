package com.crud.library.borrow.controller;

import com.crud.library.borrow.domain.BorrowDto;
import com.crud.library.borrow.mapper.BorrowMapper;
import com.crud.library.borrow.service.BorrowServiceDb;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/borrow")
public final class BorrowController {
    private final BorrowMapper borrowMapper;
    private final BorrowServiceDb borrowServiceDb;

    @GetMapping
    public List<BorrowDto> getBorrows() {
        log.info("Get borrows");
        return borrowMapper.mapToBorrowsDto(borrowServiceDb.getAllBorrows());
    }

    @GetMapping("/{id}")
    public BorrowDto getBorrow(@PathVariable final Long id) throws BorrowNotFoundException {
        log.info("Get borrow by ID: " + id);
        return borrowMapper.mapToBorrowDto(borrowServiceDb.getBorrowById(id)
                .orElseThrow(() -> new BorrowNotFoundException("Borrow doesn't exist in database!")));
    }

    @DeleteMapping("/{id}")
    public void deleteBorrow(@PathVariable final Long id) {
        log.info("Delete borrow by ID: " + id);
        borrowServiceDb.deleteBorrowById(id);
    }
}
