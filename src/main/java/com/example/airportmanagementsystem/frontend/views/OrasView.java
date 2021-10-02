package com.example.airportmanagementsystem.frontend.views;

import com.example.airportmanagementsystem.backend.models.Oras;
import com.example.airportmanagementsystem.backend.service.OrasService;
import com.example.airportmanagementsystem.frontend.MainView;
import com.example.airportmanagementsystem.frontend.components.OrasForm;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "oras", layout = MainView.class)
public class OrasView extends VerticalLayout {
    private OrasService orasService;
    Grid<Oras> gridOras = new Grid<>(Oras.class);

    public OrasView(OrasService orasService) {
        this.orasService = orasService;
        OrasForm orasForm = new OrasForm(orasService, this);
        configureGridOras(orasForm);
        add(gridOras);
    }

    public void configureGridOras(OrasForm orasForm) {
        gridOras.setColumns("id", "denumire", "regiune", "tara");
        gridOras.setItems(orasService.findAll());
        gridOras.getColumns().forEach(col -> col.setAutoWidth(true));
        gridOras.asSingleSelect().addValueChangeListener(event -> {
            if(event.getValue() != null) {
                editDetails(event.getValue(), orasForm);
            }
        });

    }

    public void editDetails(Oras oras, OrasForm orasForm) {
        if(oras == null) {
            remove(orasForm);
        } else {
            add(orasForm);
            orasForm.setOras(oras);
        }
    }


}
