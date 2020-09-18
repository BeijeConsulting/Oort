package it.beije.oort.girardi.inOut;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import it.beije.oort.rubrica.Contatto;
import it.beije.oort.girardi.inOut.RubricaCSV;
import it.beije.oort.girardi.inOut.RubricaXML;

public class MixRubriche {
	
	private static String nome_sorgente = "rubrica_lauria.csv";
	private static String nome_destinazione = "rubrica_finale1.xml";
	
	private static final String PATH_SORGENTE = "C:\\Users\\Padawan05\\Desktop\\file_testo\\";
	private static final String PATH_DESTINAZIONE = "C:\\Users\\Padawan05\\Desktop\\file_testo\\";


// ------------ METODI ------------

// metodo che riconosce formato dei file in ingresso in base al nome
	public static String formatoFile (String filename) {
		int lunghezza = filename.length();
		String formato = filename.substring(lunghezza-4, lunghezza)
		return formato.toLowerCase();
	}

	
// -------------- MAIN -----------------
// prende due file in input, riconosce i formati dei file sorgente e destinatario e riporta 
// il sorgente nel file destinazione senza cancellarne il contenuto nel caso non fosse vuoto.
	public static void main (String[] args) 
			throws IOException, ParserConfigurationException, TransformerException {
		
	//parto dalla lettura del file sorgente: se esiste creo una List Contatti
		// lettura file sorgente
		File fileSorgente = new File(PATH_SORGENTE + nome_sorgente);
		
		if (!fileSorgente.exists()) {
			System.err.println("Il file " + nome_sorgente + " non può non esistere.");
		}
		
		String formato_sorgente = formatoFile(nome_sorgente);
		switch (formato_sorgente) {
		case ".csv" : 
			
			//metodo di RubricaCSV
			
			break;
		case ".xml" : 
			
			//metodo di RubricaXML
			
			break;
		default: 
			System.err.println("Il filename deve finire con .csv o con .xml")
		}
		
		
	//passo alla lettura del file destinazione: se esiste aggiungo i contatti alla List Contatti
		// lettura file destinazione
		File fileDestinazione = new File(PATH_DESTINAZIONE + nome_destinazione);
		if (fileDestinazione.exists()) 
			System.out.println("Il file " + nome_destinazione + " esiste, lo carico... ");
		else
			System.out.println("Il file " + nome_destinazione + " non esiste, lo creo...");
		
		// metodo che riconosce formato dei file in ingresso
		
		
		// metodo per la List di contatti da csv
		List<Contatto> listaContatti = RubricaCSV.getContatto(file);  
		
		// scrittura file xml tramite il metodo implementato
		RubricaXML.writeContatti (listaContatti, PATH_FILES + "rubrica_lauria.xml");
	
	}
}