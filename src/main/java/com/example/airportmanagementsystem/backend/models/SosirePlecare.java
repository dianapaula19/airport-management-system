package com.example.airportmanagementsystem.backend.models;

import com.vaadin.flow.component.dependency.NpmPackage;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Sosire_Plecare")
public class SosirePlecare implements Serializable {

    @ManyToOne
    @MapsId("idAeroport")
    @JoinColumn(name = "id_aeroport")
    private Aeroport aeroport;

    @ManyToOne
    @MapsId("idZbor")
    @JoinColumn(name = "id_zbor")
    private Zbor zbor;

    @EmbeddedId
    private SosirePlecare.SosirePlecareId id;

    @Column(name = "poarta", nullable = false, columnDefinition = "TINYINT")
    private int poarta;

    @Column(name = "data", columnDefinition = "DATETIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    @Column(name = "tip",
            columnDefinition = "TINYINT CHECK (tip in (0, 1))",
            nullable = false
    )
    private int tip;


    @Embeddable
    public static class SosirePlecareId implements Serializable {

        @Column(name = "id_aeroport")
        private Integer idAeroport;

        @Column(name = "id_zbor")
        private Integer idZbor;

        public SosirePlecareId(Integer idAeroport, Integer idZbor) {
            this.idAeroport = idAeroport;
            this.idZbor = idZbor;
        }

        public SosirePlecareId() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass())
                return false;

            SosirePlecare.SosirePlecareId that = (SosirePlecare.SosirePlecareId) o;
            return Objects.equals(idAeroport, that.idAeroport) &&
                    Objects.equals(idZbor, that.idZbor);
        }

        @Override
        public int hashCode() {
            return Objects.hash(idAeroport, idZbor);
        }

    }

    public SosirePlecare(Aeroport aeroport, Zbor zbor, SosirePlecareId id, int poarta, Date data, int tip) {
        this.aeroport = aeroport;
        this.zbor = zbor;
        this.id = id;
        this.poarta = poarta;
        this.data = data;
        this.tip = tip;
    }

    public SosirePlecare() {
    }

    public SosirePlecareId getId() {
        return id;
    }

    public int getPoarta() {
        return poarta;
    }

    public Date getData() {
        return data;
    }

    public Aeroport getAeroport() {
        return aeroport;
    }

    public Zbor getZbor() {
        return zbor;
    }

    public int getTip() {
        return tip;
    }
}
