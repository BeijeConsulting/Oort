package it.beije.oort.astratto.concessionaria;

public class Quad extends Quadricicli{
	
	public String getColor() {
		return "Blue / Rosso / Nero / Bianco / Grigio / Giallo";
	}
	
	public String getTypeOfFule() {
		return "Benzina";
	}

	public static void main(String[] args) {
		
		Quad quad = new Quad();
		System.out.println("Caratteristice dei Quad disponibili:");
		System.out.println("Ruote: " + quad.getNumberOfWheel());
		System.out.println("Colore: " + quad.getColor());
		System.out.println("Tipo di Carburante: " + quad.getTypeOfFule());
		

	}

}
