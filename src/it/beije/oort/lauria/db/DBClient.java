package it.beije.oort.lauria.db;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import it.beije.oort.lauria.Contatto;
import it.beije.oort.lauria.CsvParser;
import it.beije.oort.lauria.XmlParser;

public class DBClient {
	
	private static final String PATH_FILES = "C:\\Users\\Padawan06\\Documenti\\temp\\";
	public static final String TABLE_NAME = "rubrica";
	public static final Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		
		int scelta  ; 
			do {
			DBClient.menu();
			// TODO gestire scelta: deve digitare un numero, non altro.
			
				scelta = in.nextInt();
//			}catch(InputMismatchException e) {
//				//System.out.println("Azione non valida.");
//			}
			System.out.println();
			in.nextLine();
			
			switch(scelta) {
				case 1:{
					int sceltaVisual;
					do {
						DBClient.menuVisualizza();
						// TODO gestire sceltaVisual: deve digitare un numero, non altro.
						sceltaVisual = in.nextInt();
						System.out.println();
						in.nextLine();
						if(sceltaVisual > 2) {
							System.out.println("Azione non valida. Riprovare.");
						}
					}while(sceltaVisual > 2);
					switch(sceltaVisual) {
						case 1:{
							// TODO possibilità di tornare indietro con le pagine
							List<Contatto> recordContatti = new ArrayList<Contatto>();						
							DBtools.preparedSelect(TABLE_NAME, recordContatti);
							scelta = DBClient.visualizzaContatti(scelta, recordContatti);
							break;
						}
						case 2:{
							DBClient.searchContact();
							break;
						}
	//					default:{
	//						System.out.println("Azione non valida. Riprovare.");
	//						//break;
	//					}
					}
					
					break;
				}
				case 2:{
					int sceltaMod;
					do {
						System.out.println("Digitare l'azione desiderata.");
						System.out.println("1 - Modificare un contatto.\n2 - Cancellare un contatto.");
						System.out.print("> ");
						// TODO gestire sceltaMod: deve digitare un numero, non altro.
						sceltaMod = in.nextInt();
						System.out.println();
						if(sceltaMod > 2) {
							System.out.println("Azione non valida. Riprovare.");
						}
					}while(sceltaMod > 2);
					switch(sceltaMod) {
						case 1:{
							DBClient.modifyContact();
							break;
						}
						case 2:{
							DBClient.deleteContact();
							break;
						}
	//					default :{
	//						System.out.println("Azione non valida.");
	//						break;
	//					}
					}
					break;
				}
				case 3:{
					DBClient.insertContact();
					break;
				}
				case 4:{
					List<Contatto> recordContatti = new ArrayList<Contatto>();				
					DBtools.preparedSelect(TABLE_NAME, recordContatti);
					DBClient.exportRubrica(recordContatti);
					break;
				}
				case 5:{
					System.out.println("Arrivederci.");
					break;
				}
				default:{
					System.out.println("Azione non valida. Riprovare.\n");
					//break;
				}
			}
		}while(scelta != 5);

		in.close();
	}
	
	public static void menu() {
		System.out.println("Digitare l'azione desiderata.");
		System.out.println("1 - Visualizzazione contatti.\n2 - Modifica/cancellazione contatti.\n3 - Inserimento di un contatto\n4 - Export di contatti.\n5 - Esci.");
		System.out.print("> ");
	}
	
	public static void menuVisualizza() {
		System.out.println("Digitare l'azione desiderata.");
		System.out.println("1 - Visualizzazione di tutti i contatti.\n2 - Ricerca contatti.");
		System.out.print("> ");
	}
	
	public static void printContacts(List<Contatto> list, int start, int end) {
		
		for(int i = start; i < end; i++) {
			System.out.print(list.get(i).getId() + " - ");
			System.out.print("nome : "+ list.get(i).getNome() + " - ");
			System.out.print("cognome : "+ list.get(i).getCognome() + " - ");
			System.out.print("telefono : "+ list.get(i).getTelefono() + " - ");
			System.out.println("email : "+ list.get(i).getEmail());
			
		}
	}
	
	public static int visualizzaContatti(int scelta, List<Contatto> recordContatti) {
		//Scanner in = new Scanner(System.in);
		String changePage = "";
		int i = 0, j;
		int notValid = 0;
		do{ 							
			j = i + 50;
			if(notValid != -1) {
				DBClient.printContacts(recordContatti, i, j);
			}
			System.out.println("\nPer visualizzare la pagina successiva: premere INVIO.");
			System.out.println("Per tornare al menu principale: premere 0.");
			System.out.println("Per terminare il programma: premere 5.");
			System.out.print("> ");
			changePage = in.nextLine();		
			notValid = 0;
			if(changePage.equalsIgnoreCase("")) {								
				i = j;
			}
			else if(changePage.equalsIgnoreCase("0")){
				break;
			}else if(changePage.equalsIgnoreCase("5")) {
				scelta = 5;
				System.out.println("Arrivederci.");
				break;
			}else {
				System.out.println("Azione non valida. Riprovare.");
				notValid = -1;
			}
		}while( j < recordContatti.size() && !changePage.equalsIgnoreCase("0") && !changePage.equalsIgnoreCase("5"));
	
		return scelta;
		//in.close();
	}
	public static void searchContact() {
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

		DBtools.preparedSelect(TABLE_NAME, cognome, nome, telefono, email);
		System.out.println();
	}
	public static void modifyContact() {
		String nome ="", cognome="", telefono="", email="";
		System.out.println("Digitare l'identificativo numerico del contatto da modificare e le modifiche da apportare: ");
		System.out.print("Numero contatto : ");
		System.out.print("> ");
		// TODO gestire id: deve digitare un numero, non altro.
		int id = in.nextInt();
		in.nextLine();
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
		
		DBtools.updateSelect(TABLE_NAME, cognome, nome, telefono, email, id);
		System.out.println("Contatto modificato con successo.\n");
	}
	public static void deleteContact() {
		System.out.println("Digitare l'identificativo numerico del contatto da eliminare: ");
		System.out.print("> ");
		// TODO gestire id: deve digitare un numero, non altro.
		int id = in.nextInt();
		in.nextLine();
		DBtools.deleteSelect(TABLE_NAME, id);
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
		
		DBtools.preparedInsert(TABLE_NAME, cognome, nome, telefono, email);
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
		
		// TODO chiedere dove salvare il file e/o dire dove viene salvato
		
		if(estensione.endsWith("csv")) {
			try{
				CsvParser.writeContattiCsv(PATH_FILES+fileName+estensione, "COGNOME;NOME;TELEFONO;EMAIL", recordContatti);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(estensione.endsWith("xml")) {
			try{
				XmlParser.writeContattiXml(PATH_FILES+fileName+estensione, recordContatti);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("File creato con successo.\n");
	}
}
