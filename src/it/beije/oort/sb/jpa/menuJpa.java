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
	/*public static void menu(){
		System.out.println("Buongiorno, questo è un comodo tool per gestire il DB di una Biblioteca \n");	
		String concl = "";
		while(!concl.equalsIgnoreCase("quit")) {
			System.out.println("Cosa vuoi fare? Scegli tra: \n1)Ricerca:\t per visualizzare tutti i contatti con determinate caratteristiche; \n2)Modifica:\t per modificare un contatto già presente nel database;");
			System.out.println("3)Cancella:\t per cancellare un contatto presente nel database; \n4)Inserisci:\t per inserire un nuovo contatto in fondo alla rubrica del database;");
			System.out.println("5)Export:\t per salvare la rubrica su file (xml o csv); \n6)Import:\t per importare una rubrica; \n7)Quit:\t\t per concludere la sessione in corso.");
			System.out.println("\nDigita il numero corrispondente all'azione desiderata e premi INVIO");
			switch(sc.nextLine().toLowerCase()) {
			case "1" :
				HDBtools.ricerca();
				break;
			case "4" :
				HDBtools.insert();
				System.out.println("Contatto inserito in fondo alla rubrica!\n");
				break;
			case "2" :
				HDBtools.update();
				System.out.println("Contatto modificato!\n");
				break;
			case "5" :
				HDBtools.export();
				System.out.println("Rubrica salvata!\n");
				break;
			case "3" :
				HDBtools.delete();
				break;
			case "6":
				HDBtools.importer();
				break;
			case "7" :
				concl = "quit";
				System.out.println("Grazie per averci usato!");
				break;
			default :
				System.out.println("Non ho riconosciuto il comando");
			}

		}
		sc.close();
}
	
	*/
	public static void main(String[] args) {
		menuPrestito();

	}

}
