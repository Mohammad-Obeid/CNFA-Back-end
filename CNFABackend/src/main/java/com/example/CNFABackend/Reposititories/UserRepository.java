package com.example.CNFABackend.Reposititories;

import com.example.CNFABackend.Entities.Farmers;
import com.example.CNFABackend.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUsername(String username);
    Page<User> findAllByRule(String rule, Pageable pageable);
    Optional<List<User>> findUsersByUsernameStartingWithAndRule(String name,String rule);
    long countByRule(String rule);

}
