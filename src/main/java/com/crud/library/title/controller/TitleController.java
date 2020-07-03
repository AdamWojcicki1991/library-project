package com.crud.library.title.controller;

import com.crud.library.title.domain.TitleDto;
import com.crud.library.title.mapper.TitleMapper;
import com.crud.library.title.service.TitleServiceDb;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/v1/title", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public final class TitleController {
    private final TitleServiceDb titleServiceDb;
    private final TitleMapper titleMapper;

    @GetMapping
    public List<TitleDto> getBooks() {
        return titleMapper.mapToTitlesDto(titleServiceDb.getAllTitles());
    }

    @GetMapping("/{id}")
    public TitleDto getTitle(@PathVariable final Long id) throws TitleNotFoundException {
        return titleMapper.mapToTitleDto(titleServiceDb.getTitleById(id)
                .orElseThrow(() -> new TitleNotFoundException("Title doesn't exist in database!")));
    }

    @PostMapping
    public void createTitle(@RequestBody final TitleDto titleDto) {
        titleServiceDb.saveTitle(titleMapper.mapToTitle(titleDto));
    }

    @PutMapping
    public TitleDto updateTitle(@RequestBody final TitleDto titleDto) {
        return titleMapper.mapToTitleDto(titleServiceDb.saveTitle(titleMapper.mapToTitle(titleDto)));
    }

    @DeleteMapping("{id}")
    public void deleteTitle(@PathVariable final Long id) {
        titleServiceDb.deleteTitleById(id);
    }
}
