package it.beije.oort.bm.exercises.figures;

public class ReverseTriangle {
	private static String symbol = "*";
	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("Correct usage: java ReverseTriangle <number of lines>");
			System.exit(0);
		}
		int lines = Integer.parseInt(args[0]);
		for(int i = 0; i<lines; i++) {
			for(int j = lines; j>i; j--) {
				System.out.print(symbol);
			}
			System.out.println();
		}
	}
}
