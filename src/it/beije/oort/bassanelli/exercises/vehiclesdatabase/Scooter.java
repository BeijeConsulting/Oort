package it.beije.oort.bassanelli.exercises.vehiclesdatabase;

public class Scooter extends Bicycle {

	public String fuel;
	
	public Scooter() {

	}

	public Scooter(String color, String fuel) {
		this.color = color;
		this.fuel = fuel;
	}

	public String getFuel() {
		return this.fuel;

	}

	public String toString() {
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append(" - [ COLOR: ").append(this.color).append(" , FUEL: ").append(this.fuel).append(" , WHEELS: ")
				.append(this.getWheels()).append(" ]");

		return builder.toString();
	}
}
