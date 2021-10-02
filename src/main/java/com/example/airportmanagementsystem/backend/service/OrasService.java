package com.example.airportmanagementsystem.backend.service;

import com.example.airportmanagementsystem.backend.models.Oras;
import com.example.airportmanagementsystem.backend.repositories.OrasRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class OrasService {
    private OrasRepository orasRepository;

    public OrasService(OrasRepository orasRepository) {
        this.orasRepository = orasRepository;
    }

    public List<Oras> findAll() throws NullPointerException {
        return orasRepository.findAll();
    }
    public void save(Oras oras) {
        orasRepository.save(oras);
    }
    public void delete(Oras oras) {
        orasRepository.delete(oras);
    }
}
