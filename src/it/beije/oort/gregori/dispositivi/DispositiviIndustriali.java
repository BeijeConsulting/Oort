package it.beije.oort.gregori.dispositivi;

public abstract class DispositiviIndustriali extends DispositiviElettronici {
	
	private double weight;
	private boolean needsManteinance;

	public boolean isNeedsManteinance() {
		return needsManteinance;
	}

	public void setNeedsManteinance(boolean needsManteinance) {
		this.needsManteinance = needsManteinance;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public double getWeight() {
		return weight;
	}

	public final String getType() {
		return "Industrial";
	}
}
