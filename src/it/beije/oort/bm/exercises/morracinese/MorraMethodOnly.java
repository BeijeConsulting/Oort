package it.beije.oort.bm.exercises.morracinese;

import java.util.Arrays;
import java.util.List;

public class MorraMethodOnly {
	
	private static final String SASSO = "SASSO", CARTA = "CARTA", FORBICE = "FORBICE";
	
	public static void main(String[] args) {
		if(args.length != 2) {
			System.out.println("Correct usage: java MorraMethodOnly <sasso|carta|forbice> <sasso|carta|forbice>");
			System.exit(0);
		}
		System.out.println(play(args[0].toUpperCase(),args[1].toUpperCase()));

	}

	private static String play(String p1, String p2) {
		List<String> scf = Arrays.asList(SASSO, CARTA, FORBICE);
		if(!scf.contains(p1) || !scf.contains(p2)) return "Paramentro non supportato";
		if(p1.equals(p2)) return "Pareggio";
		String winner = "", looser = "";
		switch(p1) {
			case SASSO:
				if(p2.contentEquals(CARTA)) {
					winner = CARTA;
					looser = SASSO;
				}
				else {
					winner = SASSO; 
					looser = FORBICE;
				}
				break;
			case CARTA:
				if(p2.contentEquals(FORBICE)) {
					winner = FORBICE;
					looser = CARTA;
				}
				else {
					winner = CARTA; 
					looser = SASSO;
				}
				break;
			case FORBICE:
				if(p2.contentEquals(SASSO)) {
					winner = SASSO;
					looser = FORBICE;
				}
				else {
					winner = FORBICE; 
					looser = CARTA;
				}
				break;
		}
		return winner + " vince su " + looser;
	}

}
