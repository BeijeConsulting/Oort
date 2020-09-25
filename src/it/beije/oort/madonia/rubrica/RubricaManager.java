package it.beije.oort.madonia.rubrica;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import it.beije.oort.madonia.rubrica.db.DBRubrica;

public class RubricaManager {
	
	private static Scanner sc = new Scanner(System.in);
	private static List<Contatto> contatti;

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		File file;
		System.out.println("Benvenuto alla modifica della rubrica.");
		
		do {
			try {
				System.out.println("Inserire path del file di contatti");
				String path = sc.nextLine();
				file = new File(path);
				contatti = file.exists() ? prendiListaContatto(file) : new ArrayList<Contatto>();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				System.out.println("Formato file non riconosciuto.");
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("Qualcosa è andato storto e non riconosco il problema.");
				e.printStackTrace();
			}
		} while(contatti == null);
		
		boolean isSalva = false;
		while (!isSalva) {
			
			System.out.println("Inserisci una voce contatto.");
			Contatto contatto = RubricaManager.chiediContatto();
			
			boolean isAnnulla = false;
			boolean operazioneEseguita = true;
			String inputUtente = null;
			do {
				System.out.println(contatto);
				System.out.println("Vuoi aggiungere questo contatto o modificare un contatto? Oppure annullare l'operazione? (a/m/n)");
				System.out.println("Puoi anche salvare! (s)");
				inputUtente = sc.nextLine();
				switch (inputUtente.toLowerCase()) {
				case "a":
					operazioneEseguita = contatti.add(contatto);
					System.out.println("Contatto aggiunto.");
					break;
				case "m":
					operazioneEseguita = gestoreModifica(contatto);
					break;
				case "n":
					isAnnulla = true;
					System.out.println("Operazione annullata");
					break;
				case "s":
					isSalva = true;
					break;
				default:
					System.out.println("Input non riconosciuto, riprova.");
					break;
				}
			} while (!operazioneEseguita && !isAnnulla && !isSalva);
		}
		
		System.out.println("Seleziona in che modo salvare la lista contatti.");
		System.out.println("1: CSV - 2: XML - 3: DB - q: QUIT");
		
		String inputUtente = null;
		do {
			inputUtente = sc.nextLine();
			switch (inputUtente.toLowerCase()) {
			case "1":
				// TODO Write
				//WriterCsvRubrica.writeCsvFile(new String[] {"NOME","COGNOME","TELEFONO","EMAIL"}, contatti, file);
				System.out.println("File CSV creato!");
				break;
			case "2":
				// TODO Write
				//WriterXmlRubrica.writeXmlFile(contatti, file);
				System.out.println("File XML creato!");
				break;
			case "3":
				DBRubrica.importContatti(contatti);
				System.out.println("DB aggiornato!");
			case "q":
				
			default:
				System.out.println("Input non riconosciuto");
				break;
			}
		} while (inputUtente != null && !inputUtente.equalsIgnoreCase("q"));
		
		System.out.println("Programma terminato!");
	}
	
	private static List<Contatto> prendiListaContatto(File file) throws ParserConfigurationException, SAXException, IOException {
		String extension = getEstensione(file.getPath());
		switch (extension) {
		case "csv":
			return ParserCsvRubrica.creaListaContatti(file);
		case "xml":
			return ParserXmlRubrica.readContatti(file);
		default:
			throw new IllegalArgumentException("L'estensione non è supportata");
		}
	}

	private static Contatto chiediContatto() {
		Contatto contatto = new Contatto();
		
		while(RubricaManager.isContattoVuoto(contatto)) {
			System.out.print("Nome: ");
			contatto.setNome(sc.nextLine());
			System.out.print("Cognome: ");
			contatto.setCognome(sc.nextLine());
			System.out.print("Telefono: ");
			contatto.setTelefono(sc.nextLine());
			System.out.print("Email: ");
			contatto.setEmail(sc.nextLine());
			
			if (RubricaManager.isContattoVuoto(contatto)) {
				System.out.println("Attenzione, non hai inserito nessun campo. Per favore riprova.");
			}
		}
		return contatto;
	}
	
	private static boolean isContattoVuoto(Contatto contatto) {
		String nome = contatto.getNome();
		String cognome = contatto.getCognome();
		String telefono = contatto.getTelefono();
		String email = contatto.getEmail();
		return !(nome != null && nome.length() > 0
				|| cognome != null && cognome.length() > 0
				|| telefono != null && telefono.length() > 0
				|| email != null && email.length() > 0);
	}
	
	private static boolean gestoreModifica(Contatto contatto) {
		boolean successo = false;
		System.out.println("Seleziona con un numero il contatto che vuoi sostituire.");
		stampaContatti();
		int valoreUtente = -1;
		while(!(valoreUtente >= 0)) {
			try {
				String inputUtente = sc.nextLine();
				if (inputUtente.equalsIgnoreCase("n")) {
					System.out.println("Operazione annullata.");
					break;
				}
				valoreUtente = Integer.parseInt(inputUtente);
			} catch (NumberFormatException e) {
				System.out.println("Formato numero errato, riprova.");
			}
		}
		
		if (valoreUtente >= 0) {
			contatti.remove(valoreUtente);
			contatti.add(valoreUtente, contatto);
			System.out.println("Contatto modificato.");
			successo = true;
		} else {
			System.out.println("Contatto non modificato.");
		}
		
		return successo;
	}
	
	private static void stampaContatti() {
		for(int i = 0; i < contatti.size(); i++) {
			StringBuilder s = new StringBuilder();
			s.append(i).append(": ").append(contatti.get(i));
			System.out.println(s.toString());
		}
	}
	
	private static String getEstensione(String pathfile) {
		int punto = pathfile.lastIndexOf('.');
		return pathfile.substring(punto + 1).toLowerCase();
	}

}
