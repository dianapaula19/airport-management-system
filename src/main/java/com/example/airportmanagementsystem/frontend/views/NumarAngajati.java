package com.example.airportmanagementsystem.frontend.views;

import com.example.airportmanagementsystem.backend.models.Aeroport;
import com.example.airportmanagementsystem.backend.models.Oras;
import com.example.airportmanagementsystem.backend.service.AeronavaService;
import com.example.airportmanagementsystem.backend.service.AeroportService;
import com.example.airportmanagementsystem.frontend.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "query_number", layout = MainView.class)
public class NumarAngajati extends VerticalLayout {
    private AeroportService aeroportService;
    Button aeroporturiMinDoiButton = new Button("Afisati aeroporturile cu cel putin 2 angajati");
    Grid<Aeroport> gridAeroport = new Grid<>(Aeroport.class);

    public NumarAngajati(AeroportService aeroportService) {
        this.aeroportService = aeroportService;
        add(aeroporturiMinDoiButton);
        aeroporturiMinDoiButton.addClickListener(event -> {
            if(event != null) {
                remove(gridAeroport);
                configureGrid();
                add(gridAeroport);
            }

        });
    }
    public void configureGrid() {
        gridAeroport.setColumns("id", "cod_aeroport", "denumire");
        gridAeroport.addColumn(aeroport -> {
            Oras oras = aeroport.getOras();
            return oras == null ? "-" : oras.getDenumireCompleta();
        }).setHeader("Oras");
        gridAeroport.addColumn(aeroport -> {
            return aeroport == null ? "-" : aeroport.getAngajati().size();
        }).setHeader("Nr Angajati");
        gridAeroport.getColumns().forEach(col -> col.setAutoWidth(true));
        gridAeroport.setItems(aeroportService.countAngajatMinTwo());
    }
}
