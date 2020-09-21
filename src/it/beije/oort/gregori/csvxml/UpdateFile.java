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
	
	public static void menu() {
		ArrayList<Contatto> contattiLocali = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("----------------Rubrica----------------");
		do {
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
				continue; // lo so che è un abominio MA è veloce
			}
			else {
				System.out.println(contatto);
				System.out.println("Contatto inserito correttamente!");
				System.out.println();
			}
			
			int scelta = options();
			switch(scelta) {
				case 1:
					// Scarta
					break;
				case 2:
					// Modifica
					break;
				case 3:
					// contatti.add(contatto);
					break;
				default:
					break;
			}
			
			
		} while(true);
	}
	
	private static int options() {
		Scanner sc = new Scanner(System.in);
		int scelta;
		do {
			System.out.println("Cosa fare con il contatto?");
			System.out.println("1) Scarta");
			System.out.println("2) Modifica");
			System.out.println("3) Aggiungi a file");
			scelta = sc.nextInt();
			if (scelta < 1 || scelta > 3) {
				System.out.println("ERRORE: scelta non consentita!");
				System.out.println();
			}
		} while(scelta < 1 || scelta > 3);
		
		sc.close();
		return scelta;
	}

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TransformerException {
//		File source = new File("/temp/rubrica_gregori.csv");
//		File dest = new File("/temp/generated.xml");
		
//		UpdateFile.fromFileToFile(source, dest);
		UpdateFile.menu();
	}

}
