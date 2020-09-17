package it.beije.oort.lauria.motorizzazione;

public class ApeCar extends Tricicli {
	//Attributi
	private String colore = "";
	private String carburante = "";
	private String cilindrata = "";
	
	//Costruttore
	public ApeCar() {};
	
	public ApeCar(String colore, String carburante, String cilindrata) {
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

		ApeCar apeCar = new ApeCar("verde", "benzina", "50cc");
		
		
		System.out.println("Colore : "+apeCar.getColor());
		System.out.println("Numero di ruote : "+apeCar.getNumOfWheels());
		System.out.println("Tipo di carburante : "+apeCar.getTypwOfFuel());
		System.out.println("Può trasportare persone : "+(apeCar.CarriesPerson() ? "Sì":"No"));
	}

}
