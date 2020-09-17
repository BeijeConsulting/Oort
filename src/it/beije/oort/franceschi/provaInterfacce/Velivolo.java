package it.beije.oort.franceschi.provaInterfacce;

import it.beije.oort.franceschi.provaInterfacce.interfacce.IAvgas;
import it.beije.oort.franceschi.provaInterfacce.interfacce.IMotore;

public abstract class Velivolo extends Veicolo implements IMotore, IAvgas {
	public final boolean canFly = true;

	@Override
	public String getTipoCarburante() {
		return getTipoCarburante();
	}
}
