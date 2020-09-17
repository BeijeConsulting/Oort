package it.beije.oort.bassanelli.exercises.vehiclesdatabase;

public class Apecar extends Tricycle {

	public Apecar() {
		
	}
	
	public Apecar(String color, String fuel) {
		this.color = color;
		this.fuel = fuel;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append(" - [ COLOR: ").append(this.color).append(" , FUEL: ").append(this.getFuel()).append(" , WHEELS: ")
				.append(this.getWheels()).append(" ]");
		return builder.toString();
	}
}
