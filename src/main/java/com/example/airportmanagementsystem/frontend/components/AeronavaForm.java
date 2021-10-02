package com.example.airportmanagementsystem.frontend.components;

import com.example.airportmanagementsystem.backend.models.Aeronava;
import com.example.airportmanagementsystem.backend.models.Angajat;
import com.example.airportmanagementsystem.backend.service.AeronavaService;
import com.example.airportmanagementsystem.backend.service.AeroportService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;



public class AeronavaForm extends VerticalLayout {

    private AeronavaService aeronavaService;
    private VerticalLayout mainViewLayout;

    Integer idAeronava;

    TextField denumire = new TextField("Denumire");
    TextField capacitate = new TextField("Capacitate");

    Button save = new Button("Salvează");
    Button delete = new Button("Șterge");

    public AeronavaForm(AeronavaService aeronavaService, VerticalLayout mainViewLayout) {
        this.mainViewLayout = mainViewLayout;
        this.aeronavaService = aeronavaService;
        add(
                denumire,
                capacitate,
                createButtonsLayout(mainViewLayout)
        );
    }

    private HorizontalLayout createButtonsLayout(VerticalLayout mainViewLayout) {

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);

        save.addClickShortcut(Key.ENTER);

        save.addClickListener(event ->  {
            saveFunction(mainViewLayout);
        });

        delete.addClickListener(event -> {

            deleteFunction(mainViewLayout);

        });

        return new HorizontalLayout(save, delete);
    }

    private void saveFunction(VerticalLayout mainViewLayout)  {
        try {
            aeronavaService.save(getAeronava());
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            mainViewLayout.add(new Text(dataIntegrityViolationException.getMessage()));
        } catch (ConstraintViolationException constraintViolationException) {
            mainViewLayout.add(new Text(constraintViolationException.getMessage()));
        } finally {
            UI.getCurrent().getPage().reload();
        }

    }

    private void deleteFunction(VerticalLayout mainViewLayout) {
        try {
            aeronavaService.delete(getAeronava());
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            mainViewLayout.add(new Text(dataIntegrityViolationException.getMessage()));
        } catch (ConstraintViolationException constraintViolationException) {
            mainViewLayout.add(new Text(constraintViolationException.getMessage()));
        } finally {
            UI.getCurrent().getPage().reload();
        }

    }

    public void setAeronava(Aeronava aeronava) {

        idAeronava = aeronava.getId();
        denumire.setValue(aeronava.getDenumire());
        capacitate.setValue(String.valueOf(aeronava.getCapacitate()));

    }

    public Aeronava getAeronava() {
        Aeronava aeronava = new Aeronava(
                idAeronava,
                denumire.getValue(),
                Integer.parseInt(capacitate.getValue())
        );
        return aeronava;
    }





}
