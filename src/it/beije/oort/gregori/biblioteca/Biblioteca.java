package it.beije.oort.gregori.biblioteca;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class Biblioteca {
	private static Scanner sc = new Scanner(System.in);
	
	public static void showMenu() throws IOException, ParserConfigurationException, TransformerException {
		System.out.println("-----CMD client per database biblioteca-----");
		System.out.println("Menu: ");
		System.out.println("1) Visualizzatore.");
		System.out.println("2) Ricerca.");
		System.out.println("3) Modifica.");
		System.out.println("4) Rimozione.");
		System.out.println("5) Inserimento.");
		System.out.println("6) Export database.");
		System.out.println("7) Termina programma.");
		
		int scelta = Integer.parseInt(sc.nextLine());
		switch(scelta) {
			case 1:
				Biblioteca.visualizza();
				Biblioteca.showMenu();
				break;
			case 2:
				System.out.println("Nice try!");
				Biblioteca.showMenu();
				break;
			case 3:
				System.out.println("Nice try!");
				Biblioteca.showMenu();
				break;
			case 4:
				System.out.println("Nice try!");
				Biblioteca.showMenu();
				break;
			case 5:
				Biblioteca.inserisci();
				Biblioteca.showMenu();
				break;
			case 6:
				System.out.println("Nice try!");
				Biblioteca.showMenu();
				break;
			case 7:
				System.out.println("Arrivederci!");
				break;
			default:
				System.out.println("ERRORE: inserire una scelta valida!");
				Biblioteca.showMenu();
		}
	}
	
	private static void inserisci() {
		int scelta = Biblioteca.sceltaTabella();
		
		switch(scelta) {
			case 1:
				UtenteUtility.inserisci();
				break;
			case 2:
				LibroUtility.inserisci();
				break;
			case 3:
				EditoreUtility.inserisci();
				break;
			case 4:
				AutoreUtility.inserisci();
				break;
			case 5:
				PrestitoUtility.inserisci();
				break;
			default:
				System.out.println("ERRORE: how did you get here?");
				break;
		}
		System.out.println("Inserire altro? (s per confermare)");
		if(sc.nextLine().charAt(0) == 's') {
			Biblioteca.inserisci();
		}
	}
	
	private static void visualizza() throws IOException, ParserConfigurationException, TransformerException {
		int scelta = Biblioteca.sceltaTabella();
		
		switch(scelta) {
			case 1:
				List<Utente> utenti = UtenteUtility.visualizza();
				System.out.println("Caricati " + utenti.size() +  " records.");
				for(Utente utente : utenti) {
					System.out.println(utente);
				}
				break;
			case 2:
				List<Libro> libri = LibroUtility.visualizza();
				System.out.println("Caricati " + libri.size() +  " records.");
				for(Libro libro : libri) {
					System.out.println(libro);
				}
				break;
			case 3:
				List<Editore> editori = EditoreUtility.visualizza();
				System.out.println("Caricati " + editori.size() +  " records.");
				for(Editore editore : editori) {
					System.out.println(editore);
				}
				break;
			case 4:
				List<Autore> autori = AutoreUtility.visualizza();
				System.out.println("Caricati " + autori.size() +  " records.");
				for(Autore autore : autori) {
					System.out.println(autore);
				}
				break;
			case 5:
				List<Prestito> prestiti = PrestitoUtility.visualizza();
				System.out.println("Caricati " + prestiti.size() +  " records.");
				for(Prestito prenotazione : prestiti) {
					System.out.println(prenotazione);
				}
				break;
			default:
				System.out.println("ERRORE: how did you get here?");
				break;
		}
		System.out.println("Visualizzare un'altra tabella? (s per confermare)");
		if(sc.nextLine().charAt(0) == 's') {
			Biblioteca.visualizza();
		}
	}
	
	private static int sceltaTabella() {
		int scelta = 0;
		System.out.println("Che tabella vuoi visualizzare?");
		System.out.println("1) Utenti");
		System.out.println("2) Libri");
		System.out.println("3) Editori");
		System.out.println("4) Autori");
		System.out.println("5) Prestiti");
		try{
			scelta = Integer.parseInt(sc.nextLine());
		} 
		catch(NumberFormatException e) {
			System.out.println("ERRORE: inserire un valore valido!");
			Biblioteca.sceltaTabella();
		}
		if(scelta < 1 || scelta > 5) {
			System.out.println("ERRORE: inserire un valore valido!");
			Biblioteca.sceltaTabella();
		}
		
		return scelta;
	}

	public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException {
		Biblioteca.showMenu();
		Biblioteca.sc.close();
	}

}
