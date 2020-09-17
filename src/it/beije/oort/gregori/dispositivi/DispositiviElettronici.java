package it.beije.oort.gregori.dispositivi;

public abstract class DispositiviElettronici {

	private String marca;
	private String modello;
	private double consumption;
	private double cost;
	
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModello() {
		return modello;
	}
	public void setModello(String modello) {
		this.modello = modello;
	}
	public double getConsumption() {
		return consumption;
	}
	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public abstract String getType();	
	public abstract String toString();
}
