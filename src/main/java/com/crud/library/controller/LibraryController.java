package com.crud.library.controller;

import com.crud.library.book.domain.BookDto;
import com.crud.library.book.mapper.BookMapper;
import com.crud.library.borrow.controller.ProcessCanNotBeExecutedException;
import com.crud.library.borrow.domain.BorrowDto;
import com.crud.library.borrow.mapper.BorrowMapper;
import com.crud.library.facade.LibraryFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/library")
public final class LibraryController {
    private final BorrowMapper borrowMapper;
    private final BookMapper bookMapper;
    private final LibraryFacade libraryFacade;

    @GetMapping("/books/search")
    public List<BookDto> getAvailableBooks(@RequestParam final Long titleId) {
        log.info("Get available books by titleId: " + titleId);
        return bookMapper.mapToBooksDto(libraryFacade.findAvailableBooks(titleId));
    }

    @GetMapping("/borrows/search")
    public List<BorrowDto> getBorrowedBookByReader(@RequestParam final Long readerId) {
        log.info("Get reader borrows by readerId: " + readerId);
        return borrowMapper.mapToBorrowsDto(libraryFacade.findBorrowedBookByReader(readerId));
    }

    @PostMapping("/borrowBook")
    public void borrowBook(@RequestParam final Long titleId, @RequestParam final Long readerId) throws ProcessCanNotBeExecutedException {
        log.info("Borrow book by titleId: " + titleId + " and " + "readerID: " + readerId);
        libraryFacade.executeBorrow(titleId, readerId);
    }

    @PutMapping("/returnBook")
    public BorrowDto returnBook(@RequestParam final Long bookId, @RequestParam final Long readerId) throws ProcessCanNotBeExecutedException {
        log.info("Return book by bookId: " + bookId + " and " + "readerID: " + readerId);
        return borrowMapper.mapToBorrowDto(libraryFacade.executeReturn(bookId, readerId));
    }
}
