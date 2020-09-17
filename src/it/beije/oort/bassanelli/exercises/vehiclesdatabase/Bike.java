package it.beije.oort.bassanelli.exercises.vehiclesdatabase;

public class Bike extends Bicycle {

	public Bike() {

	}

	public Bike(String color) {
		this.color = color;
	}

	public String getFuel() {
		return null;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
		builder.append(" - [ COLOR: ").append(this.color).append(" , FUEL: ").append(this.getFuel()).append(" , WHEELS: ")
				.append(this.getWheels()).append(" ]");

		return builder.toString();
	}

}
