package com.example.airportmanagementsystem.backend.models;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Orase",
        uniqueConstraints = @UniqueConstraint(columnNames = {"denumire", "regiune", "tara"}))
public class Oras {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "denumire", length = 90)
    @NotNull
    private String denumire;

    @Column(name = "regiune", length = 50)
    @NotNull
    private String regiune;

    @Column(name = "tara", length = 60)
    @NotNull
    private String tara;

    @OneToMany(mappedBy = "oras")
    private List<Aeroport> aeropoarte;

    public Oras(Integer id, @NotNull String denumire, @NotNull String regiune, @NotNull String tara) {
        this.id = id;
        this.denumire = denumire;
        this.regiune = regiune;
        this.tara = tara;
    }

    public Oras() {
    }

    public Integer getId() {
        return id;
    }

    public String getDenumire() {
        return denumire;
    }

    public String getRegiune() {
        return regiune;
    }

    public String getTara() {
        return tara;
    }

    public String getDenumireCompleta() {
        return getDenumire() + ", " + getRegiune() + ", " + getTara();
    }
}
