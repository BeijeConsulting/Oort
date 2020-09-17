package it.beije.oort.franceschi.provaInterfacce;

public class Aereo extends Velivolo {

	@Override
	public void avviaMotore() {
		System.out.println("Avvii i motori.");
	}

	@Override
	public void fermaMotore() {
		System.out.println("Fermi i motori");
	}

	@Override
	void frena() {
		System.out.println("Alzi i flap.");
	}

	@Override
	String getVeicolo() {
		return "Aereo";
	}

}
