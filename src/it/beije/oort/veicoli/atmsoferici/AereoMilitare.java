package it.beije.oort.veicoli.atmsoferici;

public class AereoMilitare extends VeicoloAtmosferico implements AMotore {
	public void vola() {
		System.out.println("L'aereo sta volando");
	}
	public double getAltitudine() {
		// codice per altitudine
		double altitudine = 9000.54;
		return altitudine;
	}
}
