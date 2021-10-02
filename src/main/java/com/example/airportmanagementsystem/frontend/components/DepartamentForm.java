package com.example.airportmanagementsystem.frontend.components;

import com.example.airportmanagementsystem.backend.models.Departament;
import com.example.airportmanagementsystem.backend.service.DepartamentService;
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


public class DepartamentForm extends VerticalLayout {

    private DepartamentService departamentService;
    private VerticalLayout mainViewLayout;
    private Integer idDepartament;

    TextField denumire = new TextField("Denumire");

    Button save = new Button("Salvează");
    Button delete = new Button("Șterge");

    public DepartamentForm(DepartamentService departamentService,
                           VerticalLayout mainViewLayout) {
        this.mainViewLayout = mainViewLayout;
        this.departamentService = departamentService;
        add(
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
                departamentService.save(getDepartament());
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
                departamentService.delete(getDepartament());
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

    private Departament getDepartament() {
        return new Departament(idDepartament, denumire.getValue());
    }

    public void setDepartament(Departament departament) {
        idDepartament = departament.getId();
        denumire.setValue(departament.getDenumire());
    }
}
