package com.crud.library.title.repository;

import com.crud.library.title.domain.Title;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface TitleRepository extends CrudRepository<Title, Long> {
    @Override
    Title save(Title title);

    @Override
    Optional<Title> findById(Long id);

    @Override
    List<Title> findAll();

    @Override
    void deleteById(Long id);

    @Override
    void deleteAll();
}
