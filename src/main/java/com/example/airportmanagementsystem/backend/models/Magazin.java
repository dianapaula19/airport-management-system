package com.example.airportmanagementsystem.backend.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Magazine",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_aeroport", "denumire"}),
        @UniqueConstraint(columnNames = {"id_aeroport", "etaj", "sala"})
})
public class Magazin {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "denumire", length = 50, nullable = false)
    private String denumire;

    @Column(name = "etaj", nullable = false, columnDefinition = "TINYINT")
    private int etaj;

    @Column(name = "sala", nullable = false, columnDefinition = "TINYINT")
    private int sala;

    @ManyToOne
    @JoinColumn(name = "id_aeroport", nullable = false)
    private Aeroport aeroport;

    public Magazin(Integer id, String denumire, int etaj, int sala, Aeroport aeroport) {
        this.id = id;
        this.denumire = denumire;
        this.etaj = etaj;
        this.sala = sala;
        this.aeroport = aeroport;
    }

    public Magazin() {
    }

    public Integer getId() {
        return id;
    }

    public String getDenumire() {
        return denumire;
    }

    public int getEtaj() {
        return etaj;
    }

    public int getSala() {
        return sala;
    }

    public Aeroport getAeroport() {
        return aeroport;
    }
}
