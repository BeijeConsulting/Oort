package it.beije.oort.lauria.motorizzazione;

public abstract class Tricicli implements Veicolo{
	public int getNumOfWheels() {
		return 3;
	}
	
	public abstract String getTypwOfFuel();	
	public abstract String getColor();
	public abstract boolean CarriesPerson();
}
