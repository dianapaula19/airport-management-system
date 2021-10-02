package com.example.airportmanagementsystem.frontend.views;

import com.example.airportmanagementsystem.backend.models.Aeroport;
import com.example.airportmanagementsystem.backend.models.SosirePlecare;
import com.example.airportmanagementsystem.backend.models.Zbor;
import com.example.airportmanagementsystem.backend.service.AeroportService;
import com.example.airportmanagementsystem.backend.service.SosirePlecareService;
import com.example.airportmanagementsystem.backend.service.ZborService;
import com.example.airportmanagementsystem.frontend.MainView;
import com.example.airportmanagementsystem.frontend.components.SosirePlecareForm;
import com.example.airportmanagementsystem.frontend.components.ZborForm;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "sosire_plecare", layout = MainView.class)
public class SosirePlecareView extends VerticalLayout {
    private SosirePlecareService sosirePlecareService;
    private AeroportService aeroportService;
    private ZborService zborService;
    Grid<SosirePlecare> gridSosirePlecare = new Grid<>(SosirePlecare.class);

    public SosirePlecareView(SosirePlecareService sosirePlecareService,
                             AeroportService aeroportService,
                             ZborService zborService) {
        this.sosirePlecareService = sosirePlecareService;
        this.aeroportService = aeroportService;
        this.zborService = zborService;
        SosirePlecareForm sosirePlecareForm = new SosirePlecareForm(sosirePlecareService,
                aeroportService.findAll(),
                zborService.findAll(),
                this);
        configureGridSosirePlecare(sosirePlecareForm);
        add(gridSosirePlecare);
    }

    public void configureGridSosirePlecare(SosirePlecareForm sosirePlecareForm) {
        gridSosirePlecare.setColumns("poarta", "data");
        gridSosirePlecare.addColumn(sosirePlecare -> {
            return sosirePlecare.getTip() == 0 ? "Plecare" : "Sosire";
        }).setHeader("Tip");
        gridSosirePlecare.addColumn(sosirePlecare -> {
            Aeroport aeroport = sosirePlecare.getAeroport();
            return aeroport == null ? "-" : aeroport.getDenumire();
        }).setHeader("Aeroport");
        gridSosirePlecare.addColumn(sosirePlecare -> {
            Zbor zbor = sosirePlecare.getZbor();
            return zbor == null ? "-" : zbor.getDenumire();
        }).setHeader("Zbor");
        gridSosirePlecare.getColumns().forEach(col -> col.setAutoWidth(true));
        gridSosirePlecare.setItems(sosirePlecareService.findAll());
        gridSosirePlecare.asSingleSelect().addValueChangeListener(event -> {
            if(event.getValue() != null) {
                editDetails(event.getValue(), sosirePlecareForm);
            }
        });
    }
    public void editDetails(SosirePlecare sosirePlecare, SosirePlecareForm sosirePlecareForm){
        if(sosirePlecare == null) {
            remove(sosirePlecareForm);
        } else {
            add(sosirePlecareForm);
            sosirePlecareForm.setSosirePlecare(sosirePlecare);
        }
    }
}
