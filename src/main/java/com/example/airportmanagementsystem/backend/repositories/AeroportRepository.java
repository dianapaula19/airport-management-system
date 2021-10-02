package com.example.airportmanagementsystem.backend.repositories;

import com.example.airportmanagementsystem.backend.models.Aeronava;
import com.example.airportmanagementsystem.backend.models.Aeroport;
import com.example.airportmanagementsystem.backend.models.Zbor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

@Repository
public interface AeroportRepository extends JpaRepository<Aeroport, Integer> {

    @Query(value = "SELECT z.id " +
            "FROM aeroporturi a " +
            "JOIN sosire_plecare sp ON a.id = sp.id_aeroport " +
            "JOIN zboruri z ON sp.id_zbor = z.id " +
            "WHERE sp.tip = :tip and " +
            "a.denumire = :denumAeroport",
            nativeQuery = true
    )
    public List<Integer> listZboruriIds(@Param("tip") int tip, @Param("denumAeroport") String denumAeroport);


    @Query(value = "SELECT ar.denumire, count(an.id) " +
            "FROM aeroporturi ar " +
            "JOIN angajati an ON ar.id = an.id_aeroport " +
            "group by ar.denumire" +
            "having COUNT(an.id) > 1", nativeQuery = true)
    public List<Object[]> celPutinDoiAngajati();




}
