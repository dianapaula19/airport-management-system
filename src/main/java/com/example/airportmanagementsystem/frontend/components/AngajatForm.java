package com.example.airportmanagementsystem.frontend.components;

import com.example.airportmanagementsystem.backend.models.Aeroport;
import com.example.airportmanagementsystem.backend.models.Angajat;
import com.example.airportmanagementsystem.backend.models.Departament;
import com.example.airportmanagementsystem.backend.service.AngajatService;
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
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

import java.sql.SQLException;
import java.util.List;


public class AngajatForm extends VerticalLayout {

    private AngajatService angajatService;
    private VerticalLayout mainViewLayout;

    TextField nume = new TextField("Nume");
    TextField prenume = new TextField("Prenume");
    TextField email = new TextField("Email");
    TextField numarTelefon = new TextField("Numar de telefon");
    TextField varsta = new TextField("Varsta");

    Integer idAngajat;

    ComboBox<Departament> departamente = new ComboBox<>("Departament");
    ComboBox<Aeroport> aeroporturi = new ComboBox<>("Aeroport");

    Button save = new Button("Salvează");
    Button delete = new Button("Șterge");

    public AngajatForm(AngajatService angajatService,
                       List<Departament> departamentList,
                       List<Aeroport> aeroportList,
                       VerticalLayout mainViewLayout) {
        this.mainViewLayout = mainViewLayout;
        this.angajatService = angajatService;
        departamente.setItems(departamentList);
        departamente.setItemLabelGenerator(Departament::getDenumire);
        aeroporturi.setItems(aeroportList);
        aeroporturi.setItemLabelGenerator(Aeroport::getDenumire);
        add(
                nume,
                prenume,
                email,
                numarTelefon,
                varsta,
                departamente,
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
                angajatService.save(getAngajat());
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
                angajatService.delete(getAngajat());
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

    public void setAngajat(Angajat angajat) {

        idAngajat = angajat.getId();
        nume.setValue(angajat.getNume());
        prenume.setValue(angajat.getPrenume());
        email.setValue(angajat.getEmail());
        numarTelefon.setValue(angajat.getNumar_telefon());
        varsta.setValue(String.valueOf(angajat.getVarsta()));
        departamente.setValue(angajat.getDepartament());
        aeroporturi.setValue(angajat.getAeroport());

    }

    public Angajat getAngajat() {
        Angajat angajat = new Angajat(
                idAngajat,
                nume.getValue(),
                prenume.getValue(),
                email.getValue(),
                numarTelefon.getValue(),
                Integer.parseInt(varsta.getValue()),
                aeroporturi.getValue(),
                departamente.getValue()
                );
        return angajat;
    }

}
