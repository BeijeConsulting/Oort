package it.beije.oort.examples;

public class Singleton {

	private Singleton() {}
	
	private static Singleton s;
	
	public static Singleton getSingleton() {
		if (s == null) {
			s = new Singleton();
		}
		
		return s;
	}
	
}
