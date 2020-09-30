package it.beije.oort.madonia.biblioteca;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

import it.beije.oort.madonia.biblioteca.ebeans.Autore;

public class ClientBibliotecaDB {
	
	private final static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Benvenuto alla gestione del DB della biblioteca Oort");
		System.out.println();
		
		menuPrincipale();
		
		System.out.println("Chiusura programma");
	}
	
	private static void menuPrincipale() {
		
		boolean chiudiProgramma = false;
		while(!chiudiProgramma) {
			System.out.println("*** MENU PRINCIPALE ***");
			System.out.println("Cosa vuoi inserire?");
			System.out.println("1 - AUTORE");
			//System.out.println("2 - LIBRO");
			System.out.println("0 - CHIUDI PROGRAMMA");
			
			
			// TODO richiamare il menù
			boolean comandoValido = false;
			while(!comandoValido) {
				String inputUtente = sc.nextLine();
				switch (inputUtente) {
				case "1":
					ClientBibliotecaDB.menuInserisciAutore();
					comandoValido = true;
					break;
				case "0":
					comandoValido = true;
					chiudiProgramma = true;
					break;
				default:
					System.out.println("Comando non riconosciuto");
					break;
				}
			}
		}
	}
	
	private static void menuInserisciAutore() {
		Autore autore = new Autore();
		boolean menuPrecedente = false;
		while (!menuPrecedente) {
			System.out.println("*** INSERIMENTO AUTORE ***");
			System.out.println(autore);
			System.out.println("Cosa vuoi fare?");
			System.out.println("1 - COMPILA COGNOME");
			System.out.println("2 - COMPILA NOME");
			System.out.println("3 - COMPILA DATA_NASCITA");
			System.out.println("4 - COMPILA DATA_MORTE");
			System.out.println("5 - COMPILA BIOGRAFIA");
			System.out.println("C - INSERISCI AUTORE NEL DATABASE");
			System.out.println("R - RESETTA TUTTI I CAMPI");
			System.out.println("0 - MENU PRECEDENTE");
			
			String inputUtente = sc.nextLine();
			if(inputUtente.equals("1")) {
				System.out.println("Cognome: ");
				inputUtente = sc.nextLine();
				autore.setCognome(inputUtente);
			} else if(inputUtente.equals("2")) {
				System.out.println("Nome: ");
				inputUtente = sc.nextLine();
				autore.setNome(inputUtente);
			} else if(inputUtente.equals("3")) {
				System.out.println("Data di nascita (YYYY-MM-DD): ");
				inputUtente = sc.nextLine();
				try {
					LocalDate date = LocalDate.parse(inputUtente);
					autore.setDataNascita(date);
				} catch (DateTimeException e) {
					System.out.println("Campo non aggiornato, formato data non accettato");
				}
			} else if(inputUtente.equals("4")) {
				System.out.println("Data di morte (YYYY-MM-DD): ");
				inputUtente = sc.nextLine();
				try {
					LocalDate date = LocalDate.parse(inputUtente);
					autore.setDataMorte(date);
				} catch (DateTimeException e) {
					System.out.println("Campo non aggiornato, formato data non accettato");
				}
			} else if(inputUtente.equals("5")) {
				System.out.println("Biografia: ");
				inputUtente = sc.nextLine();
				autore.setBiografia(inputUtente);
			} else if(inputUtente.equalsIgnoreCase("c")) {
				// TODO Inserimento autore
			} else if(inputUtente.equalsIgnoreCase("r")) {
				autore = new Autore();
			} else if(inputUtente.equals("0")) {
				menuPrecedente = true;
			} else {
				System.out.println("Comando non riconosciuto");
			}
			
			System.out.println();
		}
	}

}
