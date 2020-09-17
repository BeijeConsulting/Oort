package it.beije.oort.lauria.motorizzazione;

public class Motorcycle extends Biciclo{
	//Attributi
	private String colore = "";
	private String carburante = "";
	private String cilindrata = "";
	
	//Costruttore
	public Motorcycle() {};
	
	public Motorcycle(String colore, String carburante, String cilindrata) {
		this.colore = colore;
		this.carburante = carburante;
		this.cilindrata = cilindrata;
	}
	
	//Metodi
	@Override
	public String getColor() {
		return this.colore;
	}
	
	@Override
	public String getTypwOfFuel() {
		return this.carburante;
	}

	@Override
	public boolean CarriesPerson() {
		return true;
	}
	
		@Override
	public String getCilindrata() {
		return this.cilindrata;
	}
	
	
	
	public static void main(String[] args) {

		Motorcycle moto = new Motorcycle("bianca", "benzina", "200cc");
		
		
		System.out.println("Colore : "+moto.getColor());
		System.out.println("Numero di ruote : "+moto.getNumOfWheels());
		System.out.println("Tipo di carburante : "+moto.getTypwOfFuel());
		System.out.println("Cilindrata : "+moto.getCilindrata());
		System.out.println("Può trasportare persone : "+(moto.CarriesPerson() ? "Sì":"No"));
	}


}
