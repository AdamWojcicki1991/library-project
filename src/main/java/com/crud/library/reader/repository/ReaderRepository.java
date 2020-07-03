package com.crud.library.reader.repository;

import com.crud.library.reader.domain.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface ReaderRepository extends CrudRepository<Reader, Long> {
    @Override
    Reader save(Reader reader);

    @Override
    Optional<Reader> findById(Long id);

    @Override
    List<Reader> findAll();

    @Override
    void deleteById(Long id);
}
