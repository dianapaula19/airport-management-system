package com.example.airportmanagementsystem.frontend.views;

import com.example.airportmanagementsystem.backend.models.LinieAeriana;
import com.example.airportmanagementsystem.backend.service.LinieAerianaService;
import com.example.airportmanagementsystem.frontend.MainView;
import com.example.airportmanagementsystem.frontend.components.LinieAerianaForm;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "linie_aeriana", layout = MainView.class)
public class LinieAerianaView extends VerticalLayout {
    private LinieAerianaService linieAerianaService;
    Grid<LinieAeriana> gridLinieAeriana = new Grid<>(LinieAeriana.class);

    public LinieAerianaView(LinieAerianaService linieAerianaService) {
        this.linieAerianaService = linieAerianaService;
        LinieAerianaForm linieAerianaForm = new LinieAerianaForm(linieAerianaService, this);
        configureGridLinieAeriana(linieAerianaForm);
        add(gridLinieAeriana);
    }

    public void configureGridLinieAeriana(LinieAerianaForm linieAerianaForm) {
        gridLinieAeriana.setColumns("id", "cod_linie_aeriana", "denumire");
        gridLinieAeriana.setItems(linieAerianaService.findAll());
        gridLinieAeriana.getColumns().forEach(col -> col.setAutoWidth(true));
        gridLinieAeriana.asSingleSelect().addValueChangeListener(event -> {
            if(event.getValue() != null) {
                editDetails(event.getValue(), linieAerianaForm);
            }
        });
    }

    private void editDetails(LinieAeriana linieAeriana, LinieAerianaForm linieAerianaForm) {
        if(linieAeriana == null) {
            remove(linieAerianaForm);
        } else {
            add(linieAerianaForm);
            linieAerianaForm.setLinieAeriana(linieAeriana);
        }
    }
}
