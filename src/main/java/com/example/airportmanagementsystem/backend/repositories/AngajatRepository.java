package com.example.airportmanagementsystem.backend.repositories;

import com.example.airportmanagementsystem.backend.models.Angajat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AngajatRepository extends JpaRepository<Angajat, Integer> {
    @Query(value = "select * " +
            "from angajati " +
            "where angajati.id = :id_angajat",
            nativeQuery = true
    )
    public Angajat findAngajat(@Param("id_angajat") Integer id_angajat);
}
