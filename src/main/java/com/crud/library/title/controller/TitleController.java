package com.crud.library.title.controller;

import com.crud.library.title.domain.TitleDto;
import com.crud.library.title.mapper.TitleMapper;
import com.crud.library.title.service.TitleServiceDb;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/titles")
public final class TitleController {
    private final TitleMapper titleMapper;
    private final TitleServiceDb titleServiceDb;

    @GetMapping
    public List<TitleDto> getTitles() {
        log.info("Get titles");
        return titleMapper.mapToTitlesDto(titleServiceDb.getAllTitles());
    }

    @GetMapping("/{id}")
    public TitleDto getTitle(@PathVariable final Long id) throws TitleNotFoundException {
        log.info("Get title by ID: " + id);
        return titleMapper.mapToTitleDto(titleServiceDb.getTitleById(id)
                .orElseThrow(() -> new TitleNotFoundException("Title doesn't exist in database!")));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void createTitle(@RequestBody final TitleDto titleDto) {
        log.info("Create title by: " + titleDto);
        titleServiceDb.saveTitle(titleMapper.mapToTitle(titleDto));
    }

    @PutMapping
    public TitleDto updateTitle(@RequestBody final TitleDto titleDto) {
        log.info("Update title by: " + titleDto);
        return titleMapper.mapToTitleDto(titleServiceDb.saveTitle(titleMapper.mapToTitle(titleDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteTitle(@PathVariable final Long id) {
        log.info("Delete title by ID: " + id);
        titleServiceDb.deleteTitleById(id);
    }
}
