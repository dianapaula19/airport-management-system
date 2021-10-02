package com.example.airportmanagementsystem.backend.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Aeroporturi")
public class Aeroport {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cod_aeroport", length = 3, unique = true, nullable = false)
    private String cod_aeroport;

    @Column(name = "denumire", length = 50, unique = true, nullable = false)
    private String denumire;

    @OneToMany(mappedBy = "aeroport")
    private List<SosirePlecare> sosirePlecareList;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "aeroport")
    private List<Magazin> magazine;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "aeroport", fetch = FetchType.EAGER)
    private List<Angajat> angajati;

    @ManyToOne
    @JoinColumn(name = "id_oras", nullable = false)
    private Oras oras;

    public Aeroport(Integer id, String cod_aeroport, String denumire, Oras oras) {
        this.id = id;
        this.cod_aeroport = cod_aeroport;
        this.denumire = denumire;
        this.oras = oras;
    }

    public Aeroport() {
    }

    public Integer getId() {
        return id;
    }

    public String getCod_aeroport() {
        return cod_aeroport;
    }

    public String getDenumire() {
        return denumire;
    }

    public Oras getOras() {
        return oras;
    }

    public List<Angajat> getAngajati() {
        return angajati;
    }
}
