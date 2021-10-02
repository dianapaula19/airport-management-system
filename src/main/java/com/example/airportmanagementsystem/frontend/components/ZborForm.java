package com.example.airportmanagementsystem.frontend.components;

import com.example.airportmanagementsystem.backend.models.Aeronava;
import com.example.airportmanagementsystem.backend.models.LinieAeriana;
import com.example.airportmanagementsystem.backend.models.Zbor;
import com.example.airportmanagementsystem.backend.service.ZborService;
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

public class ZborForm extends VerticalLayout {

    private ZborService zborService;
    private VerticalLayout mainViewLayout;
    private Integer idZbor;

    TextField codZbor = new TextField("Cod Zbor");

    ComboBox<LinieAeriana> liniiAeriene = new ComboBox<>("Linie Aeriana");
    ComboBox<Aeronava> aeronave = new ComboBox<>("Aeronava");

    Button save = new Button("Salvează");
    Button delete = new Button("Șterge");

    public ZborForm(ZborService zborService,
                    List<LinieAeriana> linieAerianaList,
                    List<Aeronava> aeronavaList,
                    VerticalLayout mainViewLayout
                    ) {
        this.mainViewLayout = mainViewLayout;
        this.zborService = zborService;
        liniiAeriene.setItems(linieAerianaList);
        liniiAeriene.setItemLabelGenerator(LinieAeriana::getDenumire);
        aeronave.setItems(aeronavaList);
        aeronave.setItemLabelGenerator(Aeronava::getDenumire);
        add(
                codZbor,
                liniiAeriene,
                aeronave,
                createButtonsLayout(mainViewLayout)

        );
    }

    private HorizontalLayout createButtonsLayout(VerticalLayout mainViewLayout) {

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);

        save.addClickShortcut(Key.ENTER);

        save.addClickListener(event -> {
            try {
                zborService.save(getZbor());
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
                zborService.delete(getZbor());
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

    private Zbor getZbor() {
        Zbor zbor = new Zbor(idZbor, Integer.parseInt(codZbor.getValue()), aeronave.getValue(), liniiAeriene.getValue());
        return zbor;
    }

    public void setZbor(Zbor zbor) {
        idZbor = zbor.getId();
        codZbor.setValue(String.valueOf(zbor.getCod_zbor()));
        liniiAeriene.setValue(zbor.getLinieAeriana());
        aeronave.setValue(zbor.getAeronava());
    }
}
