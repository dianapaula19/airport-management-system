package com.example.airportmanagementsystem.frontend.components;

import com.example.airportmanagementsystem.backend.models.Aeroport;
import com.example.airportmanagementsystem.backend.models.SosirePlecare;
import com.example.airportmanagementsystem.backend.models.Zbor;
import com.example.airportmanagementsystem.backend.service.SosirePlecareService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.Duration;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class SosirePlecareForm extends VerticalLayout {

    private SosirePlecareService sosirePlecareService;
    private VerticalLayout mainViewLayout;
    private SosirePlecare.SosirePlecareId idSosirePlecare;

    TextField poarta = new TextField("Poarta");
    DatePicker data = new DatePicker("Data");
    TimePicker timp = new TimePicker();

    Select<String> tip = new Select<>();

    ComboBox<Aeroport> aeroporturi = new ComboBox<>("Aeroport");
    ComboBox<Zbor> zboruri = new ComboBox<>("Zbor");

    Button save = new Button("Salvează");
    Button delete = new Button("Șterge");

    public SosirePlecareForm(SosirePlecareService sosirePlecareService,
                             List<Aeroport> aeroportList,
                             List<Zbor> zborList,
                             VerticalLayout mainViewLayout) {
        this.mainViewLayout = mainViewLayout;
        this.sosirePlecareService = sosirePlecareService;
        aeroporturi.setItems(aeroportList);
        aeroporturi.setItemLabelGenerator(Aeroport::getDenumire);
        zboruri.setItems(zborList);
        zboruri.setItemLabelGenerator(Zbor::getDenumire);
        timp.setStep(Duration.ofMinutes(1));
        tip.setLabel("Tip");
        tip.setItems("plecare", "sosire");
        add(
                poarta,
                data,
                timp,
                tip,
                aeroporturi,
                zboruri,
                createButtonsLayout(mainViewLayout)
        );

    }

    private HorizontalLayout createButtonsLayout(VerticalLayout mainViewLayout) {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        save.addClickShortcut(Key.ENTER);

        save.addClickListener(event -> {
            try {
                sosirePlecareService.save(getSosirePlecare());
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
                sosirePlecareService.delete(getSosirePlecare());
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

    public SosirePlecare getSosirePlecare() {
        int value;
        if(tip.getValue().equals("plecare")) {
            value = 0;
        } else {
            value = 1;
        }

        return new SosirePlecare(aeroporturi.getValue(),
                zboruri.getValue(),
                idSosirePlecare,
                Integer.parseInt(poarta.getValue()),
                Date.from(data.getValue().atTime(timp.getValue()).atZone(ZoneId.systemDefault()).toInstant()),
                value
        );
    }

    public void setSosirePlecare(SosirePlecare sosirePlecare) {
        idSosirePlecare = sosirePlecare.getId();
        poarta.setValue(String.valueOf(sosirePlecare.getPoarta()));
        if(sosirePlecare.getTip() == 0) {
            tip.setValue("plecare");
        } else if(sosirePlecare.getTip() == 1){
            tip.setValue("sosire");
        }
        data.setValue(sosirePlecare.getData().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        timp.setValue(sosirePlecare.getData().toInstant().atZone(ZoneId.systemDefault()).toLocalTime());
        aeroporturi.setValue(sosirePlecare.getAeroport());
        zboruri.setValue(sosirePlecare.getZbor());
    }


}
