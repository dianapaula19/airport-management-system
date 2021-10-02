package com.example.airportmanagementsystem.backend.service;

import com.example.airportmanagementsystem.backend.models.Departament;
import com.example.airportmanagementsystem.backend.repositories.DepartamentRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class DepartamentService {
    private DepartamentRepository departamentRepository;

    public DepartamentService(DepartamentRepository departamentRepository) {
        this.departamentRepository = departamentRepository;
    }

    public List<Departament> findAll() throws NullPointerException {
        return departamentRepository.findAll();
    }

    public void save(Departament departament)  {
        departamentRepository.save(departament);
    }

    public void delete(Departament departament) {
        departamentRepository.delete(departament);
    }

}
