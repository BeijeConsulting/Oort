package it.beije.oort.bm.exercises.morracinese;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MorraCinese {
	
	private static final int SASSO = 0, CARTA = 1, FORBICE = 2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int p1, p2;
		while(true) {
			p2 = (int)(Math.random()*10%3);
			System.out.println("Pronto a giocare, digita \"Sasso\", \"Carta\" o \"Forbice\"... (\"exit\" per uscire)");
			String input = br.readLine();
			input = input.toLowerCase().trim();
			switch(input) {
				case "sasso":
					p1 = SASSO;
					break;
				case "carta":
					p1 = CARTA;
					break;
				case "forbice":
					p1 = FORBICE;
					break;
				case "exit":
					System.out.println("A presto!");
					System.exit(0);
				default:
					System.out.println("Non mi sembra una richiesta difficile...");
					continue;
			}
			System.out.println("Hai scelto: " + input);
			System.out.println("Computer sceglie: " + getName(p2));
			System.out.println(play(p1, p2));
		}

	}
	
	private static String play(int p1, int p2) {
		if((p1 + 1)%3 == p2) return "Hai perso!";
		else if(p1 == p2) return "Pareggio!";
		else return "Hai vinto!";
	}
	
	private static String getName(int i) {
		if(i==SASSO) return "sasso";
		else if(i==CARTA) return "carta";
		else return "forbice";
	}

}
