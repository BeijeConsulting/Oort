package it.beije.oort.veicoli.atmsoferici;

public class AereoPasseggeri extends VeicoloAtmosferico implements AMotore {
	public int postiPasseggeri;
	
	public int getPostiPasseggeri() {
		return postiPasseggeri;
	}
	public void setPostiPasseggeri(int postiPasseggeri) {
		this.postiPasseggeri = postiPasseggeri;
	}
	public void vola() {
		//... codice
		System.out.println("L'aereo sta volando");
	}
	public double getAltitudine() {
		// codice per altitudine
		int altitudine = 8000;
		return altitudine;
	}
}
