package com.example.CNFABackend.Reposititories;

import com.example.CNFABackend.Entities.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface StorageRepository extends JpaRepository<ImageData,Integer> {
    Optional<ImageData> findByNationalId(String fileName);
}
