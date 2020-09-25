package it.beije.oort.gregori.csvxml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import it.beije.oort.gregori.parser.*;
import it.beije.oort.rubrica.Contatto;

public class UpdateFile {
	
	public static void fromFileToFile(File sourceFile, File destinationFile) throws IOException, ParserConfigurationException, SAXException, TransformerException {
		String sourceFilePath = sourceFile.toString();
		String destinationFilePath = destinationFile.toString();
		List<Contatto> contatti = new ArrayList<>();
		
		String extension1 = sourceFilePath.trim().substring(sourceFilePath.length() - 4);
		String extension2 = destinationFilePath.trim().substring(destinationFilePath.length() - 4);
		
		if(extension1.equals(".csv")) {
			contatti = ReaderCsv.readContatti(sourceFile);
		}
		else if (extension1.equals(".xml")){
			contatti = ReaderXml.readContatti(sourceFile);
		}
		
		if(extension2.equals(".csv")) {
			if(destinationFile.exists()) {
				List<Contatto> oldContatti = new ArrayList<>(ReaderCsv.readContatti(destinationFile));
				contatti.addAll(oldContatti);
			}
			//added sort
			MyComparator comparator = new MyComparator();
			Collections.sort(contatti, comparator);
			WriterCsv.writeContatti(contatti, destinationFile);
		}
		else if (extension2.equals(".xml")){
			if(destinationFile.exists()) {
				List<Contatto> oldContatti = ReaderXml.readContatti(destinationFile);
				contatti.addAll(oldContatti);
			}
			//added sort
			MyComparator comparator = new MyComparator();
			Collections.sort(contatti, comparator);
			WriterXml.writeContatti(contatti, destinationFile);
		}
	}
	
	public static void menu() throws IOException, ParserConfigurationException, TransformerException {
		ArrayList<Contatto> contattiLocali = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		char myChar = ' ';
		
		System.out.println("----------------Rubrica----------------");		
		do {
			Contatto contatto = inserisciContatto();
			int scelta = options();
			switch (scelta) {
			case 1:
				// Scarta
				break;
			case 2:
				// Modifica
				modifica(contattiLocali);
				System.out.println("Contatto modificato correttamente!");
				break;
			case 3:
				contattiLocali.add(contatto);
				System.out.println("Contatto aggiunto correttamente!");
				break;
			case 4:
				// scrivi su file;
				int sceltaScrittura = scrivi(contattiLocali);
				if (sceltaScrittura == 1) {
					// csv
					WriterCsv.writeContatti(contattiLocali, new File("/temp/generated-csv.csv"));
					System.out.println("File cvs salvato correttamente!");
				}
				if (sceltaScrittura == 2) {
					// xml
					WriterXml.writeContatti(contattiLocali, new File("/temp/generated-xml.xml"));
					System.out.println("File xml salvato correttamente!");
				}
				if (sceltaScrittura == 3) {
					// db
					WriterDb.writeContatti(contattiLocali);
					System.out.println("Contatti inseriti correttamente sul database!");
				}
			default:
				break;
			}
			System.out.println("Vuoi inserire un altro contatto? (n per terminare il programma)");	
			myChar = sc.nextLine().charAt(0);
		} while(myChar != 'n');
		
		System.out.println("Arrivederci!");
		sc.close();
	}
	
	private static int options() {
		Scanner sc = new Scanner(System.in);
		int scelta;
		do {
			System.out.println("Cosa fare con il contatto?");
			System.out.println("1) Scarta");
			System.out.println("2) Modifica");
			System.out.println("3) Aggiungi alla lista");
			System.out.println("4) Salva su file");
			scelta = sc.nextInt();
			if (scelta < 1 || scelta > 4) {
				System.out.println("ERRORE: scelta non consentita!");
				System.out.println();
			}
		} while(scelta < 1 || scelta > 3);
				
//		sc.close();
		return scelta;
	}
	
	private static void modifica(List<Contatto> contatti) {
		Scanner sc = new Scanner(System.in);
		
		if(contatti.size() > 0) {
			for (int i = 0; i < contatti.size(); i++) {
				System.out.println(i + ") " + contatti.get(i));
			}
			int value = 0;
			do {
				System.out.print("Scegliere l'indice del contatto da modificare: ");
				value = sc.nextInt();
				if (value < 0 || value > contatti.size() - 1) {
					System.out.println("ERRORE: inserire un indice esistente!");
				}
			} while (value < 0 || value > contatti.size() - 1);
			System.out.println();
			System.out.println(contatti.get(value));
			Contatto contatto = inserisciContatto();
			contatti.add(contatto);
		}
		else {
			System.out.println("Non esistono contatti da modificare!");
		}
		
//		sc.close();		
	}
	
	
	private static Contatto inserisciContatto() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Inserimento di un contatto.");
		Contatto contatto = new Contatto();
		System.out.print("Nome: ");
		contatto.setNome(sc.nextLine());
		System.out.print("Cognome: ");
		contatto.setCognome(sc.nextLine());
		System.out.print("Telefono: ");
		contatto.setTelefono(sc.nextLine());
		System.out.print("Email: ");
		contatto.setEmail(sc.nextLine());
		int counter = 0;
		if(contatto.getNome().equals(" ")) { counter++; }
		if(contatto.getCognome().equals(" ")) { counter++; }
		if(contatto.getEmail().equals(" ")) { counter++; }
		if(contatto.getTelefono().equals(" ")) { counter++; }
		if(counter == 4) {
			System.out.println("ERRORE: Almeno un campo deve essere inserito!");
			inserisciContatto();
		}
		System.out.println(contatto);
		System.out.println("Contatto inserito correttamente!");
		System.out.println();
		
//		sc.close();
		
		return contatto;
	}
	
	
	private static int scrivi(List<Contatto> contatti) {
		
		Scanner sc = new Scanner(System.in);
		
		int scelta = 0;
		do {
			System.out.println("Come vuoi salvare il file?");
			System.out.println("1) Formato .csv");
			System.out.println("2) Formato .xml");
			System.out.println("3) Sul database");
		} while(scelta < 0 || scelta > 3);
		
//		sc.close();
		
		return scelta;
	}
	

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TransformerException {
//		File source = new File("/temp/rubrica_gregori.csv");
//		File dest = new File("/temp/generated.xml");
		
//		UpdateFile.fromFileToFile(source, dest);
		UpdateFile.menu();
	}

}
