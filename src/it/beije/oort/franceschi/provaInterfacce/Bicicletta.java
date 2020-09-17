package it.beije.oort.franceschi.provaInterfacce;

public class Bicicletta extends Veicolo implements ICiclo{
	public void pedala() {
		System.out.println("Pedali e la " + getVeicolo() + " si muove.");
	}

	void frena() {
		System.out.println("Freni e la " + getVeicolo() + " si ferma.");
	}
	
	String getVeicolo() {
		return "Bicicletta";
	}
	
	boolean isStabile() {return ICiclo.isStabile();}
}
