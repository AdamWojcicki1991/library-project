package com.crud.library.borrow.controller;

import com.crud.library.borrow.domain.BorrowDto;
import com.crud.library.borrow.mapper.BorrowMapper;
import com.crud.library.borrow.service.BorrowServiceDb;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/v1/borrow", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public final class BorrowController {
    private final BorrowServiceDb borrowServiceDb;
    private final BorrowMapper borrowMapper;

    @GetMapping
    public List<BorrowDto> getBorrows() {
        return borrowMapper.mapToBorrowsDto(borrowServiceDb.getAllBorrows());
    }

    @GetMapping("/{id}")
    public BorrowDto getBorrow(@PathVariable final Long id) throws BorrowNotFoundException {
        return borrowMapper.mapToBorrowDto(borrowServiceDb.getBorrowById(id)
                .orElseThrow(() -> new BorrowNotFoundException("Borrow doesn't exist in database!")));
    }

    @PostMapping("/borrowBook")
    public void borrowBook(@RequestBody final BorrowDto borrowDto) {
        //TODO
    }

    @PutMapping("/returnBook")
    public BorrowDto returnBook(@RequestBody final BorrowDto borrowDto) {
        //TODO
        return null;
    }
}
