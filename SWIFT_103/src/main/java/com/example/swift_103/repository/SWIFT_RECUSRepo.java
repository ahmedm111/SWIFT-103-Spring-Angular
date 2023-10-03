package com.example.swift_103.repository;

import com.example.swift_103.entities.SWIFT_RECUS;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SWIFT_RECUSRepo extends JpaRepository<SWIFT_RECUS, Integer> {

    void deleteByTABLEID(Integer id2);

    List<SWIFT_RECUS> findByTABLEID(String tableID);
}
