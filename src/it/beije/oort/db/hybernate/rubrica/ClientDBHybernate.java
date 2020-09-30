package it.beije.oort.db.hybernate.rubrica;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import it.beije.oort.rubrica.Contatto;

public class ClientDBHybernate {
	
	//Variabili
	static ArrayList<Contatto> contatto = new ArrayList<>();
	static Scanner scan = new Scanner(System.in);
	static String scelta = "";
	static boolean flag = true;
	public static String pathFile = "/tempDB/rubrica_from_client_hibernate.csv";
	
	//Metodo di inizio del programma che stampa il menù per la selezione delle operazioni possibili sul Client_Scanner_Hibernate
	public static void myMenu() throws IOException, SQLException {
		System.out.println("\t \t \t \t \t <[Client_Scanner_Hibernate a linea di comando]> <[v_1.0]> \n");
		System.out.println("Selezionare tramite inserimento del numero l'opzione desiderata tra le seguenti: ");
		do {
		System.out.println("1) Inserire un nuovo contatto! (Inserire almeno un campo)");
		System.out.println("2) Aggiornare un contatto già esistente!");
		System.out.println("3) Cancellare un contatto già esistente!");
		System.out.println("4) Ricerca utenza nel DB!");
		System.out.println("5) Export DB!");
		System.out.println("6) Uscire dallo scanner!");
		System.out.println("N.B. " + " Per poter aggiornare (2) o cancellare (3) bisogna conoscere l'ID del contatto, si consiglia di fare prima una ricerca(4)");
		System.out.print("Digitare la scelta: ");
		scelta = scan.nextLine();
		if(scelta.equals("1")) {
			MethodsForHB.insert();
		}else {
			if(scelta.equals("2")) { 
				MethodsForHB.edit();		
			}else {
				if(scelta.equals("3")) { 
					MethodsForHB.delete();
				}else{
					if(scelta.equals("4")) {
						MethodsForHB.show();
					}else {
						if(scelta.contentEquals("5")) {
							MethodsForHB.ExportDB(pathFile);
						}else {								
							if(scelta.contentEquals("6")) {
								System.out.println("Chiusura in corso..");
								System.out.println("Scanner closed!");
								flag = false;
							}else {
							System.out.println("ATTENZIONE! Scelta non valida! Ripetere l'operazione..");
							flag = true;
							}
						}
					}
				}
			}
		}
		System.out.println();
		}while(flag);
}
	
	//Metodo MAIN 
	public static void main (String[] args) throws IOException, SQLException {
		myMenu();
	}

}
