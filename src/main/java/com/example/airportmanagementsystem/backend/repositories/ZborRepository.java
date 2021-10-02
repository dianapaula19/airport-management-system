package com.example.airportmanagementsystem.backend.repositories;

import com.example.airportmanagementsystem.backend.models.Zbor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ZborRepository extends JpaRepository<Zbor, Integer> {
    @Query(value = "select id " +
            "from zboruri " +
            "where id = :id_zbor", nativeQuery = true)
    public Zbor findZbor(@Param("id_zbor") Integer idZbor);
}
