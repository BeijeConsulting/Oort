package it.beije.oort.astratto.concessionaria;

public class Scooter extends Bicicli{
	
	public String getColor() {
		return "Verde";
	}

	
	public String getTypeOfFule() {
		return "Benzina";
	}

	public static void main(String[] args) {
		
		Scooter scooter = new Scooter();
		System.out.println("Caratteristice degli Scooter disponibili:");
		System.out.println("-Ruote: " + scooter.getNumberOfWheel());
		System.out.println("-Colore: " + scooter.getColor());
		System.out.println("-Tipo di Carburante: " + scooter.getTypeOfFule());
		
	}
}
