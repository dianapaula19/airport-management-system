package com.example.airportmanagementsystem.frontend.components;

import com.example.airportmanagementsystem.backend.models.Oras;
import com.example.airportmanagementsystem.backend.service.OrasService;
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

public class OrasForm extends VerticalLayout {

    private OrasService orasService;
    private VerticalLayout mainViewLayout;
    private Integer idOras;

    TextField denumire = new TextField("Denumire");
    TextField regiune = new TextField("Regiune");
    TextField tara = new TextField("Tara");

    Button save = new Button("Salvează");
    Button delete = new Button("Șterge");

    public OrasForm(OrasService orasService,
                    VerticalLayout mainViewLayout) {
        this.mainViewLayout = mainViewLayout;
        this.orasService = orasService;
        add(
                denumire,
                regiune,
                tara,
                createButtonsLayout(mainViewLayout)
        );
    }

    private HorizontalLayout createButtonsLayout(VerticalLayout mainViewLayout) {

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);

        save.addClickShortcut(Key.ENTER);

        save.addClickListener(event -> {
            try {
                orasService.save(getOras());
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
                orasService.delete(getOras());
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

    private Oras getOras() {
        return new Oras(idOras, denumire.getValue(), regiune.getValue(), tara.getValue());
    }

    public void setOras(Oras oras) {
        idOras = oras.getId();
        denumire.setValue(oras.getDenumire());
        regiune.setValue(oras.getRegiune());
        tara.setValue(oras.getTara());
    }
}
