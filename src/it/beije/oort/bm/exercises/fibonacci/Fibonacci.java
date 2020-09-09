package it.beije.oort.bm.exercises.fibonacci;

public class Fibonacci {

	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("Correct usage: java Fibonacci <num_elements>");
			System.exit(0);
		}
		int num_elements = Integer.parseInt(args[0]);
		long prec = 0;
		long last = 0;
		for(int i = 0; i < num_elements; i++) {
			if(prec == 0 && last == 0) {
				last = 1;
			}else if(prec == 0 && last == 1) {
				prec = 1;
			}else {
				long temp = last;
				last = last + prec;
				prec = temp;
			}
			System.out.print(last + " ");
		}

	}

}
