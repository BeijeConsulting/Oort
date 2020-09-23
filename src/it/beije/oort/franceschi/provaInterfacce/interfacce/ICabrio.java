package it.beije.oort.franceschi.provaInterfacce.interfacce;

public interface ICabrio {
    default void tiraGiuLaCapote() {
        System.out.println("Tiri giù la capote. Ti riempi di moscerini.");
    }
}
