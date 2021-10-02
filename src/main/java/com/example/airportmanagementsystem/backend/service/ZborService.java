package com.example.airportmanagementsystem.backend.service;


import com.example.airportmanagementsystem.backend.models.Zbor;
import com.example.airportmanagementsystem.backend.repositories.ZborRepository;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.SQLException;
import java.util.List;

@Service
public class ZborService {
    private ZborRepository zborRepository;

    public ZborService(ZborRepository zborRepository) {
        this.zborRepository = zborRepository;
    }

    public List<Zbor> findAll() throws NullPointerException {
        return zborRepository.findAll();
    }
    public void save(Zbor zbor)  {
        zborRepository.save(zbor);
    }

    public void delete(Zbor zbor) {
        zborRepository.delete(zbor);
    }

    public Zbor findZbor(Integer idZbor) {
        return zborRepository.findZbor(idZbor);
    }
}
