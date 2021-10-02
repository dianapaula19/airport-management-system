package com.example.airportmanagementsystem.backend.service;

import com.example.airportmanagementsystem.backend.models.SosirePlecare;
import com.example.airportmanagementsystem.backend.repositories.SosirePlecareRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class SosirePlecareService {
    private SosirePlecareRepository sosirePlecareRepository;

    public SosirePlecareService(SosirePlecareRepository sosirePlecareRepository) {
        this.sosirePlecareRepository = sosirePlecareRepository;
    }

    public List<SosirePlecare> findAll() throws NullPointerException {
        return sosirePlecareRepository.findAll();
    }

    public void save(SosirePlecare sosirePlecare) {
        sosirePlecareRepository.save(sosirePlecare);
    }

    public void delete(SosirePlecare sosirePlecare) {
        sosirePlecareRepository.delete(sosirePlecare);
    }
}
