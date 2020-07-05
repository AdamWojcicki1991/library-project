package com.crud.library.borrow.controller;

import com.crud.library.borrow.domain.BorrowDto;
import com.crud.library.borrow.mapper.BorrowMapper;
import com.crud.library.borrow.service.BorrowServiceDb;
import com.crud.library.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/borrow")
public final class BorrowController {
    private final BorrowMapper borrowMapper;
    private final BorrowServiceDb borrowServiceDb;
    private final LibraryService libraryService;

    @GetMapping
    public List<BorrowDto> getBorrows() {
        return borrowMapper.mapToBorrowsDto(borrowServiceDb.getAllBorrows());
    }

    @GetMapping("/{id}")
    public BorrowDto getBorrow(@PathVariable final Long id) throws BorrowNotFoundException {
        return borrowMapper.mapToBorrowDto(borrowServiceDb.getBorrowById(id)
                .orElseThrow(() -> new BorrowNotFoundException("Borrow doesn't exist in database!")));
    }

    @DeleteMapping("/{id}")
    public void deleteBorrow(@PathVariable final Long id) {
        borrowServiceDb.deleteBorrowById(id);
    }

    @PostMapping(value = "/borrowBook")
    public void borrowBook(@RequestParam final String bookTitle, @RequestParam final Long readerId) throws ProcessCanNotBeExecutedException {
        libraryService.executeBorrow(bookTitle, readerId);
    }

    @PutMapping("/returnBook")
    public BorrowDto returnBook(@RequestParam final Long bookId, @RequestParam final Long readerId) throws ProcessCanNotBeExecutedException {
        return borrowMapper.mapToBorrowDto(libraryService.executeReturn(bookId, readerId));
    }
}
