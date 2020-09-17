package it.beije.oort.veicoli.atmsoferici;

public class Aliante extends VeicoloAtmosferico implements SenzaMotore {
	public void vola() {
		//... codice
		System.out.println("L'aliante sta volando");
	}
	public double getAltitudine() {
		// codice per altitudine
		int altitudine = 8000;
		return altitudine;
	}
}
