package it.beije.oort.kirolosmater;

public class DoWhile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length == 0) {
			System.out.println("Non hai inserito nulla");
		} else {
			int i = 0;
			do {
				System.out.println(args[i]);
				i++;
			} while(i < args.length);
		}
	}

}
