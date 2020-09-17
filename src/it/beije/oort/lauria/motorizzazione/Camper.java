package it.beije.oort.lauria.motorizzazione;

public class Camper  extends Quadricicli {
	//Attributi
	private String colore = "";
	private String carburante = "";
	private String cilindrata = "";
	
	//Costruttore
	public Camper() {};
	
	public Camper(String colore, String carburante, String cilindrata) {
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

		Camper camper = new Camper("azzurro", "benzina", "2800cc");
		
		
		System.out.println("Colore : "+camper.getColor());
		System.out.println("Numero di ruote : "+camper.getNumOfWheels());
		System.out.println("Tipo di carburante : "+camper.getTypwOfFuel());
		System.out.println("Cilindrata : "+camper.getCilindrata());
		System.out.println("Può trasportare persone : "+(camper.CarriesPerson() ? "Sì":"No"));
	}
}