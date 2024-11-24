package com.example.CNFABackend.Reposititories;

import com.example.CNFABackend.Entities.Logger;
import com.example.CNFABackend.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggerRepository extends JpaRepository<Logger,Integer> {
    Page<Logger> findAll(Pageable pageable);

}
