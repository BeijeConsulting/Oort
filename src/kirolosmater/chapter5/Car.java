package kirolosmater.chapter5;

public abstract class Car extends Vehicle{
	@Override
	boolean transporstPeople() {
		// TODO Auto-generated method stub
		return true;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
