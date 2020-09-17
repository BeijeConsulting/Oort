package it.beije.oort.gregori.dispositivi;

public abstract class Manuale extends DispositiviIndustriali {

	private int numPeople;
	private int averageWeight = 75;
	
	@Override
	public double getWeight() {
		return super.getWeight() * numPeople * averageWeight;
	}

	@Override
	public String toString() {
		
		return null;
	}

}
