package com.example.airportmanagementsystem.frontend.views;

import com.example.airportmanagementsystem.backend.models.Aeroport;
import com.example.airportmanagementsystem.backend.models.Angajat;
import com.example.airportmanagementsystem.backend.models.Departament;
import com.example.airportmanagementsystem.backend.service.AeroportService;
import com.example.airportmanagementsystem.backend.service.AngajatService;
import com.example.airportmanagementsystem.backend.service.DepartamentService;
import com.example.airportmanagementsystem.frontend.MainView;
import com.example.airportmanagementsystem.frontend.components.AngajatForm;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "angajat", layout = MainView.class)
public class AngajatView extends VerticalLayout {
    private AngajatService angajatService;
    private DepartamentService departamentService;
    private AeroportService aeroportService;
    Grid<Angajat> gridAngajat = new Grid<>(Angajat.class);

    public AngajatView(AngajatService angajatService,
                       DepartamentService departamentService,
                       AeroportService aeroportService) {
        this.angajatService = angajatService;
        this.departamentService = departamentService;
        this.aeroportService = aeroportService;
        AngajatForm angajatForm = new AngajatForm(angajatService,
                departamentService.findAll(),
                aeroportService.findAll(),
                this);

        configureGridAngajat(angajatForm);
        add(gridAngajat);
    }

    public void configureGridAngajat(AngajatForm angajatForm) {

        gridAngajat.setColumns("id", "nume", "prenume", "email", "numar_telefon", "varsta");
        gridAngajat.addColumn(angajat -> {
            Departament departament = angajat.getDepartament();
            return departament == null ? "-" : departament.getDenumire();
        }).setHeader("Departament");
        gridAngajat.addColumn(angajat -> {
            Aeroport aeroport = angajat.getAeroport();
            return aeroport == null ? "-" : aeroport.getDenumire();
        }).setHeader("Aeroport");
        gridAngajat.getColumns().forEach(col -> col.setAutoWidth(true));
        gridAngajat.setItems(angajatService.findAll());

        gridAngajat.asSingleSelect().addValueChangeListener(event -> {
            if(event.getValue() != null) {
                editDetails(event.getValue(), angajatForm);
            }
        });
    }
    public void editDetails(Angajat angajat, AngajatForm angajatForm){
        if(angajat == null) {
            remove(angajatForm);
        } else {
            add(angajatForm);
            angajatForm.setAngajat(angajat);
        }
    }

}
