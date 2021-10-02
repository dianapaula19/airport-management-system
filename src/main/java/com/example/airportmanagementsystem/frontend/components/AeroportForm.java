package com.example.airportmanagementsystem.frontend.components;

import com.example.airportmanagementsystem.backend.models.Aeroport;
import com.example.airportmanagementsystem.backend.models.Oras;
import com.example.airportmanagementsystem.backend.service.AeronavaService;
import com.example.airportmanagementsystem.backend.service.AeroportService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

public class AeroportForm extends VerticalLayout {

    private AeroportService aeroportService;
    private VerticalLayout mainViewLayout;
    private Integer idAeroport;

    TextField codAeroport = new TextField("Cod Aeroport");
    TextField denumire = new TextField("Denumire");

    ComboBox<Oras> orase = new ComboBox<>("Oras");

    Button save = new Button("Salvează");
    Button delete = new Button("Șterge");

    public AeroportForm(AeroportService aeroportService, List<Oras> orasList, VerticalLayout mainViewLayout) {
        this.aeroportService = aeroportService;
        this.mainViewLayout = mainViewLayout;
        orase.setItemLabelGenerator(Oras::getDenumireCompleta);
        orase.setItems(orasList);
        add(
                codAeroport,
                denumire,
                orase,
                createButtonsLayout(mainViewLayout)
        );
    }

    private HorizontalLayout createButtonsLayout(VerticalLayout mainViewLayout) {

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);

        save.addClickShortcut(Key.ENTER);

        save.addClickListener(event -> {
            try {
                aeroportService.save(getAeroport());
            } catch (DataIntegrityViolationException dataIntegrityViolationException) {
                mainViewLayout.add(new Text(dataIntegrityViolationException.getMessage()));
            } catch (ConstraintViolationException constraintViolationException) {
                mainViewLayout.add(new Text(constraintViolationException.getMessage()));
            } finally {
                UI.getCurrent().getPage().reload();
            }
        });
        delete.addClickListener(event -> {
            try {
                aeroportService.delete(getAeroport());
            } catch (DataIntegrityViolationException dataIntegrityViolationException) {
                mainViewLayout.add(new Text(dataIntegrityViolationException.getMessage()));
            } catch (ConstraintViolationException constraintViolationException) {
                mainViewLayout.add(new Text(constraintViolationException.getMessage()));
            } finally {
                UI.getCurrent().getPage().reload();
            }
        });
        return new HorizontalLayout(save, delete);
    }

    public Aeroport getAeroport() {
        return new Aeroport(idAeroport, codAeroport.getValue(), denumire.getValue(), orase.getValue());
    }

    public void setAeroport(Aeroport aeroport) {
        idAeroport = aeroport.getId();
        codAeroport.setValue(aeroport.getCod_aeroport());
        denumire.setValue(aeroport.getDenumire());
        orase.setValue(aeroport.getOras());
    }

}
