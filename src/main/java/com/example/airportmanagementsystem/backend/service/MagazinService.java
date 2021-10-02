package com.example.airportmanagementsystem.backend.service;

import com.example.airportmanagementsystem.backend.models.Magazin;
import com.example.airportmanagementsystem.backend.repositories.MagazinRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class MagazinService {
    private MagazinRepository magazinRepository;

    public MagazinService(MagazinRepository magazinRepository) {
        this.magazinRepository = magazinRepository;
    }

    public List<Magazin> findAll() throws NullPointerException {
        return magazinRepository.findAll();
    }
    public void save(Magazin magazin) {
        magazinRepository.save(magazin);
    }
    public void delete(Magazin magazin) {
        magazinRepository.delete(magazin);
    }
}
