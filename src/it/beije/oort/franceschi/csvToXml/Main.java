package it.beije.oort.franceschi.csvToXml;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) {
		System.out.println("Programma avviato.");
		System.out.println("Conversione da CSV a XML.");
		InputManager im = new InputManager();
		for (int i = 0; i < im.getInputAmount(); i++) {
			CSVParser r = new CSVParser(im.getNextInputPath());
			XMLWriter.writeList(r.creaListaContatti(), im.getNextOutputPath());
		}
		System.out.println("Conversione da CSV a XML conclusa.");
		System.out.println("Conversione da XML a CSV.");
		for (int i = 0; i < im.getInputAmount(); i++) {
			try {
				CSVWriter.writeCSV(XMLParser.parseXML(im.getNextInputPathReverse()), im.getNextOuputPathReverse());
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		System.out.println("Conversione da XML a CSV conclusa.");
		System.out.println("Programma terminato.");
		/*
		 * TODO aggiungere input dall'utente per definire il divisore e l'eventuale
		 * presenza delle virgolette Virgolette: chiede all'utente se ci sono o no, in
		 * caso di sì attiva boolean che elimina il primo e l'ultimo carattere da ogni
		 * parola trimmata nel ciclo Divisore: semplicemente salvare in una variabile
		 * char o String il divisore immesso dall'utente e usarlo nel trim
		 */
	}
}