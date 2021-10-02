package com.example.airportmanagementsystem.backend.service;

import com.example.airportmanagementsystem.backend.models.Angajat;
import com.example.airportmanagementsystem.backend.repositories.AngajatRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AngajatService {

    private AngajatRepository angajatRepository;

    public AngajatService(AngajatRepository angajatRepository) {
        this.angajatRepository = angajatRepository;
    }

    public List<Angajat> findAll() throws NullPointerException {
        return angajatRepository.findAll();
    }
    public Angajat findAngajat(Integer id_angajat) {
        return angajatRepository.findAngajat(id_angajat);
    }

    public void save(Angajat angajat) {
        angajatRepository.save(angajat);
    }

    public void delete(Angajat angajat) {
        angajatRepository.delete(angajat);
    }

}
