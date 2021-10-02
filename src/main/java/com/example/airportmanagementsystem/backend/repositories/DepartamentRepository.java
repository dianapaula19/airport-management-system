package com.example.airportmanagementsystem.backend.repositories;

import com.example.airportmanagementsystem.backend.models.Departament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentRepository extends JpaRepository<Departament, Integer> {
}
