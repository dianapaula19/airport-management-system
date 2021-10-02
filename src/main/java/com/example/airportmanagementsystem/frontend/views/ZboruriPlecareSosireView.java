package com.example.airportmanagementsystem.frontend.views;

import com.example.airportmanagementsystem.backend.models.Aeroport;
import com.example.airportmanagementsystem.backend.models.Zbor;
import com.example.airportmanagementsystem.backend.service.AeroportService;
import com.example.airportmanagementsystem.frontend.MainView;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.Route;

@Route(value = "query_zboruri", layout = MainView.class)
public class ZboruriPlecareSosireView extends VerticalLayout {
    private AeroportService aeroportService;
    ComboBox<Aeroport> aeroportComboBox = new ComboBox<>();
    Select<String> selectTip = new Select<>();
    Grid<Zbor> zborGrid = new Grid<>(Zbor.class);

    public ZboruriPlecareSosireView(AeroportService aeroportService) {
        this.aeroportService = aeroportService;
        VerticalLayout zboruriAeroportLayout = new VerticalLayout();
        VerticalLayout aeroporturiMinDoi = new VerticalLayout();
        configureAeroportComboBox();
        configureZborGrid();
        add(aeroportComboBox);

    }
    public void configureAeroportComboBox() {
        aeroportComboBox.setItemLabelGenerator(Aeroport::getDenumire);
        aeroportComboBox.setPlaceholder("Aeroport");
        aeroportComboBox.setItems(aeroportService.findAll());
        aeroportComboBox.addValueChangeListener(event -> {
            if(event != null) {
                remove(selectTip);
                remove(zborGrid);
                configureSelectTip(event.getValue());
                add(selectTip);
            }
        });
    }

    public void configureZborGrid() {
        zborGrid.setColumns("id");
        zborGrid.addColumn(zbor -> zbor == null ? "-" : zbor.getDenumire()).setHeader("Zbor");
        zborGrid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    public void configureSelectTip(Aeroport aeroport) {
        selectTip.setLabel("Tip");
        selectTip.setItems("Plecare", "Sosire");
        selectTip.addValueChangeListener(event -> {
            if(event.getValue() != null) {
                remove(zborGrid);
                if (event.getValue().equals("Plecare")) {
                    zborGrid.setItems(aeroportService.findZboruri(0, aeroport.getDenumire()));
                }
                if (event.getValue().equals("Sosire")) {
                    zborGrid.setItems(aeroportService.findZboruri(1, aeroport.getDenumire()));
                }
                add(zborGrid);

            }
        });
    }

}
