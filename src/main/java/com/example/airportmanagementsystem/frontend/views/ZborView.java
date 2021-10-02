package com.example.airportmanagementsystem.frontend.views;

import com.example.airportmanagementsystem.backend.models.Aeronava;
import com.example.airportmanagementsystem.backend.models.Angajat;
import com.example.airportmanagementsystem.backend.models.LinieAeriana;
import com.example.airportmanagementsystem.backend.models.Zbor;
import com.example.airportmanagementsystem.backend.service.AeronavaService;
import com.example.airportmanagementsystem.backend.service.LinieAerianaService;
import com.example.airportmanagementsystem.backend.service.ZborService;
import com.example.airportmanagementsystem.frontend.MainView;
import com.example.airportmanagementsystem.frontend.components.AngajatForm;
import com.example.airportmanagementsystem.frontend.components.ZborForm;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "zbor", layout = MainView.class)
public class ZborView extends VerticalLayout {
    private ZborService zborService;
    private LinieAerianaService linieAerianaService;
    private AeronavaService aeronavaService;
    Grid<Zbor> gridZbor = new Grid<>(Zbor.class);

    public ZborView(ZborService zborService,
                    LinieAerianaService linieAerianaService,
                    AeronavaService aeronavaService) {
        this.zborService = zborService;
        this.linieAerianaService = linieAerianaService;
        this.aeronavaService = aeronavaService;
        ZborForm zborForm = new ZborForm(zborService, linieAerianaService.findAll(), aeronavaService.findAll(), this);
        configureGridZbor(zborForm);
        add(gridZbor);
    }

    public void configureGridZbor(ZborForm zborForm) {
        gridZbor.setColumns("id", "cod_zbor");
        gridZbor.addColumn(zbor -> {
            LinieAeriana linieAeriana = zbor.getLinieAeriana();
            return linieAeriana == null ? "-" : linieAeriana.getDenumire();
        }).setHeader("Linie Aeriana");
        gridZbor.addColumn(zbor -> {
            Aeronava aeronava = zbor.getAeronava();
            return aeronava == null ? "-" : aeronava.getDenumire();
        }).setHeader("Aeronava");
        gridZbor.getColumns().forEach(col -> col.setAutoWidth(true));
        gridZbor.setItems(zborService.findAll());
        gridZbor.asSingleSelect().addValueChangeListener(event -> {
            if(event.getValue() != null) {
                editDetails(event.getValue(), zborForm);
            }
        });
    }

    public void editDetails(Zbor zbor, ZborForm zborForm){
        if(zbor == null) {
            remove(zborForm);
        } else {
            add(zborForm);
            zborForm.setZbor(zbor);
        }
    }
}
