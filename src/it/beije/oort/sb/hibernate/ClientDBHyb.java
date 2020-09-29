package it.beije.oort.sb.hibernate;

import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;



public class ClientDBHyb {
	static Scanner sc = new Scanner(System.in);

	//metodo effettivo che racchiude i vari metodi di HDBtools in un unico, così da poterlo utilizzare in altre classi comodamente
	public static void menu() throws DOMException, IOException, ParserConfigurationException, TransformerException, SAXException {
		int size = HDBtools.numElementi();//messo in modo da far partire la config
		System.out.println("Buongiorno, questo è un comodo tool per importare e/o esportare contatti da un DB \n");	
		System.out.println("Attualmente ci sono " + size + " contatti nel DataBase\n");
		String concl = "";
		while(!concl.equalsIgnoreCase("quit")) {
			System.out.println("Cosa vuoi fare? Scegli tra: \n1)Ricerca:\t per visualizzare tutti i contatti con determinate caratteristiche; \n2)Modifica:\t per modificare un contatto già presente nel database;");
			System.out.println("3)Cancella:\t per cancellare un contatto presente nel database; \n4)Inserisci:\t per inserire un nuovo contatto in fondo alla rubrica del database;");
			System.out.println("5)Export:\t per salvare la rubrica su file (xml o csv); \n6)Import:\t per importare una rubrica; \n7)Quit:\t\t per concludere la sessione in corso.");
			System.out.println("\nDigita il numero corrispondente all'azione desiderata e premi INVIO");
			switch(sc.nextLine().toLowerCase()) {
			case "1" :
				HDBtools.ricerca();
				break;
			case "4" :
				HDBtools.insert();
				System.out.println("Contatto inserito in fondo alla rubrica!\n");
				break;
			case "2" :
				HDBtools.update();
				System.out.println("Contatto modificato!\n");
				break;
			case "5" :
				HDBtools.export();
				System.out.println("Rubrica salvata!\n");
				break;
			case "3" :
				HDBtools.delete();
				break;
			case "6":
				HDBtools.importer();
				break;
			case "7" :
				concl = "quit";
				System.out.println("Grazie per averci usato!");
				break;
			default :
				System.out.println("Non ho riconosciuto il comando");
			}

		}
		sc.close();
}
	
}
