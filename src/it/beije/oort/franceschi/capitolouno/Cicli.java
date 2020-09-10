package it.beije.oort.franceschi.capitolouno;

public class Cicli {

	public static void main(String[] args) {
		System.out.println("ForEach");
		for (String s : args) {
			System.out.print(s + "\t");
		}
		
		System.out.println();
		System.out.println();
		System.out.println("For");
		for (int i = 0; i<args.length; i++) {
			System.out.print(args[i] + "\t");
		}
		
		System.out.println();
		System.out.println();
		System.out.println("While");
		int whileCounter = 0;
		while(whileCounter < args.length) {
			System.out.print(args[whileCounter] + "\t");
			whileCounter++;
		}
		
		
		System.out.println();
		System.out.println();
		System.out.println("Do-While");
		int doCounter = 0;
		do {
			System.out.print(args[doCounter] + "\t");
			doCounter++;
		} while (doCounter < args.length);
		
		
		System.out.println();
		System.out.println();
		System.out.println("Solo pari");
		for (int k = 0; k<args.length; k++)
			if (k % 2 == 0) {
				System.out.print(args[k] + "\t");
			}
	}
}
