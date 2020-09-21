package it.beije.oort.bassanelli.exercises.vehiclesdatabase;

public class Runner {

	public static void main(String[] args) {
			
		Scooter scooter = new Scooter("red", "gasoline");
		Bike bike = new Bike("white");
		Apecar apecar = new Apecar("yellow", "diesel");
		Car car = new Car("black", "gpl");
			
		System.out.println(scooter.toString());
		System.out.println(bike.toString());
		System.out.println(apecar.toString());
		System.out.println(car.toString());	
	}
}