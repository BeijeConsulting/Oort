package it.beije.oort.lauria.motorizzazione;

public abstract class Quadricicli implements Veicolo{
	public int getNumOfWheels() {
		return 4;
	}
	
	public abstract String getTypwOfFuel();	
	public abstract String getColor();
	public abstract boolean CarriesPerson();
}