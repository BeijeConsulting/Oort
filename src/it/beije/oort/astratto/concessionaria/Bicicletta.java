package it.beije.oort.astratto.concessionaria;

public class Bicicletta extends Bicicli {

	
	public String getColor() {
		return "Bianco";
	}

	
	public String getTypeOfFule() {
		return "La bici non utilizza carburante!";
	}


	

	public static void main(String[] args) {
		Bicicletta bici = new Bicicletta();
		System.out.println("Caratteristice delle Biciclette disponibili:");
		System.out.println("-Ruote: " + bici.getNumberOfWheel());
		System.out.println("-Colore: " + bici.getColor());
		System.out.println("-Tipo di Carburante: " + bici.getTypeOfFule());
		
	}
}
