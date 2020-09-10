package it.beije.oort.progetti;
import java.util.Scanner;

public class RockPaperScissors {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Giocatore 1: ");
		String mano1 = sc.next();
		System.out.print("Giocatore 2: ");
		String mano2 = sc.next();
		sc.close();
		
		play(mano1.toUpperCase(), mano2.toUpperCase());
	}
	
	public static void beats(String mano1, String mano2) {
		if (mano1.equals("S") && mano2.equals("F") || mano1.equals("F") && mano2.equals("S")) {
			System.out.println("Sasso vince su Forbici.");
		} else if (mano1.equals("S") && mano2.equals("C") || mano1.equals("C") && mano2.equals("S")) {
			System.out.println("Carta vince su Sasso.");
		} else if (mano1.equals("C") && mano2.equals("F") || mano1.equals("F") && mano2.equals("C")) {
			System.out.println("Forbici vince su Carta.");
		} else {
			System.out.println("Errore random.");
		}
	}
	
	public static boolean validate(String s) {
		if (s.equals("S") || s.equals("C")  || s.equals("F") ) return true; else return false;
	}
	
	public static void play(String mano1, String mano2) {		
		if (validate(mano1) && validate(mano2)) {
			if (mano1.equals(mano2)) System.out.println("Pareggio!"); else beats(mano1, mano2);
		} else System.out.println("Input non valido.");
	}
}
