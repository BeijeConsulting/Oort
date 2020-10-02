package it.beije.oort.sb.jpa;

import java.util.Scanner;

import it.beije.oort.sb.hibernate.HDBtools;

public class menuJpa {
	static Scanner sc = new Scanner(System.in);
	
	public static void menuAutore() {
		System.out.println("Cosa vuoi fare? \n1)Cancellare un Autore; \n2)Inserire un nuovo Autore.");
		System.out.println("Scegli il numero corrispondente alla tua richiesta");
		String funzione = sc.nextLine();
		switch(funzione) {
		case "2":
			JPDBtools.insert(JPDBtools.createAutore());
			break;
		case "1": 
			JPDBtools.delete("Autori", JPDBtools.sceltaAutore());
		}
	}
	
	public static void menuLibro() {
		System.out.println("Cosa vuoi fare? \n1)Cancellare un Libro; \n2)Inserire un nuovo Libro.");
		System.out.println("Scegli il numero corrispondente alla tua richiesta");
		String funzione = sc.nextLine();
		switch(funzione) {
		case "2":
			JPDBtools.insert(JPDBtools.createLibro());
			break;
		case "1": 
			JPDBtools.delete("Libri", JPDBtools.sceltaLibro());
		}
	}
	public static void menuUtente() {
		System.out.println("Cosa vuoi fare? \n1)Cancellare un Utente; \n2)Inserire un nuovo Utente.");
		System.out.println("Scegli il numero corrispondente alla tua richiesta");
		String funzione = sc.nextLine();
		switch(funzione) {
		case "2":
			JPDBtools.insert(JPDBtools.createUtente());
			break;
		case "1": 
			JPDBtools.delete("Utenti", JPDBtools.sceltaUtente());
		}
	}
	public static void menuEditore() {
		System.out.println("Cosa vuoi fare? \n1)Cancellare un Editore; \n2)Inserire un nuovo Editore.");
		System.out.println("Scegli il numero corrispondente alla tua richiesta");
		String funzione = sc.nextLine();
		switch(funzione) {
		case "2":
			JPDBtools.insert(JPDBtools.createEditore());
			break;
		case "1": 
			JPDBtools.delete("Editori", JPDBtools.sceltaEditore());
		}
	}
	public static void menuPrestito() {
		System.out.println("Cosa vuoi fare? \n1)Cancellare un Prestito; \n2)Inserire un nuovo Prestito.");
		System.out.println("Scegli il numero corrispondente alla tua richiesta");
		String funzione = sc.nextLine();
		switch(funzione) {
		case "2":
			JPDBtools.insert(JPDBtools.createPrestito());
			break;
		case "1": 
			JPDBtools.delete("Prestiti", JPDBtools.sceltaPrestito());
		}
	}
	public static void menu(){
		System.out.println("Buongiorno, questo è un comodo tool per gestire il DB di una Biblioteca \n");	
		String concl = "";
		while(!concl.equalsIgnoreCase("quit")) {
			System.out.println("Cosa vuoi gestire? vuoi fare? Scegli tra: \n1)Autore; \n2)Libro; \n3)Prestito; \n4)Utente; \n5)Editore; \n6))Quit:\t\t per concludere la sessione in corso.");
			System.out.println("\nDigita il numero corrispondente all'azione desiderata e premi INVIO");
			switch(sc.nextLine().toLowerCase()) {
			case "1" :
				menuAutore();
				break;
			case "2" :
				menuLibro();
				break;
			case "3" :
				menuPrestito();
				break;
			case "4" :
				menuUtente();
				break;
			case "5" :
				menuEditore();
				break;
			case "6" :
				concl = "quit";
				System.out.println("Grazie per averci usato!");
				break;
			default :
				System.out.println("Non ho riconosciuto il comando");
			}

		}
		sc.close();
}
	
	
	public static void main(String[] args) {
		menu();

	}

}
