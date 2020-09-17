package it.beije.oort.veicoli.atmsoferici;

public class Elicottero extends VeicoloAtmosferico implements AdElica {
	private int lunghezzaEliche;
	private int dimensioneCabina;
	
	public int getLunghezzaEliche() {
		return lunghezzaEliche;
	}
	public void setLunghezzaEliche(int lunghezzaEliche) {
		this.lunghezzaEliche = lunghezzaEliche;
	}
	public int getDimensioneCabina() {
		return dimensioneCabina;
	}
	public void setDimensioneCabina(int dimensioneCabina) {
		this.dimensioneCabina = dimensioneCabina;
	}
	//...
	public int getVelocit‡DiRotazione() {
		int velocit‡ = 200;
		return velocit‡;
	}
	public void vola() {
		System.out.println("L'aereo sta volando");
	}
	public double getAltitudine() {
		// codice per altitudine
		double altitudine = 9000.54;
		return altitudine;
	}
}

