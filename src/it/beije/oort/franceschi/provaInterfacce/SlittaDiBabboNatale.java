package it.beije.oort.franceschi.provaInterfacce;

import it.beije.oort.franceschi.provaInterfacce.interfacce.ISlitta;

public class SlittaDiBabboNatale extends Veicolo implements ISlitta {
    public final boolean canFly = true;

    @Override
    void frena() {
        System.out.println("Oh oh oh. La slitta rallenta.");
    }

    @Override
    String getVeicolo() {
        return "Slitta di Babbo Natale";
    }

    @Override
    public void accelera() {
        System.out.println("Sproni le renne ad accelerare.");
    }
}
