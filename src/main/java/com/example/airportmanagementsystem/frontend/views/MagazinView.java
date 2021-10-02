package com.example.airportmanagementsystem.frontend.views;

import com.example.airportmanagementsystem.backend.models.Aeroport;
import com.example.airportmanagementsystem.backend.models.Magazin;
import com.example.airportmanagementsystem.backend.service.AeroportService;
import com.example.airportmanagementsystem.backend.service.MagazinService;
import com.example.airportmanagementsystem.frontend.MainView;
import com.example.airportmanagementsystem.frontend.components.MagazinForm;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "magazin", layout = MainView.class)
public class MagazinView extends VerticalLayout {
    private MagazinService magazinService;
    private AeroportService aeroportService;
    Grid<Magazin> gridMagazin = new Grid<>(Magazin.class);

    public MagazinView(MagazinService magazinService, AeroportService aeroportService) {
        this.magazinService = magazinService;
        this.aeroportService = aeroportService;
        MagazinForm magazinForm = new MagazinForm(magazinService, aeroportService.findAll(), this);
        configureGridMagazin(magazinForm);
        add(gridMagazin);
    }

    public void configureGridMagazin(MagazinForm magazinForm) {
        gridMagazin.setColumns("id", "denumire", "etaj", "sala");
        gridMagazin.addColumn(magazin -> {
            Aeroport aeroport = magazin.getAeroport();
            return aeroport == null ? "-" : aeroport.getDenumire();
        }).setHeader("Aeroport");
        gridMagazin.getColumns().forEach(col -> col.setAutoWidth(true));
        gridMagazin.setItems(magazinService.findAll());
        gridMagazin.asSingleSelect().addValueChangeListener(event -> {
            if(event.getValue() != null) {
                editDetails(event.getValue(), magazinForm);
            }
        });

    }

    public void editDetails(Magazin magazin, MagazinForm magazinForm) {
        if(magazin == null) {
            remove(magazinForm);
        } else {
            add(magazinForm);
            magazinForm.setMagazin(magazin);
        }
    }
}
