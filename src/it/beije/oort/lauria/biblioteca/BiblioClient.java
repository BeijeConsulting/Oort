package it.beije.oort.lauria.biblioteca;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Scanner;

public class BiblioClient {
	public static final Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		
		new JPADBtools();
		
		String scelta = ""; 
			do {				
				BiblioClient.menu();	
				scelta = in.nextLine();
				switch(scelta) {
					case "1":{
						String sceltaVisual;
						do {
							BiblioClient.menuVisualizza();
							do{
								try{		
								 sceltaVisual = in.nextLine();
								}catch(NumberFormatException e) {
									System.out.println("Il valore inserito, non è un numero. Riprovare.");
									sceltaVisual = "";
								}
							}while(sceltaVisual.equals(""));
							if(Integer.parseInt(sceltaVisual) <= 0 && Integer.parseInt(sceltaVisual) >= 5) {
								System.out.println("Azione non valida. Riprovare.");
							}
						}while(Integer.parseInt(sceltaVisual)  <= 0 && Integer.parseInt(sceltaVisual)  >= 5);
						switch(sceltaVisual) {
							case "1":{
								// visualizza lista libri
								BiblioClient.visualizzaLibri(scelta);
								break;
							}
							case "2":{
								// visualizza lista autori
								BiblioClient.visualizzaAutori(scelta);
								break;
							}
							case "3":{
								// visualizza lista editori
								BiblioClient.visualizzaEditori(scelta);
								break;
							}
							case "4":{
								// visualizza lista utenti
								visualizzaUtenti(scelta);
								break;
							}
							case "5":{
								// visualizza lista prestiti
								visualizzaPrestiti(scelta);
								break;
							}
						}		
						break;
					}
					case "2":{
						String sceltaReg;
						do {
							BiblioClient.menuRegistrazione();
							do{
								try{		
									sceltaReg = in.nextLine();
								}catch(NumberFormatException e) {
									System.out.println("Il valore inserito, non è un numero. Riprovare.");
									sceltaReg = "";
								}
							}while(sceltaReg.equals(""));
							if(Integer.parseInt(sceltaReg) <= 0 && Integer.parseInt(sceltaReg) >= 5) {
								System.out.println("Azione non valida. Riprovare.");
							}
						}while(Integer.parseInt(sceltaReg)  <= 0 && Integer.parseInt(sceltaReg)  >= 5);
						switch(sceltaReg) {
							case "1":{
								// inserimento libro in tabella libri
								if(!BiblioClient.registraLibro()) {
									System.out.println("Registrazione del libro fallita.\n");
									break;
								}
								break;
							}
							case "2":{
								// inserimento autore in tabella autori
								BiblioClient.registraAutore();
								break;
							}
							case "3":{
								// inserimento editore in tabella editori
								BiblioClient.registraEditore();
								break;
							}
							case "4":{
								// inserimento utente in tabella utenti
								BiblioClient.registraUtente();
								break;
							}
							case "5":{
								// inserimento prestito in tabella prestiti
								if(!BiblioClient.registraPrestito()) {
									System.out.println("Registrazione del prestito fallita.\n");
									break;
								}
								break;
							}
						}		
						break;
					
					}
					case "3":{
					 System.out.println("Arrivederci.");
					 break;
					}
					default:{
						System.out.println("Azione non valida. Riprovare.\n");
						//break;
					}
				}
			}while(!scelta.equalsIgnoreCase("3"));

			in.close();
	}
	
	public static void menu() {
		System.out.println("Digitare l'azione desiderata.");
		System.out.println("1 - Visualizzazione.\n2 - Inserimento.\n3 - Esci.");
		System.out.println("> ");
	}
	
	public static void menuVisualizza() {
		System.out.println("Digitare l'azione desiderata.");
		System.out.println("1 - Visualizzazione di tutti i libri disponibili.\n2 - Visualizzazione di tutti gli autori disponibili.\n"
				+ "3 - Visualizzazione di tutti gli editori disponibili.\n4 - Visualizzazione di tutti gli utenti registrati.\n"
				+ "5 - Visualizzazione di tutti i prestiti registrati.");
		System.out.print("> ");
	}
	
	public static void menuRegistrazione() {
		System.out.println("Digitare l'azione desiderata.");
		System.out.println("1 - Registrazione di un nuovo libro.\n2 - Registrazione di un nuovo autore.\n"
				+ "3 - Registrazione di un nuovo editore.\n4 - Registrazione di un nuovo utente.\n"
				+ "5 - Registrazione di un nuovo prestito.");
		System.out.print("> ");
	}
	
	public static String visualizzaLibri(String scelta) {		
		
		String changePage = "";
		int i = 0, j;
		int notValid = 0;
		boolean result;
		do{ 							
			j = i + 50;
			if(notValid != -1) {
				result = JPADBtools.selectLibri(i, j);
				if(!result && i == 0) {
					System.out.println("Non sono presenti prestiti di libri in biblioteca.");
					break;
				}else if(!result && i > 0){
					System.out.println("Fine lista prestiti di libri in biblioteca.");
					break;
				}
//				else {
//					JPADBtools.selectPrestiti(i, j);
//				}
			}
			System.out.println("\nPer visualizzare la pagina successiva: premere INVIO.");
			System.out.println("Per visualizzare la pagina precedente: premere < .");
			System.out.println("Per tornare al menu principale: premere 0.");
			System.out.println("Per terminare il programma: premere 3.");
			System.out.print("> ");
			changePage = in.nextLine();		
			notValid = 0;			
			if(changePage.equalsIgnoreCase("")) {								
				i = j;
			}else if(changePage.equalsIgnoreCase("<")){
				if(i != 0) {
					i -= 50;
					j -= 50;
				}
			}else if(changePage.equalsIgnoreCase("0")){
				break;
			}else if(changePage.equalsIgnoreCase("3")) {
				scelta = "3";
				System.out.println("Arrivederci.");
				break;
			}else {
				System.out.println("Azione non valida. Riprovare.");
				notValid = -1;
			}
			
		}while( !changePage.equalsIgnoreCase("0") && !changePage.equalsIgnoreCase("3"));
	
		return scelta;
	}
	
	public static String visualizzaAutori(String scelta) {		
		
		String changePage = "";
		int i = 0, j;
		int notValid = 0;
		boolean result;
		do{ 							
			j = i + 50;
			if(notValid != -1) {
				result = JPADBtools.selectAutori(i, j);
				if(!result && i == 0) {
					System.out.println("Non sono presenti prestiti di libri in biblioteca.");
					break;
				}else if(!result && i > 0){
					System.out.println("Fine lista prestiti di libri in biblioteca.");
					break;
				}
//				else {
//					JPADBtools.selectPrestiti(i, j);
//				}
			}
			System.out.println("\nPer visualizzare la pagina successiva: premere INVIO.");
			System.out.println("Per visualizzare la pagina precedente: premere < .");
			System.out.println("Per tornare al menu principale: premere 0.");
			System.out.println("Per terminare il programma: premere 3.");
			System.out.print("> ");
			changePage = in.nextLine();		
			notValid = 0;			
			if(changePage.equalsIgnoreCase("")) {								
				i = j;
			}else if(changePage.equalsIgnoreCase("<")){
				if(i != 0) {
					i -= 50;
					j -= 50;
				}
			}else if(changePage.equalsIgnoreCase("0")){
				break;
			}else if(changePage.equalsIgnoreCase("3")) {
				scelta = "3";
				System.out.println("Arrivederci.");
				break;
			}else {
				System.out.println("Azione non valida. Riprovare.");
				notValid = -1;
			}
			
		}while( !changePage.equalsIgnoreCase("0") && !changePage.equalsIgnoreCase("3"));
	
		return scelta;
	}
	
	public static String visualizzaEditori(String scelta) {		
		
		String changePage = "";
		int i = 0, j;
		int notValid = 0;
		boolean result;
		do{ 							
			j = i + 50;
			if(notValid != -1) {
				result = JPADBtools.selectEditori(i, j);
				if(!result && i == 0) {
					System.out.println("Non sono presenti prestiti di libri in biblioteca.");
					break;
				}else if(!result && i > 0){
					System.out.println("Fine lista prestiti di libri in biblioteca.");
					break;
				}
//				else {
//					JPADBtools.selectPrestiti(i, j);
//				}
			}
			System.out.println("\nPer visualizzare la pagina successiva: premere INVIO.");
			System.out.println("Per visualizzare la pagina precedente: premere < .");
			System.out.println("Per tornare al menu principale: premere 0.");
			System.out.println("Per terminare il programma: premere 3.");
			System.out.print("> ");
			changePage = in.nextLine();		
			notValid = 0;			
			if(changePage.equalsIgnoreCase("")) {								
				i = j;
			}else if(changePage.equalsIgnoreCase("<")){
				if(i != 0) {
					i -= 50;
					j -= 50;
				}
			}else if(changePage.equalsIgnoreCase("0")){
				break;
			}else if(changePage.equalsIgnoreCase("3")) {
				scelta = "3";
				System.out.println("Arrivederci.");
				break;
			}else {
				System.out.println("Azione non valida. Riprovare.");
				notValid = -1;
			}
			
		}while( !changePage.equalsIgnoreCase("0") && !changePage.equalsIgnoreCase("3"));
	
		return scelta;
	}
	
	public static String visualizzaUtenti(String scelta) {		
		
		String changePage = "";
		int i = 0, j;
		int notValid = 0;
		boolean result;
		do{ 							
			j = i + 50;
			if(notValid != -1) {
				result = JPADBtools.selectUtenti(i, j);
				if(!result && i == 0) {
					System.out.println("Non sono presenti prestiti di libri in biblioteca.");
					break;
				}else if(!result && i > 0){
					System.out.println("Fine lista prestiti di libri in biblioteca.");
					break;
				}
//				else {
//					JPADBtools.selectPrestiti(i, j);
//				}
			}
			System.out.println("\nPer visualizzare la pagina successiva: premere INVIO.");
			System.out.println("Per visualizzare la pagina precedente: premere < .");
			System.out.println("Per tornare al menu principale: premere 0.");
			System.out.println("Per terminare il programma: premere 3.");
			System.out.print("> ");
			changePage = in.nextLine();		
			notValid = 0;			
			if(changePage.equalsIgnoreCase("")) {								
				i = j;
			}else if(changePage.equalsIgnoreCase("<")){
				if(i != 0) {
					i -= 50;
					j -= 50;
				}
			}else if(changePage.equalsIgnoreCase("0")){
				break;
			}else if(changePage.equalsIgnoreCase("3")) {
				scelta = "3";
				System.out.println("Arrivederci.");
				break;
			}else {
				System.out.println("Azione non valida. Riprovare.");
				notValid = -1;
			}
			
		}while( !changePage.equalsIgnoreCase("0") && !changePage.equalsIgnoreCase("3"));
	
		return scelta;
	}
	
	public static String visualizzaPrestiti(String scelta) {		
		
		String changePage = "";
		int i = 0, j;
		int notValid = 0;
		boolean result;
		do{ 							
			j = i + 50;
			if(notValid != -1) {
				result = JPADBtools.selectPrestiti(i, j);
				if(!result && i == 0) {
					System.out.println("Non sono presenti prestiti di libri in biblioteca.");
					break;
				}else if(!result && i > 0){
					System.out.println("Fine lista prestiti di libri in biblioteca.");
					break;
				}
//				else {
//					JPADBtools.selectPrestiti(i, j);
//				}
			}
			System.out.println("\nPer visualizzare la pagina successiva: premere INVIO.");
			System.out.println("Per visualizzare la pagina precedente: premere < .");
			System.out.println("Per tornare al menu principale: premere 0.");
			System.out.println("Per terminare il programma: premere 3.");
			System.out.print("> ");
			changePage = in.nextLine();		
			notValid = 0;			
			if(changePage.equalsIgnoreCase("")) {								
				i = j;
			}else if(changePage.equalsIgnoreCase("<")){
				if(i != 0) {
					i -= 50;
					j -= 50;
				}
			}else if(changePage.equalsIgnoreCase("0")){
				break;
			}else if(changePage.equalsIgnoreCase("3")) {
				scelta = "3";
				System.out.println("Arrivederci.");
				break;
			}else {
				System.out.println("Azione non valida. Riprovare.");
				notValid = -1;
			}
			
		}while( !changePage.equalsIgnoreCase("0") && !changePage.equalsIgnoreCase("3"));
	
		return scelta;
	}
	
	
	
	
	public static boolean registraLibro() {
		String titolo = "", descrizione = "", anno = "", id_autore = "", id_editore = "";
		
		System.out.println("Digitare i campi del libro da inserire: ");				
		System.out.print("titolo : ");
		titolo = in.nextLine();
		System.out.print("descrizione : ");
		descrizione = in.nextLine();
		System.out.print("id_autore : ");
		do{
			try{		
				id_autore = in.nextLine();
			}catch(NumberFormatException e) {
				System.out.println("Il valore inserito, non è un numero. Riprovare.");
				id_autore = "";
			}
		}while(id_autore.equals(""));
		
		if(JPADBtools.entityManager.find(Autore.class, Integer.parseInt(id_autore)) == null) {
			System.out.println("Non esiste un autore associato all'identificativo numerico digitato.");
			return false;
		}
		System.out.print("id_editore : ");
		do{
			try{		
				id_editore = in.nextLine();
			}catch(NumberFormatException e) {
				System.out.println("Il valore inserito, non è un numero. Riprovare.");
				id_editore = "";
			}
		}while(id_editore.equals(""));
		
		if(JPADBtools.entityManager.find(Editore.class, Integer.parseInt(id_editore)) == null) {
			System.out.println("Non esiste un editore associato all'identificativo numerico digitato.");
			return false;
		}
		System.out.print("anno : ");
		anno = in.nextLine();
		System.out.println();
		
		JPADBtools.insertLibro(titolo, descrizione, Integer.parseInt(id_autore), Integer.parseInt(id_editore), anno);
		System.out.println("Libro registrato con successo.\n");
		return true;
	}


	public static void registraAutore() {
		String nome = "", cognome = "", biografia = "", data_n = "", data_m = "";
		
		System.out.println("Digitare i campi dell'autore da inserire: ");				
		System.out.print("nome : ");
		nome = in.nextLine();
		System.out.print("cognome : ");
		cognome = in.nextLine();
		
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd MM yyyy");
		System.out.print("data di nascita (\"dd MM yyyy\") : ");
		data_n = in.nextLine();		
		LocalDate data_nascita = LocalDate.parse(data_n, f);
		System.out.print("data di morte (\"dd MM yyyy\") : ");
		data_m = in.nextLine();		
		LocalDate data_morte = LocalDate.parse(data_m, f);
		
		System.out.print("biografia : ");
		biografia = in.nextLine();
		System.out.println();
		
		JPADBtools.insertAutore(nome, cognome, data_nascita, data_morte, biografia);
		System.out.println("Autore registrato con successo.\n");
	}
	
	public static void registraEditore() {
		String denominazione ="", descrizione="";
		System.out.println("Digitare i campi dell'editore da inserire: ");				
		System.out.print("denominazione : ");
		denominazione = in.nextLine();					
		System.out.print("descrizione : ");
		descrizione = in.nextLine();		
		System.out.println();
		
		JPADBtools.insertEditore(denominazione, descrizione);
		System.out.println("Editore registrato con successo.\n");
	}
	
	public static void registraUtente() {
		String nome ="", cognome="", telefono="", email="", codice_fiscale = "", indirizzo = "";
		System.out.println("Digitare i dati dell'utente da inserire: ");				
		System.out.print("nome : ");
		nome = in.nextLine();					
		System.out.print("cognome : ");
		cognome = in.nextLine();
		System.out.print("codice fiscale : ");
		codice_fiscale = in.nextLine();
		System.out.print("email : ");
		email = in.nextLine();
		System.out.print("telefono : ");
		telefono = in.nextLine();
		System.out.print("indirizzo : ");
		indirizzo = in.nextLine();
		System.out.println();
		
		JPADBtools.insertUtente(nome, cognome, codice_fiscale, email, telefono, indirizzo);
		System.out.println("Utente registrato con successo.\n");
	}
	
	public static boolean registraPrestito() {
		String id_libro = "", id_utente = "",  data_n = "", data_f = "", note = "";
		
		System.out.println("Digitare i campi necessari alla registrazione del prestito: ");				
		System.out.print("id_libro : ");
		do{
			try{		
				id_libro = in.nextLine();
			}catch(NumberFormatException e) {
				System.out.println("Il valore inserito, non è un numero. Riprovare.");
				id_libro = "";
			}
		}while(id_libro.equals(""));
		
		if(JPADBtools.entityManager.find(Libro.class, Integer.parseInt(id_libro)) == null) {
			System.out.println("Non esiste un editore associato all'identificativo numerico digitato.");
			return false;
		}
		System.out.print("id_utente : ");
		do{
			try{		
				id_utente = in.nextLine();
			}catch(NumberFormatException e) {
				System.out.println("Il valore inserito, non è un numero. Riprovare.");
				id_utente = "";
			}
		}while(id_utente.equals(""));
		
		if(JPADBtools.entityManager.find(Utente.class, Integer.parseInt(id_utente)) == null) {
			System.out.println("Non esiste un editore associato all'identificativo numerico digitato.");
			return false;
		}
		
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd MM yyyy");
		System.out.print("data di inizio prestito (\"dd MM yyyy\") : ");
		data_n = in.nextLine();		
		LocalDate data_inizio = LocalDate.parse(data_n, f);
		System.out.print("data di reso del libro (\"dd MM yyyy\") : ");
		data_f = in.nextLine();		
		LocalDate data_fine = LocalDate.parse(data_f, f);
		
		System.out.print("note : ");
		note = in.nextLine();
		System.out.println();
		
		JPADBtools.insertPrestito(Integer.parseInt(id_libro), Integer.parseInt(id_utente),  data_inizio, data_fine, note);
		System.out.println("Prestito registrato con successo.\n");
		return true;
	}
}
