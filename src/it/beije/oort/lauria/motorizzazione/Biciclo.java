package it.beije.oort.lauria.motorizzazione;

public abstract class Biciclo implements Veicolo {
	public int getNumOfWheels() {
		return 2;
	}
	
	public abstract String getTypwOfFuel();	
	public abstract String getColor();
	public abstract boolean CarriesPerson();
}
