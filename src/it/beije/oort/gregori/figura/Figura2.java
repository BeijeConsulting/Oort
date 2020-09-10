package it.beije.oort.gregori.figura;

/**
 * stampare a video la seguente figura
 * ****
 * ***
 * **
 * *
 * 
 * @author Luca Gregori
 *
 */
public class Figura2 {

	public static void main(String[] args) {

		int rows = Integer.parseInt(args[0]);
		
		for(int i = rows; i >= 0; i--) {
			for(int j = 0; j < i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}

	}

}
