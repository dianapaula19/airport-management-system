package com.example.airportmanagementsystem.backend.repositories;

import com.example.airportmanagementsystem.backend.models.Aeronava;
import com.example.airportmanagementsystem.backend.models.Zbor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface AeronavaRepository extends JpaRepository<Aeronava, Integer> {


}
