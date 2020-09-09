package it.beije.oort.bm.exercises.figures;

public class Triangle {
	private static String symbol = "#";
	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("Correct usage: java Triangle <number of lines>");
			System.exit(0);
		}
		int lines = Integer.parseInt(args[0]);
		for(int i = 1; i<=lines; i++) {
			for(int j = 0; j<i; j++) {
				System.out.print(symbol);
			}
			System.out.println();
		}
	}
}
