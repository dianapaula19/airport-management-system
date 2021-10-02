package com.example.airportmanagementsystem.frontend;

import com.example.airportmanagementsystem.frontend.views.*;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route(value = "")
public class MainView extends AppLayout {

    public MainView() {
        addToNavbar(new DrawerToggle());
        createDrawer();
    }


    private void createDrawer() {
        RouterLink aeronavaLink = new RouterLink("Aeronave", AeronavaView.class);
        aeronavaLink.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink aeroportLink = new RouterLink("Aeroporturi", AeroportView.class);
        aeroportLink.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink angajatLink = new RouterLink("Angajati", AngajatView.class);
        angajatLink.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink departamentLink = new RouterLink("Departamente", DepartamentView.class);
        departamentLink.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink linieAerianaLink = new RouterLink("Linii Aeriene", LinieAerianaView.class);
        linieAerianaLink.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink magazinLink = new RouterLink("Magazine", MagazinView.class);
        magazinLink.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink orasLink = new RouterLink("Orase", OrasView.class);
        orasLink.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink sosirePlecareLink = new RouterLink("Sosire/Plecare", SosirePlecareView.class);
        sosirePlecareLink.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink zborLink = new RouterLink("Zboruri", ZborView.class);
        zborLink.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink cerereZboruriLink = new RouterLink("Zboruri Plecare/Sosire", ZboruriPlecareSosireView.class);
        cerereZboruriLink.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink cerereNumarLink = new RouterLink("Numar Angajati", NumarAngajati.class);
        cerereNumarLink.setHighlightCondition(HighlightConditions.sameLocation());
        addToDrawer(new VerticalLayout(
                aeronavaLink,
                aeroportLink,
                angajatLink,
                departamentLink,
                linieAerianaLink,
                magazinLink,
                orasLink,
                sosirePlecareLink,
                zborLink,
                cerereZboruriLink,
                cerereNumarLink));
    }


}
