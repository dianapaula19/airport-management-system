package com.example.airportmanagementsystem.backend.service;


import com.example.airportmanagementsystem.backend.models.Aeroport;
import com.example.airportmanagementsystem.backend.models.SosirePlecare;
import com.example.airportmanagementsystem.backend.models.Zbor;
import com.example.airportmanagementsystem.backend.repositories.AeroportRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AeroportService {

    private AeroportRepository aeroportRepository;
    private SosirePlecareService sosirePlecareService;


    public AeroportService(AeroportRepository aeroportRepository, SosirePlecareService sosirePlecareService) {
        this.sosirePlecareService = sosirePlecareService;
        this.aeroportRepository = aeroportRepository;
    }

    public List<Aeroport> findAll() throws NullPointerException {
        return aeroportRepository.findAll();
    }
    public void save(Aeroport aeroport) {
        aeroportRepository.save(aeroport);
    }

    public void delete(Aeroport aeroport) {
        aeroportRepository.delete(aeroport);
    }

    public List<Zbor> findZboruri(int tip, String denumAeroport) {
        List<Zbor> zborList = new ArrayList<>();
        for (SosirePlecare sp : sosirePlecareService.findAll()) {
            if(sp.getAeroport().getDenumire().equals(denumAeroport) && sp.getTip() == tip) {
                zborList.add(sp.getZbor());
            }

        }
        return zborList;
    }

    public List<Aeroport> countAngajatMinTwo() {
        List<Aeroport> aeroportList = new ArrayList<>();
        for (Aeroport a : aeroportRepository.findAll()) {
            if(a.getAngajati().size() > 1)
                aeroportList.add(a);

        }
        return aeroportList;
    }


}
