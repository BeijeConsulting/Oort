package it.beije.oort.gregori.dummy;

/**
 * scrivere un programma che stampi le tabellina del numero dato come argomento
 * 
 * @author Luca Gregori
 *
 */
public class Tabellina {

	public static void main(String[] args) {
		
		int value = Integer.parseInt(args[0]);
		
		for(int i = 0; i < 11; i++) {
			System.out.println(value + " * " + i + " = " + (value * i));
		}
		
	}

}
