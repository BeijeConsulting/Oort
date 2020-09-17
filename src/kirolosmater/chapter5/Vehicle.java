package kirolosmater.chapter5;

public abstract class Vehicle implements CanFly, CanSail, CanSubmerge, CanTravelByLand{
	int speed;
	int cargo;
	boolean hasWheels;
	boolean hasWings;
	abstract boolean transporstPeople();
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getCargo() {
		return cargo;
	}
	public void setCargo(int cargo) {
		this.cargo = cargo;
	}
}
