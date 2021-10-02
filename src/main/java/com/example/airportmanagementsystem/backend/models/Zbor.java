package com.example.airportmanagementsystem.backend.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Zboruri",
        uniqueConstraints =
@UniqueConstraint(columnNames = {"cod_zbor", "id_linie_aeriana"}
))
public class Zbor {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cod_zbor", columnDefinition = "SMALLINT CHECK (cod_zbor BETWEEN 1 AND 9999)", nullable = false)
    private int cod_zbor;

    @OneToMany(mappedBy = "zbor")
    private List<SosirePlecare> sosirePlecareList;

    @ManyToOne
    @JoinColumn(name = "id_aeronava", nullable = false)
    private Aeronava aeronava;

    @ManyToOne
    @JoinColumn(name = "id_linie_aeriana", nullable = false)
    private LinieAeriana linieAeriana;

    public Zbor(Integer id, int cod_zbor, Aeronava aeronava, LinieAeriana linieAeriana) {
        this.id = id;
        this.cod_zbor = cod_zbor;
        this.aeronava = aeronava;
        this.linieAeriana = linieAeriana;
    }

    public Zbor() {
    }

    public Integer getId() {
        return id;
    }

    public int getCod_zbor() {
        return cod_zbor;
    }

    public LinieAeriana getLinieAeriana() {
        return linieAeriana;
    }

    public String getDenumire() {
        return getLinieAeriana().getCod_linie_aeriana() + getCod_zbor();
    }

    public Aeronava getAeronava() {
        return aeronava;
    }



}
