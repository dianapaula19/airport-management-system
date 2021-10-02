package com.example.airportmanagementsystem.backend.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Departamente")
public class Departament {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "denumire", length = 50, unique = true, nullable = false)
    private String denumire;

    @OneToMany(mappedBy = "departament", cascade = CascadeType.DETACH)
    private List<Angajat> angajati;

    @PreRemove
    private void preRemove() {
        if(angajati != null) {
            angajati.forEach(angajat -> angajat.setDepartament(null));
        }
    }

    public Departament(Integer id, String denumire) {
        this.id = id;
        this.denumire = denumire;
    }

    public Departament() {
    }

    public Integer getId() {
        return id;
    }

    public String getDenumire() {
        return denumire;
    }
}
