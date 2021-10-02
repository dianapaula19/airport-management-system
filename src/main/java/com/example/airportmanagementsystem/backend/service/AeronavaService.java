package com.example.airportmanagementsystem.backend.service;

import com.example.airportmanagementsystem.backend.models.Aeronava;
import com.example.airportmanagementsystem.backend.models.Angajat;
import com.example.airportmanagementsystem.backend.repositories.AeronavaRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class AeronavaService {
    private AeronavaRepository aeronavaRepository;

    public AeronavaService(AeronavaRepository aeronavaRepository) {
        this.aeronavaRepository = aeronavaRepository;
    }

    public List<Aeronava> findAll() throws NullPointerException {
        return aeronavaRepository.findAll();
    }

    public void save(Aeronava aeronava)  {
        aeronavaRepository.save(aeronava);
    }

    public void delete(Aeronava aeronava) {

        aeronavaRepository.delete(aeronava);

    }

}
