package it.beije.oort.astratto.concessionaria;

public class Apecar extends Tricicli {
	
	public String getColor() {
		return "Grigio / Verde";
	}
	
	public String getTypeOfFule() {
		return "Benzina";
	}

	public static void main(String[] args) {
	
		Apecar apecar = new Apecar();
		System.out.println("Caratteristice delle Apecar disponibili: ");
		System.out.println("-Ruote: " + apecar.getNumberOfWheel());
		System.out.println("-Colore: " + apecar.getColor());
		System.out.println("-Tipo di Carburante: " + apecar.getTypeOfFule());
	}

}
