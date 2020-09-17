package it.beije.oort.astratto.concessionaria;

public class ScooterTreRuote extends Tricicli {
	
	public String getColor() {
		return "Blue / Rosso";
	}
	
	public String getTypeOfFule() {
		return "Benzina/ Diesel";
	}


	public static void main(String[] args) {
		
		ScooterTreRuote s3r = new ScooterTreRuote();
		System.out.println("Caratteristice degli Scooter (3 ruote) disponibili:");
		System.out.println("-Ruote: " + s3r.getNumberOfWheel());
		System.out.println("-Colore: " + s3r.getColor());
		System.out.println("-Tipo di Carburante: " + s3r.getTypeOfFule());
		

	}

}
