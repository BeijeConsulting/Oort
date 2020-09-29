package it.beije.oort.lauria.hb;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import it.beije.oort.lauria.Contatto;
import it.beije.oort.lauria.CsvParser;
import it.beije.oort.lauria.XmlParser;
import it.beije.oort.lauria.db.DBClient;

public class HDBClient {
	
//	private static final String PATH_FILES = "C:\\Users\\Padawan06\\Documenti\\temp\\";
	public static final Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		
		String scelta = ""; 
			do {
			DBClient.menu();	
				 scelta = in.nextLine();
			switch(scelta) {
				case "1":{
					String sceltaVisual;
					do {
						DBClient.menuVisualizza();
						do{
							try{		
							 sceltaVisual = in.nextLine();
							}catch(NumberFormatException e) {
								System.out.println("Il valore inserito, non è un numero. Riprovare.");
								sceltaVisual = "";
							}
						}while(sceltaVisual.equals(""));
						if(Integer.parseInt(sceltaVisual) != 1 && Integer.parseInt(sceltaVisual) != 2) {
							System.out.println("Azione non valida. Riprovare.");
						}
					}while(Integer.parseInt(sceltaVisual) != 1 && Integer.parseInt(sceltaVisual) != 2);
					switch(sceltaVisual) {
						case "1":{
							List<Contatto> recordContatti = new ArrayList<Contatto>();						
							HDBtools.selectContacts(recordContatti);
							scelta = HDBClient.visualizzaContatti(scelta, recordContatti);
							break;
						}
						case "2":{
							HDBClient.searchContact();
							break;
						}
					}					
					break;
				}
				case "2":{
					String sceltaMod;
					do {
						System.out.println("Digitare l'azione desiderata.");
						System.out.println("1 - Modificare un contatto.\n2 - Cancellare un contatto.");
						System.out.print("> ");
						do{
							try{		
								sceltaMod = in.nextLine();
							}catch(NumberFormatException e) {
								System.out.println("Il valore inserito, non è un numero. Riprovare.");
								sceltaMod = "";
							}
						}while(sceltaMod.equals(""));
						if(Integer.parseInt(sceltaMod) != 1 && Integer.parseInt(sceltaMod) != 2) {
							System.out.println("Azione non valida. Riprovare.");
						}
					}while(Integer.parseInt(sceltaMod) != 1 && Integer.parseInt(sceltaMod) != 2);
					switch(sceltaMod) {
						case "1":{
							HDBClient.modifyContact();
							break;
						}
						case "2":{
							HDBClient.deleteContact();
							break;
						}
					}
					break;
				}
				case "3":{
					HDBClient.insertContact();
					break;
				}
				case "4":{
					List<Contatto> recordContatti = new ArrayList<Contatto>();				
					HDBtools.selectContacts(recordContatti);
					HDBClient.exportRubrica(recordContatti);
					break;
				}
				case "5":{
					System.out.println("Arrivederci.");
					break;
				}
				default:{
					System.out.println("Azione non valida. Riprovare.\n");
					//break;
				}
			}
		}while(!scelta.equalsIgnoreCase("5"));

		in.close();
	}
	
	public static String visualizzaContatti(String scelta, List<Contatto> recordContatti) {
		
		String changePage = "";
		int i = 0, j;
		int notValid = 0;
		do{ 							
			j = i + 50;
			if(notValid != -1) {
					DBClient.printContacts(recordContatti, i, j);
			}
			if(j+50 > recordContatti.size()) {
				System.out.println("Fine rubrica.");
				break;
			}
			System.out.println("\nPer visualizzare la pagina successiva: premere INVIO.");
			System.out.println("Per visualizzare la pagina precedente: premere < .");
			System.out.println("Per tornare al menu principale: premere 0.");
			System.out.println("Per terminare il programma: premere 5.");
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
			}else if(changePage.equalsIgnoreCase("5")) {
				scelta = "5";
				System.out.println("Arrivederci.");
				break;
			}else {
				System.out.println("Azione non valida. Riprovare.");
				notValid = -1;
			}
			
		}while( j < recordContatti.size() && !changePage.equalsIgnoreCase("0") && !changePage.equalsIgnoreCase("5"));
	
		return scelta;
	}
	
	public static void searchContact() {
		
		String sceltaSearch;
		do{
			System.out.println("1 - Ricercare un contatto per nome/cognome/telefono/email\n2 - Ricercare un conatto tramite identificativo numerico (id).");
			System.out.println("> ");
			sceltaSearch = in.nextLine();
			if(Integer.parseInt(sceltaSearch)!=1 && Integer.parseInt(sceltaSearch)!=2) {
				System.out.println("Azione non valida. Riprovare.");
			}
		}while(Integer.parseInt(sceltaSearch)!=1 && Integer.parseInt(sceltaSearch)!=2);
		
		
		if(Integer.parseInt(sceltaSearch) == 1) {
			String nome ="", cognome="", telefono="", email="";
			System.out.println("Digitare contatto da ricercare: ");
			System.out.print("nome : ");
			nome = in.nextLine();
			System.out.print("cognome : ");
			cognome = in.nextLine();
			System.out.print("telefono : ");
			telefono = in.nextLine();
			System.out.print("email : ");
			email = in.nextLine();
			System.out.println();
			
			HDBtools.selectContacts(nome, cognome, telefono, email);
			System.out.println();
			
		}else { //if(Integer.parseInt(sceltaSearch) == 2) {
			System.out.println("Digitare l'id corrispondente al contatto da ricercare.");
			System.out.println("> ");
			String id = "";
			do{
				try{		
				 id = in.nextLine();
				}catch(NumberFormatException e) {
					System.out.println("Il valore inserito, non è un numero. Riprovare.");
					id = "";
				}
			}while(id.equals(""));
			HDBtools.selectContacts(Integer.parseInt(id));
			System.out.println();
		}
		
	}
	
	public static void modifyContact() {
		String nome ="", cognome="", telefono="", email="";
		System.out.println("Digitare l'identificativo numerico del contatto da modificare e le modifiche da apportare: ");
		System.out.print("Numero contatto : ");
		System.out.print("> ");
		String id = "";
		do{
			try{		
			 id = in.nextLine();
			}catch(NumberFormatException e) {
				System.out.println("Il valore inserito, non è un numero. Riprovare.");
				id = "";
			}
		}while(id.equals(""));
		//System.out.print("Inserire le modifiche da apportare.");
		
		System.out.print("nome : ");
		nome = in.nextLine();						
		System.out.print("cognome : ");
		cognome = in.nextLine();
		System.out.print("telefono : ");
		telefono = in.nextLine();
		System.out.print("email : ");
		email = in.nextLine();
		System.out.println();
		
		
		HDBtools.update(Integer.parseInt(id), cognome, nome, telefono, email);
		System.out.println("Contatto modificato con successo.\n");
	}
	public static void deleteContact() {
		System.out.println("Digitare l'identificativo numerico del contatto da eliminare: ");
		System.out.print("> ");
		String id = "";
		do{
			try{		
			 id = in.nextLine();
			}catch(NumberFormatException e) {
				System.out.println("Il valore inserito, non è un numero. Riprovare.");
				id = "";
			}
		}while(id.equals(""));
		HDBtools.deleteContact(Integer.parseInt(id));
		System.out.println("Contatto eliminato con successo.\n");
	}
	public static void insertContact() {
		String nome ="", cognome="", telefono="", email="";
		System.out.println("Digitare i campi del contatto da inserire: ");				
		System.out.print("nome : ");
		nome = in.nextLine();					
		System.out.print("cognome : ");
		cognome = in.nextLine();
		System.out.print("telefono : ");
		telefono = in.nextLine();
		System.out.print("email : ");
		email = in.nextLine();
		System.out.println();
		
		HDBtools.insertContact(cognome, nome, telefono, email);
		System.out.println("Contatto inserito con successo.\n");
	}
	
	public static void exportRubrica(List<Contatto> recordContatti) {
		//Scanner in = new Scanner(System.in);
		System.out.println("Inserire il nome del file su cui scrivere i contatti della rubrica.");
		String fileName = in.nextLine();
		String estensione = "";
		do {
			System.out.println("Specificare l'estensione del file desiderato fra le due tipologie seguenti:\n .csv\t.xml");
			estensione = in.nextLine();
			if(!estensione.endsWith("csv") && !estensione.endsWith("xml")) {
				System.out.println("Estensione del file non disponibile. Riprovare.");
				estensione = "";
			}
		}while(estensione.equalsIgnoreCase(""));
		
//		estensione = !estensione.contains(".") ? estensione : "." + estensione;
		if(!estensione.contains(".")) {
			estensione = "." + estensione;
		}
		
		
		if(estensione.endsWith("csv")) {
			try{
				CsvParser.writeContattiCsv("C:\\temp\\"+fileName+estensione, "COGNOME;NOME;TELEFONO;EMAIL", recordContatti);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(estensione.endsWith("xml")) {
			try{
				XmlParser.writeContattiXml("C:\\temp\\"+fileName+estensione, recordContatti);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("File creato con successo.\n");
	}
}
