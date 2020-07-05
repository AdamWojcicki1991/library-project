package com.crud.library.borrow.repository;

import com.crud.library.borrow.domain.Borrow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface BorrowRepository extends CrudRepository<Borrow, Long> {
    @Override
    Borrow save(Borrow borrow);

    @Override
    Optional<Borrow> findById(Long id);

    @Override
    List<Borrow> findAll();

    @Override
    void deleteById(Long id);

    @Override
    void deleteAll();
}
