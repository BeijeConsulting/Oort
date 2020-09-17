package it.beije.oort.lauria.motorizzazione;

public class Bicicletta extends Biciclo {
	
	//Attributi
	private String colore = "";
	
	//Costruttore
	public Bicicletta() {};
	
	public Bicicletta(String colore) {
		this.colore = colore;
	}
	
	//Metodi
	@Override
	public String getColor() {
		return this.colore;
	}
	
	@Override
	public String getTypwOfFuel() {
		return "Una bicicletta non ha bisogno di carburante.";
	}

	@Override
	public boolean CarriesPerson() {
		return true;
	}
	
	
	@Override
	public String getCilindrata() {		
		return "Una bicicletta non ha cilindrata.";
	}
	
	
	public static void main(String[] args) {

		Bicicletta Bici = new Bicicletta("nera");
		
		
		System.out.println("Colore : "+Bici.getColor());
		System.out.println("Numero di ruote : "+Bici.getNumOfWheels());
		System.out.println("Tipo di carburante : "+Bici.getTypwOfFuel());
		System.out.println("Cilindrata : "+Bici.getCilindrata());
		System.out.println("Può trasportare persone : "+(Bici.CarriesPerson() ? "Sì":"No"));
	}


}
