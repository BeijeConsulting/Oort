package it.beije.oort.progetti;

public class Tabellina {

	public static void main(String[] args) {
		int val = Integer.parseInt(args[0]);
		for (int x = 1; x <= 10; x++) {
			System.out.print(val * x + " ");
		}
	}

}