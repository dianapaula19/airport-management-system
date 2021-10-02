package com.example.airportmanagementsystem.backend.repositories;

import com.example.airportmanagementsystem.backend.models.SosirePlecare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SosirePlecareRepository extends JpaRepository<SosirePlecare, Integer> {
}
