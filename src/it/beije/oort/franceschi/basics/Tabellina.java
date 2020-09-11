package it.beije.oort.franceschi.basics;

public class Tabellina {

	public static void main(String[] args) {
		int val = Integer.parseInt(args[0]);
		for (int x = 1; x <= 10; x++) {
			System.out.print(val * x + " ");
		}
	}

}
