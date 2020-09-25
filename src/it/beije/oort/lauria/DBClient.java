package it.beije.oort.lauria;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DBClient {
	
	private static final String PATH_FILES = "C:\\Users\\Padawan06\\Documenti\\temp\\";
	public static final String TABLE_NAME = "rubrica";

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		DBClient.menuFirst();
		
		int scelta = in.nextInt();
		System.out.println();
		in.nextLine();
		switch(scelta) {
			case 1:{
				DBClient.menuVisualizza();
				int sceltaVisual = in.nextInt();
				System.out.println();
				in.nextLine();
				switch(sceltaVisual) {
					case 1:{
						List<Contatto> recordContatti = new ArrayList<Contatto>();						
						DBtools.preparedSelect(TABLE_NAME, recordContatti);
						String changePage = "";
						int i = 0, j;
						do{ 							
							j = i + 50;
							printContacts(recordContatti, i, j);
							System.out.println("Per visualizzare la pagina successiva, digitare NEXT.");
							System.out.println("Per uscire, digitare ESC.");
							System.out.print("> ");
							changePage = in.nextLine();														
							if(changePage.equalsIgnoreCase("next")) {								
								i = j;
							}
							else if(changePage.equalsIgnoreCase("esc")){
								break;
							}											
						}while( j < recordContatti.size() && !changePage.equalsIgnoreCase("esc") );
						break;
					}
					case 2:{
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
					}
					default:{
						System.out.println("Azione non valida.");
						break;
					}
				}
				
			}
			case 2:{
				System.out.println("Per modificare un contatto, digitare 1.");
				System.out.println("Per cancellare un contatto, digitare 2.");
				int sceltaMod = in.nextInt();
				System.out.println();
				switch(sceltaMod) {
				case 1:{
					String nome ="", cognome="", telefono="", email="";
					System.out.println("Digitare l'identificativo (id) del contatto da modificare e le modifiche da apportare: ");
					System.out.print("> ");
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
					break;
				}
				case 2:{
					System.out.println("Digitare l'identificativo (id) del contatto da eliminare: ");
					System.out.print("> ");
					int id = in.nextInt();
					in.nextLine();
					DBtools.deleteSelect(TABLE_NAME, id);
					System.out.println("Contatto eliminato con successo.");
					break;
				}
				default :{
					System.out.println("Azione non valida.");
					break;
				}
				}
			}
			case 3:{
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
				System.out.println("Contatto inserito con successo.");
				break;
			}
			case 4:{
				String tableName = "rubrica";
				
				List<Contatto> recordContatti = new ArrayList<Contatto>();
				
				DBtools.preparedSelect(tableName, recordContatti);
				
				System.out.println("Inserire il nome del file su cui scrivere i contatti della rubrica.");
				String fileName = in.nextLine();
				System.out.println("Specificare l'estensione del file desiderato tra i due tipi seguenti: .csv\t.xml");
				String estensione = in.nextLine();
//				System.out.println("Specificare l'ordine con cui scrivere i campi dei contatti, separati dal carattere ; .");
//				String intestazione = in.nextLine();
				if(estensione.endsWith("csv")) {
					try{
						CsvParser.writeContattiCsv(fileName+estensione, "COGNOME;NOME;TELEFONO;EMAIL", recordContatti);
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
				}else {
					System.out.println("Estensione del file non disponibile.");
				}
				break;
			}
			default:{
				System.out.println("Azione non valida.");
				break;
			}
		}
		
		in.close();
	}
	
	public static void menuFirst() {
		System.out.println("Digitare l'azione desiderata.");
		System.out.println("1 - Visualizzazione contatti.\n2 - Modifica/cancellazione contatti.\n3 - Inserimento di un contatto\n4 - Export di contatti.");
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

}
