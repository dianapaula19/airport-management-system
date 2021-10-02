package com.example.airportmanagementsystem.frontend.components;

import com.example.airportmanagementsystem.backend.models.LinieAeriana;
import com.example.airportmanagementsystem.backend.service.LinieAerianaService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;

public class LinieAerianaForm extends VerticalLayout {

    private LinieAerianaService linieAerianaService;
    private VerticalLayout mainViewLayout;
    private Integer idLinieAeriana;

    TextField codLinieAeriana = new TextField("Cod Linie Aeriana");
    TextField denumire = new TextField("Denumire");

    Button save = new Button("Salvează");
    Button delete = new Button("Șterge");

    public LinieAerianaForm(LinieAerianaService linieAerianaService,
                            VerticalLayout mainViewLayout) {

        this.mainViewLayout = mainViewLayout;
        this.linieAerianaService = linieAerianaService;
        add(
                codLinieAeriana,
                denumire,
                createButtonsLayout(mainViewLayout)
        );
    }

    private HorizontalLayout createButtonsLayout(VerticalLayout mainViewLayout) {

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);

        save.addClickShortcut(Key.ENTER);
        save.addClickListener(event -> {
            try {
                linieAerianaService.save(getLinieAeriana());
            } catch (DataIntegrityViolationException dataIntegrityViolationException) {
                mainViewLayout.add(new Text(dataIntegrityViolationException.getMessage()));
            } catch (ConstraintViolationException constraintViolationException) {
                mainViewLayout.add(new Text(constraintViolationException.getMessage()));
            } catch (JpaSystemException jpaSystemException) {
                mainViewLayout.add(new Text(jpaSystemException.getMessage()));
            } finally {
                UI.getCurrent().getPage().reload();
            }
        });
        delete.addClickListener(event -> {
            try {
                linieAerianaService.delete(getLinieAeriana());
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

    private LinieAeriana getLinieAeriana() {
        return new LinieAeriana(idLinieAeriana, codLinieAeriana.getValue(), denumire.getValue());
    }

    public void setLinieAeriana(LinieAeriana linieAeriana) {
        idLinieAeriana = linieAeriana.getId();
        codLinieAeriana.setValue(linieAeriana.getCod_linie_aeriana());
        denumire.setValue(linieAeriana.getDenumire());
    }
}
