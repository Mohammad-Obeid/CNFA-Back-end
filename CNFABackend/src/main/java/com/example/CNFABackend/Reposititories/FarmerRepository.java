package com.example.CNFABackend.Reposititories;

import com.example.CNFABackend.Entities.Farmers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FarmerRepository extends JpaRepository<Farmers,Integer> {
    Optional<Farmers> findFarmersByNationalId(String nationalId);
    Optional<List<Farmers>> findFarmersByNationalIdStartingWith(String nationalId);
    Page<Farmers> findAll(Pageable pageable);
    long count();

}
