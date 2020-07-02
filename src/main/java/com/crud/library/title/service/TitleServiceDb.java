package com.crud.library.title.service;

import com.crud.library.title.repository.TitleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TitleServiceDb {
    private TitleRepository titleRepository;
}
