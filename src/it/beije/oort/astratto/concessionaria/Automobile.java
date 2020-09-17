package it.beije.oort.astratto.concessionaria;

public class Automobile extends Quadricicli {
	
	public String getColor() {
		return "Blue / Rosso / Nero / Bianco / Grigio";
	}
	
	public String getTypeOfFule() {
		return "Benzina / Diesel / GPL / Metano / Hybrid";
	}

	public static void main(String[] args) {
		
		Automobile auto = new Automobile();
		System.out.println("Caratteristice delle Automobili disponibili:");
		System.out.println("Ruote: " + auto.getNumberOfWheel());
		System.out.println("Colore: " + auto.getColor());
		System.out.println("Tipo di Carburante: " + auto.getTypeOfFule());
	}

}
