package com.example.airportmanagementsystem.backend.repositories;

import com.example.airportmanagementsystem.backend.models.LinieAeriana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinieAerianaRepository extends JpaRepository<LinieAeriana, Integer> {
}
