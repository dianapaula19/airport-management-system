package com.example.airportmanagementsystem.frontend.views;

import com.example.airportmanagementsystem.backend.models.Departament;
import com.example.airportmanagementsystem.backend.service.DepartamentService;
import com.example.airportmanagementsystem.frontend.MainView;
import com.example.airportmanagementsystem.frontend.components.DepartamentForm;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "departament", layout = MainView.class)
public class DepartamentView extends VerticalLayout {
    private DepartamentService departamentService;
    Grid<Departament> gridDepartament = new Grid<>(Departament.class);

    public DepartamentView(DepartamentService departamentService) {
        this.departamentService = departamentService;
        DepartamentForm departamentForm = new DepartamentForm(departamentService, this);
        configureGridDepartament(departamentForm);
        add(gridDepartament);
    }

    public void configureGridDepartament(DepartamentForm departamentForm) {
        gridDepartament.setColumns("id", "denumire");
        gridDepartament.setItems(departamentService.findAll());
        gridDepartament.getColumns().forEach(col -> col.setAutoWidth(true));
        gridDepartament.asSingleSelect().addValueChangeListener(event -> {
            if(event.getValue() != null) {
                editDetails(event.getValue(), departamentForm);
            }
        });
    }

    private void editDetails(Departament departament, DepartamentForm departamentForm) {
        if(departament == null) {
            remove(departamentForm);
        } else {
            add(departamentForm);
            departamentForm.setDepartament(departament);
        }
    }
}
