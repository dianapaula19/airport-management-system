package com.example.airportmanagementsystem.frontend.views;

import com.example.airportmanagementsystem.backend.models.Aeronava;
import com.example.airportmanagementsystem.backend.models.Aeroport;
import com.example.airportmanagementsystem.backend.models.Oras;
import com.example.airportmanagementsystem.backend.service.AeroportService;
import com.example.airportmanagementsystem.backend.service.OrasService;
import com.example.airportmanagementsystem.frontend.MainView;
import com.example.airportmanagementsystem.frontend.components.AeronavaForm;
import com.example.airportmanagementsystem.frontend.components.AeroportForm;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "aeroport", layout = MainView.class)
public class AeroportView extends VerticalLayout {
    private AeroportService aeroportService;
    private OrasService orasService;
    Grid<Aeroport> gridAeroport = new Grid<>(Aeroport.class);

    public AeroportView(AeroportService aeroportService, OrasService orasService) {
        this.aeroportService = aeroportService;
        this.orasService = orasService;
        AeroportForm aeroportForm = new AeroportForm(aeroportService, orasService.findAll(), this);
        configureGridAeroport(aeroportForm);
        add(gridAeroport);
    }

    public void configureGridAeroport(AeroportForm aeroportForm) {
        gridAeroport.setColumns("id", "cod_aeroport", "denumire");
        gridAeroport.addColumn(aeroport -> {
            Oras oras = aeroport.getOras();
            return oras == null ? "-" : oras.getDenumireCompleta();
        }).setHeader("Oras");
        gridAeroport.getColumns().forEach(col -> col.setAutoWidth(true));
        gridAeroport.setItems(aeroportService.findAll());
        gridAeroport.asSingleSelect().addValueChangeListener(event -> {
            if(event.getValue() != null) {
                editDetails(event.getValue(), aeroportForm);
            }
        });
    }

    public void editDetails(Aeroport aeroport, AeroportForm aeroportForm){
        if(aeroport == null) {
            remove(aeroportForm);
        } else {
            add(aeroportForm);
            aeroportForm.setAeroport(aeroport);
        }
    }
}
