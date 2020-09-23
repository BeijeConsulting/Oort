package it.beije.oort.franceschi.provaInterfacce;

import it.beije.oort.franceschi.provaInterfacce.interfacce.IBenzina;
import it.beije.oort.franceschi.provaInterfacce.interfacce.IMotore;
import it.beije.oort.franceschi.provaInterfacce.interfacce.IQuattroRuote;

public abstract class Automobile extends Veicolo implements IMotore, IQuattroRuote, IBenzina {
    public final boolean canFly = false;

    public static int getNumeroRuote() {
        return IQuattroRuote.getNumeroRuote();
    }

    public String getTipoCarburante() {
        return IBenzina.tipoCarburante;
    }

    abstract String getModel();
}
