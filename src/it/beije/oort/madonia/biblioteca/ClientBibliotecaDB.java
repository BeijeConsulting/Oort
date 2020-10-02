package it.beije.oort.madonia.biblioteca;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

import it.beije.oort.madonia.biblioteca.db.DatabaseBibliotecaManager;
import it.beije.oort.madonia.biblioteca.ebeans.Autore;
import it.beije.oort.madonia.biblioteca.ebeans.Editore;
import it.beije.oort.madonia.biblioteca.ebeans.Libro;
import it.beije.oort.madonia.biblioteca.ebeans.Utente;

public class ClientBibliotecaDB {
	
	private final static Scanner sc = new Scanner(System.in);
	
	//TODO Gestione del prestito
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
			System.out.println("2 - EDITORE");
			System.out.println("3 - LIBRO");
			//System.out.println("4 - PRESTITO");
			System.out.println("PRESTITO NON IMPLEMENTATO");
			System.out.println("5 - UTENTE");
			
			System.out.println("0 - CHIUDI PROGRAMMA");
			
			boolean comandoValido = false;
			while(!comandoValido) {
				String inputUtente = sc.nextLine();
				switch (inputUtente) {
				case "1":
					ClientBibliotecaDB.menuInserisciAutore();
					comandoValido = true;
					break;
				case "2":
					ClientBibliotecaDB.menuInserisciEditore();
					comandoValido = true;
					break;
				case "3":
					ClientBibliotecaDB.menuInserisciLibro();
					comandoValido = true;
					break;
				case "5":
					ClientBibliotecaDB.menuInserisciUtente();
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
				if (autore.getCognome() == null || autore.getCognome().isEmpty()) {
					System.out.println("Autore non inserito, devi indicare almeno il cognome");
				} else {
					DatabaseBibliotecaManager.inserisciEntity(autore);
					System.out.println("Autore inserito nel database");
					autore = new Autore();
				}
				
				
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
	
	private static void menuInserisciEditore() {
		Editore editore = new Editore();
		boolean menuPrecedente = false;
		while (!menuPrecedente) {
			System.out.println("*** INSERIMENTO EDITORE ***");
			System.out.println(editore);
			System.out.println("Cosa vuoi fare?");
			System.out.println("1 - COMPILA DENOMINAZIONE");
			System.out.println("2 - COMPILA DESCRIZIONE");
			System.out.println("C - INSERISCI EDITORE NEL DATABASE");
			System.out.println("R - RESETTA TUTTI I CAMPI");
			System.out.println("0 - MENU PRECEDENTE");
			
			String inputUtente = sc.nextLine();
			
			if(inputUtente.equals("1")) {
				System.out.println("Denominazione: ");
				inputUtente = sc.nextLine();
				editore.setDenominazione(inputUtente);
				
			} else if(inputUtente.equals("2")) {
				System.out.println("Descrizione: ");
				inputUtente = sc.nextLine();
				editore.setDescrizione(inputUtente);
				
			} else if(inputUtente.equalsIgnoreCase("c")) {
				if (editore.getDenominazione() == null || editore.getDenominazione().isEmpty()) {
					System.out.println("Editore non inserito, devi indicare almeno la denominazione");
				} else {
					DatabaseBibliotecaManager.inserisciEntity(editore);
					System.out.println("Editore inserito nel database");
					editore = new Editore();
				}
				
				
			} else if(inputUtente.equalsIgnoreCase("r")) {
				editore = new Editore();
				
			} else if(inputUtente.equals("0")) {
				menuPrecedente = true;
			} else {
				System.out.println("Comando non riconosciuto");
			}

			System.out.println();
		}
	}
	
	private static void menuInserisciLibro() {
		Libro libro = new Libro();
		boolean menuPrecedente = false;
		while (!menuPrecedente) {
			System.out.println("*** INSERIMENTO LIBRO ***");
			System.out.println(libro);
			System.out.println("Cosa vuoi fare?");
			System.out.println("1 - COMPILA TITOLO");
			System.out.println("2 - COMPILA DESCRIZIONE");
			System.out.println("3 - COMPILA AUTORE");
			System.out.println("4 - COMPILA EDITORE");
			System.out.println("5 - COMPILA ANNO");
			System.out.println("C - INSERISCI LIBRO NEL DATABASE");
			System.out.println("R - RESETTA TUTTI I CAMPI");
			System.out.println("0 - MENU PRECEDENTE");
			
			String inputUtente = sc.nextLine();
			
			if(inputUtente.equals("1")) {
				System.out.println("Titolo: ");
				inputUtente = sc.nextLine();
				libro.setTitolo(inputUtente);
				
			} else if(inputUtente.equals("2")) {
				System.out.println("Descrizione: ");
				inputUtente = sc.nextLine();
				libro.setDescrizione(inputUtente);
				
			} else if(inputUtente.equals("3")) {
				Map<Integer, Autore> mappaAutori = DatabaseBibliotecaManager.lookUp(new Autore());
				System.out.println("Scegli l'autore dalla lista indicando il suo numero");
				System.out.println("0) ANNULLA SCELTA");
				stampaTabella(mappaAutori);
				
				boolean isNumeroValido = false;
				while (!isNumeroValido) {
					inputUtente = sc.nextLine();
					int selezione;
					try {
						selezione = Integer.parseInt(inputUtente);
						if (selezione == 0) {
							System.out.println("Scelta non effettuata");
							isNumeroValido = true;
						} else if (! mappaAutori.containsKey(selezione)) {
							System.out.println("Non si trova in elenco, riprovare");
						} else {
							libro.setIdAutore(mappaAutori.get(selezione).getId());
							isNumeroValido = true;
						}
					} catch (NumberFormatException e) {
						System.out.println("Non è un numero, riprovare");
					}
				}
				
			} else if(inputUtente.equals("4")) {
				Map<Integer, Editore> mappaEditori = DatabaseBibliotecaManager.lookUp(new Editore());
				System.out.println("Scegli l'editore dalla lista indicando il suo numero");
				System.out.println("0) ANNULLA SCELTA");
				stampaTabella(mappaEditori);
				
				boolean isNumeroValido = false;
				while (!isNumeroValido) {
					inputUtente = sc.nextLine();
					int selezione;
					try {
						selezione = Integer.parseInt(inputUtente);
						if (selezione == 0) {
							System.out.println("Scelta non effettuata");
							isNumeroValido = true;
						} else if (! mappaEditori.containsKey(selezione)) {
							System.out.println("Non si trova in elenco, riprovare");
						} else {
							libro.setIdEditore(mappaEditori.get(selezione).getId());
							isNumeroValido = true;
						}
					} catch (NumberFormatException e) {
						System.out.println("Non è un numero, riprovare");
					}
				}
				
			} else if(inputUtente.equals("5")) {
				System.out.println("Anno: ");
				inputUtente = sc.nextLine();
				try {
					int anno = Integer.parseInt(inputUtente);
					libro.setAnno(anno);
				} catch (NumberFormatException e) {
					System.out.println("Anno non inserito, il numero non è nel formato corretto");
				}
				
			} else if(inputUtente.equalsIgnoreCase("c")) {
				if (libro.getTitolo() == null || libro.getTitolo().isEmpty()) {
					System.out.println("Libro non inserito, devi indicare almeno il titolo");
				} else {
					DatabaseBibliotecaManager.inserisciEntity(libro);
					System.out.println("Libro inserito nel database");
					libro = new Libro();
				}
				
				
			} else if(inputUtente.equalsIgnoreCase("r")) {
				libro = new Libro();
				
			} else if(inputUtente.equals("0")) {
				menuPrecedente = true;
			} else {
				System.out.println("Comando non riconosciuto");
			}
			
			System.out.println();
		}
	}
	
	private static void menuInserisciUtente() {
		Utente utente = new Utente();
		boolean menuPrecedente = false;
		while (!menuPrecedente) {
			System.out.println("*** INSERIMENTO UTENTE ***");
			System.out.println(utente);
			System.out.println("Cosa vuoi fare?");
			System.out.println("1 - COMPILA COGNOME");
			System.out.println("2 - COMPILA NOME");
			System.out.println("3 - COMPILA CODICE_FISCALE");
			System.out.println("4 - COMPILA EMAIL");
			System.out.println("5 - COMPILA TELEFONO");
			System.out.println("6 - COMPILA INDIRIZZO");
			System.out.println("C - INSERISCI UTENTE NEL DATABASE");
			System.out.println("R - RESETTA TUTTI I CAMPI");
			System.out.println("0 - MENU PRECEDENTE");
			
			String inputUtente = sc.nextLine();
			
			if(inputUtente.equals("1")) {
				System.out.println("Cognome: ");
				inputUtente = sc.nextLine();
				utente.setCognome(inputUtente);
				
			} else if(inputUtente.equals("2")) {
				System.out.println("Nome: ");
				inputUtente = sc.nextLine();
				utente.setNome(inputUtente);
				
			} else if(inputUtente.equals("3")) {
				System.out.println("Codice fiscale: ");
				inputUtente = sc.nextLine();
				if (inputUtente.length() != 16) {
					System.out.println("Codice fiscale non inserito, non è della lunghezza corretta");
				} else {
					utente.setCodiceFiscale(inputUtente);
				}
				
			} else if(inputUtente.equals("4")) {
				System.out.println("Email: ");
				inputUtente = sc.nextLine();
				utente.setEmail(inputUtente);
				
			} else if(inputUtente.equals("5")) {
				System.out.println("Telefono: ");
				inputUtente = sc.nextLine();
				utente.setTelefono(inputUtente);
				
			} else if (inputUtente.equals("6")) {
				System.out.println("Indirizzo: ");
				inputUtente = sc.nextLine();
				utente.setIndirizzo(inputUtente);
				
			} else if(inputUtente.equalsIgnoreCase("c")) {
				if (utente.getCognome() == null || utente.getCognome().isEmpty()) {
					System.out.println("Utente non inserito, devi indicare almeno il cognome");
				} else {
					DatabaseBibliotecaManager.inserisciEntity(utente);
					System.out.println("Utente inserito nel database");
					utente = new Utente();
				}
				
			} else if(inputUtente.equalsIgnoreCase("r")) {
				utente = new Utente();
				
			} else if(inputUtente.equals("0")) {
				menuPrecedente = true;
			} else {
				System.out.println("Comando non riconosciuto");
			}
			
			System.out.println();
		}
	}
	
	private static void stampaRigaLookUp(int i, Autore autore) {
		StringBuilder sb = new StringBuilder()
				.append(i)
				.append(") ")
				.append(autore.getNome())
				.append(" ")
				.append(autore.getCognome());
		System.out.println(sb);
	}
	
	private static void stampaRigaLookUp(int i, Editore editore) {
		StringBuilder sb = new StringBuilder()
				.append(i)
				.append(") ")
				.append(editore.getDenominazione());
		System.out.println(sb);
	}

	private static void stampaTabella(Map<Integer, ?> mappa) {
		for(int i = 0; i < mappa.size(); i++) {
			Object obj = mappa.get(i + 1);
			if (obj instanceof Autore) {
				stampaRigaLookUp(i + 1, (Autore) obj);
			} else if (obj instanceof Editore) {
				stampaRigaLookUp(i + 1, (Editore) obj);
			}
		}
	}
}
