package it.beije.oort.madonia.rubrica;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class RubricaManager {
	
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		File file = new File("/temp/rubrica/rubrica_manager.xml");
		
		List<Contatto> contatti = file.exists() ? ParserXmlRubrica.readContatti(file) : new ArrayList<Contatto>();
		System.out.println("Benvenuto alla modifica della rubrica, inserisci una voce contatto.");
		
		Contatto contatto = RubricaManager.chiediContatto();
		System.out.println(contatto);
		
		System.out.println("Vuoi aggiungere questo contatto o sostituire un contatto? Oppure annullare l'operazione? a/s/n");
		switch (sc.nextLine()) {
		case "a":
			contatti.add(contatto);
			break;
		case "m":
			break;
		default:
			break;
		}
		
		WriterXmlRubrica.writeXmlFile(contatti, file);
	}

	private static Contatto chiediContatto() {
		Contatto contatto = new Contatto();
		
		while(!RubricaManager.isContattoVuoto(contatto)) {
			System.out.print("Nome: ");
			contatto.setNome(sc.nextLine());
			System.out.print("Cognome: ");
			contatto.setCognome(sc.nextLine());
			System.out.print("Telefono: ");
			contatto.setTelefono(sc.nextLine());
			System.out.print("Email: ");
			contatto.setEmail(sc.nextLine());
			
			if (!RubricaManager.isContattoVuoto(contatto)) {
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
		return nome != null && nome.length() > 0
				|| cognome != null && cognome.length() > 0
				|| telefono != null && telefono.length() > 0
				|| email != null && email.length() > 0;
	}

}
