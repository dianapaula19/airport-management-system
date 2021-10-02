package com.example.airportmanagementsystem.backend.repositories;

import com.example.airportmanagementsystem.backend.models.Magazin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagazinRepository extends JpaRepository<Magazin, Integer> {
}
