package it.beije.oort.bassanelli.exercises.vehiclesdatabase;

public abstract class Bicycle implements Vehicle {
	
	public String color;

	public int getWheels() {
		return 2;
	}
	
	public String getColor() {
		return this.color;
	}
	

}
