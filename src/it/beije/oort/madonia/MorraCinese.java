package it.beije.oort.madonia;

import java.util.Scanner;

public class MorraCinese {

	static final String sasso = "sasso";
	static final String forbice = "forbice";
	static final String carta = "carta";
	static final String vittoria = "vittoria";
	static final String pareggio = "pareggio";
	static final String sconfitta = "sconfitta";
	static final String[] mosseTriangolo = {sasso, forbice, carta};

	public static void main(String[] args) {
		MorraCinese mr = new MorraCinese();
		Scanner sc = new Scanner(System.in);
		System.out.print("Scrivere la prima mossa: ");
		String simbolo1 = sc.nextLine().toLowerCase();
		System.out.print("Scrivere la seconda mossa: ");
		String simbolo2 = sc.nextLine().toLowerCase();
		
		// Il metodo risultato usa il case switch per calcolare il risultato
		// Il metodo risultato ternario usa un array con posizioni fisse per calcolare il risultato
		
//		String risultato = mr.risultato(simbolo1, simbolo2);
//		mr.stampaRisultato(simbolo1, simbolo2, risultato);
		
		String risultatoTernario = mr.risultatoTernario(simbolo1, simbolo2);
		mr.stampaRisultato(simbolo1, simbolo2, risultatoTernario);

		sc.close();
	}

	private void stampaRisultato(String simbolo1, String simbolo2, String risultato) {
		switch(risultato) {
		case vittoria:
			System.out.println(simbolo1 + " vince su " + simbolo2);
			break;
		case pareggio:
			System.out.println(simbolo1 + " pareggia con " + simbolo2);
			break;
		case sconfitta:
			System.out.println(simbolo1 + " perde su " + simbolo2);
			break;
		default:
			System.out.println("le mosse non sono valide");
		}
	}

	// Risultato usando il case switch
	public String risultato(String simbolo1, String simbolo2) {
		boolean isVittoria = false;
		boolean isPareggio = false;
		boolean isSconfitta = false;
		
		switch(simbolo1) {
		case sasso:
			isVittoria = simbolo2.equals(forbice);
			isPareggio = simbolo2.equals(sasso);
			isSconfitta = simbolo2.equals(carta);
			break;
		case forbice:
			isVittoria = simbolo2.equals(carta);
			isPareggio = simbolo2.equals(forbice);
			isSconfitta = simbolo2.equals(sasso);
			break;
		case carta:
			isVittoria = simbolo2.equals(sasso);
			isPareggio = simbolo2.equals(carta);
			isSconfitta = simbolo2.equals(forbice);
			break;
		}
		
		if (isVittoria) {
			return vittoria;
		} else if (isPareggio) {
			return pareggio;
		} else if (isSconfitta) {
			return sconfitta;
		} else {
			return "NaN";
		}
	}
	
	// Risultato usando un array circolare dove il primo simbolo può battere solo se il secondo si trova alla sua destra
	public String risultatoTernario(String simbolo1, String simbolo2) {
		boolean isVittoria = false;
		boolean isPareggio = false;
		boolean isSconfitta = false;
		
		int indexSimbolo1 = -1;
		for(int i = 0; i < mosseTriangolo.length; i++) {
			indexSimbolo1 = mosseTriangolo[i].equals(simbolo1) ? i : indexSimbolo1;
		}
		
		if (indexSimbolo1 >= 0) {
			int indexSuccessivo = (indexSimbolo1 + 1) % mosseTriangolo.length;
			int indexPrecedente = (indexSimbolo1 - 1 + mosseTriangolo.length) % mosseTriangolo.length;
			isVittoria = mosseTriangolo[indexSuccessivo].equals(simbolo2);
			isPareggio = mosseTriangolo[indexSimbolo1].equals(simbolo2);
			isSconfitta = mosseTriangolo[indexPrecedente].equals(simbolo2);
		}
		
		if (isVittoria) {
			return vittoria;
		} else if (isPareggio) {
			return pareggio;
		} else if (isSconfitta) {
			return sconfitta;
		} else {
			return "NaN";
		}
	}

}
