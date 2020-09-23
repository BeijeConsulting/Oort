package it.beije.oort.franceschi.provaInterfacce.interfacce;

public interface ICiclo {
    void pedala();

    static boolean isStabile() {
        return false;
    }
}