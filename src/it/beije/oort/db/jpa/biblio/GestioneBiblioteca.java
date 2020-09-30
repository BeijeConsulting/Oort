package it.beije.oort.db.jpa.biblio;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import it.beije.oort.db.hybernate.biblioteca.AutoreUtility;
import it.beije.oort.db.hybernate.biblioteca.Autori;
import it.beije.oort.db.hybernate.biblioteca.Biblioteca;
import it.beije.oort.db.hybernate.biblioteca.Editore;
import it.beije.oort.db.hybernate.biblioteca.EditoreUtility;
import it.beije.oort.db.hybernate.biblioteca.Libro;
import it.beije.oort.db.hybernate.biblioteca.LibroUtility;
import it.beije.oort.db.hybernate.biblioteca.Prestito;
import it.beije.oort.db.hybernate.biblioteca.PrestitoUtility;
import it.beije.oort.db.hybernate.biblioteca.Utente;
import it.beije.oort.db.hybernate.biblioteca.UtenteUtility;

public class GestioneBiblioteca {

	private static Scanner scan = new Scanner(System.in);

	public static void showMenu() throws IOException, ParserConfigurationException, TransformerException {
		System.out.println("\t \t \t \t \t <[Client_Scanner_JPA a linea di comando]> <[v_2.0]> \n");
		System.out.println("\t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t  ___________________ ");
		System.out.println("\t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t |Benvenuti nella    | ");
		System.out.println("\t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t |Biblioteca Comunale|");
		System.out.println("\t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t |___________________| ");
		System.out.println("Menu: ");
		System.out.println("1) Visualizzatore.");
		System.out.println("2) Ricerca.");
		System.out.println("3) Modifica.");
		System.out.println("4) Rimozione.");
		System.out.println("5) Inserimento.");
		System.out.println("6) Export database.");
		System.out.println("7) Termina programma.");
		System.out.print("Inserire scelta: ");
		int scelta = Integer.parseInt(scan.nextLine());
		
		switch(scelta) {
			case 1:
				GestioneBiblioteca.visualizza();
				GestioneBiblioteca.showMenu();
				break;
			case 2:
				System.out.println("Nice try!");
				GestioneBiblioteca.showMenu();
				break;
			case 3:
				System.out.println("Nice try!");
				GestioneBiblioteca.showMenu();
				break;
			case 4:
				System.out.println("Nice try!");
				GestioneBiblioteca.showMenu();
				break;
			case 5:
				GestioneBiblioteca.inserisci();
				GestioneBiblioteca.showMenu();
				break;
			case 6:
				System.out.println("Nice try!");
				GestioneBiblioteca.showMenu();
				break;
			case 7:
				System.out.println("Chiusura in corso..");
				System.out.println("Closed!");
				System.out.println("\t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t  ___________________ ");
				System.out.println("\t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t |Arrivederci dalla  | ");
				System.out.println("\t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t |Biblioteca Comunale|");
				System.out.println("\t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t |___________________| ");
				break;
			default:
				System.out.println("ERRORE: inserire una scelta valida!");
				GestioneBiblioteca.showMenu();
		}
	}

	private static void inserisci() {
		int scelta = GestioneBiblioteca.sceltaTabella();

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
		System.out.println("Inserire altro? (s/n)");
		if(scan.nextLine().charAt(0) == 's') {
			GestioneBiblioteca.inserisci();
		}
	}

	private static void visualizza() throws IOException, ParserConfigurationException, TransformerException {
		int scelta = GestioneBiblioteca.sceltaTabella();

		switch(scelta) {
			case 1:
				List<Utente> utenti = UtenteUtility.visualizza();
				System.out.println("Campi presenti: " + utenti.size());
				for(Utente utente : utenti) {
					System.out.println(utente);
				}
				break;
			case 2:
				List<Libro> libri = LibroUtility.visualizza();
				System.out.println("Campi presenti: " + libri.size());
				for(Libro libro : libri) {
					System.out.println(libro);
				}
				break;
			case 3:
				List<Editore> editori = EditoreUtility.visualizza();
				System.out.println("Campi presenti: " + editori.size());
				for(Editore editore : editori) {
					System.out.println(editore);
				}
				break;
			case 4:
				List<Autore> autori = AutoreUtil.visualizza();
				System.out.println("Campi presenti: " + autori.size());
				for(Autore autore : autori) {
					System.out.println(autore);
				}
				break;
			case 5:
				List<Prestito> prestiti = PrestitoUtility.visualizza();
				System.out.println("Campi presenti: " + prestiti.size());
				for(Prestito prenotazione : prestiti) {
					System.out.println(prenotazione);
				}
				break;
			default:
				System.out.println("ERRORE!");
				break;
		}
		System.out.println("Visualizzare un'altra tabella? (s/n)");
		if(scan.nextLine().charAt(0) == 's') {
			GestioneBiblioteca.visualizza();
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
			System.out.print("Inserisci la scelta: ");
			scelta = Integer.parseInt(scan.nextLine());
		} 
		catch(NumberFormatException e) {
			System.out.println("ERRORE: inserire un valore valido!");
			GestioneBiblioteca.sceltaTabella();
		}
		if(scelta < 1 || scelta > 5) {
			System.out.println("ERRORE: inserire un valore valido!");
			GestioneBiblioteca.sceltaTabella();
		}

		return scelta;
	}

	public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException {
		GestioneBiblioteca.showMenu();
		GestioneBiblioteca.scan.close();
	}

}