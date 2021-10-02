package com.example.airportmanagementsystem.backend.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Aeronave")
public class Aeronava {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "denumire", length = 50, unique = true)
    private String denumire;

    @Column(name = "capacitate", columnDefinition = "SMALLINT")
    private int capacitate;

    @OneToMany(mappedBy = "aeronava")
    private List<Zbor> zborList;

    public Aeronava(Integer id, String denumire, int capacitate) {
        this.id = id;
        this.denumire = denumire;
        this.capacitate = capacitate;
    }

    public Aeronava() {
    }

    public Integer getId() {
        return id;
    }

    public String getDenumire() {
        return denumire;
    }

    public int getCapacitate() {
        return capacitate;
    }



}
