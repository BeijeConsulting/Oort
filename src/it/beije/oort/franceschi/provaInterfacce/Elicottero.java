package it.beije.oort.franceschi.provaInterfacce;

public class Elicottero extends Velivolo {

    @Override
    public void avviaMotore() {
        System.out.println("Le pale iniziano a girare.");
    }

    @Override
    public void fermaMotore() {
        System.out.println("Le pale si fermano");
    }

    @Override
    void frena() {
        System.out.println("Rallenti l'elicottero.");
    }

    @Override
    String getVeicolo() {
        return "Elicottero";
    }

}
