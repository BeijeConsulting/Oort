package it.beije.oort.franceschi.basics;

public class DisegnoDue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int x = Integer.parseInt(args[0]);
		System.out.println("Righe: " + x);
		
		for (int k = x; k > 0; k--) {
			for (int z = k; z > 0; z--) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

}