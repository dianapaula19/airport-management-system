package com.example.airportmanagementsystem.backend.service;

import com.example.airportmanagementsystem.backend.models.LinieAeriana;
import com.example.airportmanagementsystem.backend.repositories.LinieAerianaRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LinieAerianaService {
    private LinieAerianaRepository linieAerianaRepository;

    public LinieAerianaService(LinieAerianaRepository linieAerianaRepository) {
        this.linieAerianaRepository = linieAerianaRepository;
    }
    public List<LinieAeriana> findAll() throws NullPointerException {
        return linieAerianaRepository.findAll();
    }
    public void save(LinieAeriana linieAeriana) {
        linieAerianaRepository.save(linieAeriana);
    }
    public void delete(LinieAeriana linieAeriana) {
        linieAerianaRepository.delete(linieAeriana);
    }
}
