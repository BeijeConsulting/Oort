package it.beije.oort.bm.exercises.tabellina;

public class Tabellina {
	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("Correct usage: java Tabellina <number 1-9>");
			System.exit(0);
		}
		int number = Integer.parseInt(args[0]);
		if(number <= 0 || number > 9) {
			System.out.println("Non fare il bambino cattivo, ho detto un numero tra 1 e 9.");
			System.exit(0);
		}
		for(int i = 1; i<=10; i++) {
			System.out.println(number + " * " + i + " fa: " + number*i);
		}
	}
}
