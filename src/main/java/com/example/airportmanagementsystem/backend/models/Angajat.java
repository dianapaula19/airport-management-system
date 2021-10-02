package com.example.airportmanagementsystem.backend.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Angajati")
public class Angajat {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nume", length = 50, nullable = false)
    private String nume;

    @Column(name = "prenume", length = 50, nullable = false)
    private String prenume;

    @Column(name = "email", unique = true, length = 50)
    private String email;

    @Column(name = "numar_telefon", unique = true, length = 50, nullable = false)
    private String numar_telefon;

    @Column(name = "varsta", columnDefinition = "TINYINT CHECK (varsta BETWEEN 18 AND 65)", nullable = false)
    private int varsta;

    @ManyToOne
    @JoinColumn(name = "id_aeroport", nullable = false)
    private Aeroport aeroport;

    @ManyToOne
    @JoinColumn(name = "id_departament")
    private Departament departament;

    public Angajat(Integer id, String nume, String prenume, String email, String numar_telefon, int varsta, Aeroport aeroport, Departament departament) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.numar_telefon = numar_telefon;
        this.varsta = varsta;
        this.aeroport = aeroport;
        this.departament = departament;
    }

    public Angajat() {
    }

    public void setDepartament(Departament departament) {
        this.departament = departament;
    }

    public Integer getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getEmail() {
        return email;
    }

    public String getNumar_telefon() {
        return numar_telefon;
    }

    public int getVarsta() {
        return varsta;
    }

    public Aeroport getAeroport() {
        return aeroport;
    }

    public Departament getDepartament() {
        return departament;
    }
}
