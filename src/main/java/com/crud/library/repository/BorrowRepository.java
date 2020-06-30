package com.crud.library.repository;

import com.crud.library.domain.Borrow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface BorrowRepository extends CrudRepository<Borrow, Long> {
    @Override
    Optional<Borrow> findById(Long id);

    @Override
    List<Borrow> findAll();
}
