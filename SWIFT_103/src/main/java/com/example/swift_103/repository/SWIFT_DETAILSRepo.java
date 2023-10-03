package com.example.swift_103.repository;

import com.example.swift_103.entities.SWIFT_DETAILS;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SWIFT_DETAILSRepo extends JpaRepository<SWIFT_DETAILS, Integer> {
    void deleteByTABLEID(Integer id2);

    List<SWIFT_DETAILS> findByTABLEID(String tableID);
}
