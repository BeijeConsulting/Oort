package it.beije.oort.gregori.morracinese;

import java.util.Scanner;

public class MorraCinese {
	
	public void check(String s1, String s2) {
		switch(s1.toLowerCase()) {
			case "sasso":
				if(s2.equalsIgnoreCase("carta")) System.out.println(s2 + " vince!");
				else if(s2.equalsIgnoreCase("forbice")) System.out.println(s1 + " vince!");
				else if(s2.equalsIgnoreCase("sasso")) System.out.println("Pareggio!");
				break;
			case "carta":
				if(s2.equalsIgnoreCase("carta")) System.out.println("Pareggio!");
				else if(s2.equalsIgnoreCase("forbice")) System.out.println(s2 + " vince!");
				else if(s2.equalsIgnoreCase("sasso")) System.out.println(s1 + " vince!");
				break;
			case "forbice":
				if(s2.equalsIgnoreCase("carta")) System.out.println(s1 + " vince!");
				else if(s2.equalsIgnoreCase("forbice")) System.out.println("Pareggio!");
				else if(s2.equalsIgnoreCase("sasso")) System.out.println(s2 + " vince!");
				break;
			default:
				System.out.println("ERRORE: Valori inseriti non validi.");
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s1 = "";
		String s2 = "";
		char scelta = ' ';
		MorraCinese m = new MorraCinese();
		
		do {
			do {
				System.out.println("Giocatore1: sasso, carta, forbice?");
				s1 = sc.nextLine();
				if(!s1.equalsIgnoreCase("carta") && !s1.equalsIgnoreCase("forbice") && !s1.equalsIgnoreCase("sasso")) {
					System.out.println("ERRORE: valori inseriti non validi.");
				}
			} while(!s1.equalsIgnoreCase("carta") && !s1.equalsIgnoreCase("forbice") && !s1.equalsIgnoreCase("sasso"));	
			
			do {
				System.out.println("Giocatore2: sasso, carta, forbice?");
				s2 = sc.nextLine();
				if(!s2.equalsIgnoreCase("carta") && !s2.equalsIgnoreCase("forbice") && !s2.equalsIgnoreCase("sasso")) {
					System.out.println("ERRORE: valori inseriti non validi.");
				}
			} while(!s2.equalsIgnoreCase("carta") && !s2.equalsIgnoreCase("forbice") && !s2.equalsIgnoreCase("sasso"));	
			
			m.check(s1, s2);
			
			System.out.println("Vuoi giocare ancora? (Inserire 'n' per terminare)");
			scelta = sc.nextLine().charAt(0);
		} while(scelta != 'n');
		
		sc.close();	
	}

}
