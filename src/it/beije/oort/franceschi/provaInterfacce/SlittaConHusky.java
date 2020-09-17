package it.beije.oort.franceschi.provaInterfacce;

import it.beije.oort.franceschi.provaInterfacce.interfacce.ISlitta;

public class SlittaConHusky extends Veicolo implements ISlitta {
	public final boolean canFly = true;

	@Override
	void frena() {
		System.out.println("Chiedi cordialmente ai cani di rallentare.");
		System.out.println("Woof! La slitta rallenta.");
	}

	@Override
	String getVeicolo() {
		return "Slitta trainata da Husky";
	}

	@Override
	public void accelera() {
		System.out.println("Sproni i cani ad accelerare.");
	}
}