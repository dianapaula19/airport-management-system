package com.example.airportmanagementsystem.frontend.views;

import com.example.airportmanagementsystem.backend.models.Aeronava;
import com.example.airportmanagementsystem.backend.models.Angajat;
import com.example.airportmanagementsystem.backend.service.AeronavaService;
import com.example.airportmanagementsystem.frontend.MainView;
import com.example.airportmanagementsystem.frontend.components.AeronavaForm;
import com.example.airportmanagementsystem.frontend.components.AeroportForm;
import com.example.airportmanagementsystem.frontend.components.AngajatForm;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "aeronava", layout = MainView.class)
public class AeronavaView extends VerticalLayout {

    private AeronavaService aeronavaService;
    Grid<Aeronava> gridAeronava = new Grid<>(Aeronava.class);

    public AeronavaView(AeronavaService aeronavaService) {
        this.aeronavaService = aeronavaService;
        AeronavaForm aeronavaForm = new AeronavaForm(aeronavaService, this);
        configureAeronava(aeronavaForm);
        add(gridAeronava);
    }

    public void configureAeronava(AeronavaForm aeronavaForm) {
        gridAeronava.setColumns("id", "denumire", "capacitate");
        gridAeronava.setItems(aeronavaService.findAll());
        gridAeronava.getColumns().forEach(col -> col.setAutoWidth(true));
        gridAeronava.setItems(aeronavaService.findAll());
        gridAeronava.asSingleSelect().addValueChangeListener(event -> {
            if(event.getValue() != null) {
                editDetails(event.getValue(), aeronavaForm);
            }
        });
    }

    public void editDetails(Aeronava aeronava, AeronavaForm aeronavaForm){
        if(aeronava == null) {
            remove(aeronavaForm);
        } else {
            add(aeronavaForm);
            aeronavaForm.setAeronava(aeronava);
        }
    }
}
