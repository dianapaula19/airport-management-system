package com.example.airportmanagementsystem.backend.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Linii_Aeriene")
public class LinieAeriana {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cod_linie_aeriana", length = 2, unique = true, nullable = false)
    private String cod_linie_aeriana;

    @Column(name = "denumire", length = 50, unique = true, nullable = false)
    private String denumire;

    @OneToMany(mappedBy = "linieAeriana")
    private List<Zbor> zborList;

    public LinieAeriana(Integer id, String cod_linie_aeriana, String denumire) {
        this.id = id;
        this.cod_linie_aeriana = cod_linie_aeriana;
        this.denumire = denumire;
    }

    public LinieAeriana() {
    }

    public Integer getId() {
        return id;
    }

    public String getCod_linie_aeriana() {
        return cod_linie_aeriana;
    }

    public String getDenumire() {
        return denumire;
    }
}
