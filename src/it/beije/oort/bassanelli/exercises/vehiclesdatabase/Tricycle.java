package it.beije.oort.bassanelli.exercises.vehiclesdatabase;

public abstract class Tricycle implements Vehicle{
	

	public String color;
	public String fuel;

	public int getWheels() {
		return 3;
	}
	
	public String getColor() {
		return this.color;
	}
	
	public String getFuel() {
		return this.fuel;
	}
	
}
