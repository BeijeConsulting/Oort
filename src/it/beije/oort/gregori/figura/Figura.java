package it.beije.oort.gregori.figura;

/**
 * stampare a video la seguente figura:
 * #
 * ##
 * ###
 * ####
 * magari indicando il numero di righe come parametro in ingresso
 * 
 * @author Luca Gregori
 *
 */
public class Figura {

	public static void main(String[] args) {
		
		int rows = Integer.parseInt(args[0]);
		
		for(int i = 1; i <= rows; i++) {
			for(int j = 0; j < i; j++) {
				System.out.print("#");
			}
			System.out.println();
		}
		
	}
}
