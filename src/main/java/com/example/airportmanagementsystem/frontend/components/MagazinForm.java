package com.example.airportmanagementsystem.frontend.components;

import com.example.airportmanagementsystem.backend.models.Aeroport;
import com.example.airportmanagementsystem.backend.models.Magazin;
import com.example.airportmanagementsystem.backend.service.MagazinService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class MagazinForm extends VerticalLayout {

    private MagazinService magazinService;
    private VerticalLayout mainViewLayout;
    private Integer idMagazin;

    TextField denumire = new TextField("Denumire");
    TextField etaj = new TextField("Etaj");
    TextField sala = new TextField("Sala");

    ComboBox<Aeroport> aeroporturi = new ComboBox<>("Aeroport");

    Button save = new Button("Salvează");
    Button delete = new Button("Șterge");

    public MagazinForm(MagazinService magazinService,
                       List<Aeroport> aeroportList,
                       VerticalLayout mainViewLayout) {
        this.mainViewLayout = mainViewLayout;
        this.magazinService = magazinService;
        aeroporturi.setItems(aeroportList);
        aeroporturi.setItemLabelGenerator(Aeroport::getDenumire);

        add(
                denumire,
                etaj,
                sala,
                aeroporturi,
                createButtonsLayout(mainViewLayout)
        );

    }

    private HorizontalLayout createButtonsLayout(VerticalLayout mainViewLayout) {

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);

        save.addClickShortcut(Key.ENTER);

        save.addClickListener(event -> {
            try {
                magazinService.save(getMagazin());
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
                magazinService.delete(getMagazin());
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

    public Magazin getMagazin() {
        return new Magazin(idMagazin, denumire.getValue(), Integer.parseInt(etaj.getValue()), Integer.parseInt(sala.getValue()), aeroporturi.getValue());
    }

    public void setMagazin(Magazin magazin) {
        idMagazin = magazin.getId();
        denumire.setValue(magazin.getDenumire());
        etaj.setValue(String.valueOf(magazin.getEtaj()));
        sala.setValue(String.valueOf(magazin.getSala()));
        aeroporturi.setValue(magazin.getAeroport());
    }
}
