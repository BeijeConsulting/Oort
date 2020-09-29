package it.beije.oort.file.sala.rubrica;

import java.util.Random;

public class GeneraMail {
	public static String generaMail(String nome, String cognome, String dominio) {
		StringBuilder email = new StringBuilder();
		Random r = new Random();
		int rand = r.nextInt(10);
		boolean usaNome = (r.nextInt(5) == 1) ? false : true;
		boolean usaCognome = (r.nextInt(3) == 1) ? false : true;
		
		if (!usaNome && !usaCognome) {
			for(int i=0;i<r.nextInt(15)+6;i++) {
				email.append(r.nextInt(10));
			}
			email.append("@"+dominio);
		} else if (!usaNome) {
			if (rand != 1) email.append(removeSpace(cognome) + r.nextInt(10) +
					r.nextInt(10) + "@" + dominio);
			else email.append(removeSpace(cognome) + "@" + dominio);
		} else if (!usaCognome){
			if (rand != 1) email.append(removeSpace(nome) + r.nextInt(10) +
					r.nextInt(10) + "@" + dominio);
			else email.append(removeSpace(nome) + "@" + dominio);
		} else {
			email.append(mailNomeCognome(nome, cognome, dominio));
		}
		return removeSingleQuote(email.toString()).toLowerCase();
	}
	
	private static String abbreviaVocale(String s) {
		StringBuilder temp = new StringBuilder();
		temp.append(s.charAt(0));
		for (int i = 1; i < s.length(); i++) {
			if ("AEIOUaeiou".indexOf(s.charAt(i)) != -1) {
				temp.append(s.charAt(i));
				break;
			}
			temp.append(s.charAt(i));
		}
		return temp.toString();
	}
	
	private static String removeSpace(String s) {
		StringBuilder sb = new StringBuilder(s);
		while(sb.indexOf(" ")!=-1) {
			sb.deleteCharAt(sb.indexOf(" "));
		}
		return sb.toString();
	}
	
	private static String removeSingleQuote(String s) {
		StringBuilder sb = new StringBuilder(s);
		while(sb.indexOf("'")!=-1) {
			sb.deleteCharAt(sb.indexOf("'"));
		}
		return sb.toString();
	}
	
	private static String sostituisciVocali(String s) {
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == 'a' || c == 'A') {
				temp.append(4);
			} else if (c == 'e' || c == 'E') {
				temp.append(3);
			} else if (c == 'i' || c == 'I') {
				temp.append(1);
			} else if (c == 'o' || c == 'O') {
				temp.append(0);
			} else {
				temp.append(c);
			}
		}
		return temp.toString();
	}

	private static String mailNomeCognome(String nome, String cognome, String dominio) {
		Random r = new Random();
		StringBuilder email = new StringBuilder();
		
		// random 1-10 : valori 1-2 abbreviare il nome alla sola iniziale; 
		int abbrevia = r.nextInt(9) + 1;
		if (abbrevia <= 2) {
			nome = nome.substring(0,1);
		} else if (abbrevia <= 5) {
		// 3-5 abbreviare fino alla prima vocale successiva alla prima lettera (compresa)
			nome = abbreviaVocale(nome);
		}
		
		// separatore deciso da ulteriore random 1-10
		String separatore = "";
		int rSeparatore = r.nextInt(10) + 1;
		if(rSeparatore > 3 && rSeparatore <= 7) {
			separatore = ".";
		} else if( rSeparatore <= 10) {
			separatore = "-";
		}
		
		if (r.nextInt(5) + 1 == 1) {
			nome = sostituisciVocali(nome);
			cognome = sostituisciVocali(cognome);
		}
		
		// - random 1-4 : se esce 1 invertire nome con cognome
		if ((r.nextInt(3)+1) == 1) {
			email.append(cognome).append(separatore).append(nome);
		} else {
			email.append(nome).append(separatore).append(cognome);
		}
		
		//Unisco
		return removeSpace(email.append("@").append(dominio).toString().toLowerCase());
	}
}