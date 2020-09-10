package it.beije.oort.gregori.figura;

/**
 * 
 * stampare a video la seguente figura:
 * 1      654321
 * 12      54321
 * 123      4321
 * 1234      321
 * 12345      21
 * 123456      1
 * 
 * @author Luca Gregori
 *
 */
public class Figura4 {

	public static void main(String[] args) {
		
		int value = Integer.parseInt(args[0]);
		
		for(int i = 1; i <= value; i++) {
			for(int k = 1; k <= i; k++) {
				System.out.print(k);
			}
			
			System.out.print(" ");
			
			for(int j = value - (i - 1); j > 0; j--) {
				System.out.print(j);
			}
			System.out.println();
		}

	}

}
