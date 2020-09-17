package it.beije.oort.lauria.motorizzazione;

public class Car extends Quadricicli {
	//Attributi
	private String colore = "";
	private String carburante = "";
	private String cilindrata = "";
	
	//Costruttore
	public Car() {};
	
	public Car(String colore, String carburante, String cilindrata) {
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

		Car macchina = new Car("azzurro", "benzina", "1200cc");
		
		
		System.out.println("Colore : "+macchina.getColor());
		System.out.println("Numero di ruote : "+macchina.getNumOfWheels());
		System.out.println("Tipo di carburante : "+macchina.getTypwOfFuel());
		System.out.println("Cilindrata : "+macchina.getCilindrata());
		System.out.println("Può trasportare persone : "+(macchina.CarriesPerson() ? "Sì":"No"));
	}
	
}
