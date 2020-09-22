package it.beije.oort.girardi.inOut;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import it.beije.oort.rubrica.Contatto;


public class RubricaCSV {
	
	private static final String PATH_FILES = "C:\\Users\\Padawan05\\Desktop\\file_testo\\";
	

// ------------ METODI ------------
	
//USARE QUESTO: riporta in una lista di contatti il contenuto di un file CSV in cui la 
//prima riga fornisce l'ordine degli attributi che andranno a formare il contatto.
	public static List<Contatto> getListContatti(File file) throws IOException {
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<Contatto> listaContatti = new ArrayList<Contatto>();
		
		//lettura riga iniziale (intestazione):
		List<String> intestazione = Arrays.asList(bufferedReader.readLine().split(";"));
		
		// riporto i contatti nella lista
		while (bufferedReader.ready()) {
			String[] campi = bufferedReader.readLine().split(";");			
			listaContatti.add(getContatto(intestazione, campi));
		}
		return listaContatti;
	}
	
//data l'intestazione della rubrica e l'array di stringhe di una riga del csv
//restituisce il Contatto.
	private static Contatto getContatto (List<String> intestazione, String[] campi) {
		Contatto contatto = new Contatto();
		for(int i = 0; i < campi.length; i++) {
			switch (intestazione.get(i).trim().toLowerCase()) {
			case "nome":
				contatto.setNome(campi[i]);
				break;
			case "cognome":
				contatto.setCognome(campi[i]);
				break;
			case "telefono":
				contatto.setTelefono(campi[i]);
				break;
			case "email":
			case "e-mail":
				contatto.setEmail(campi[i]);
				break;
			}
		}
		return contatto;
	}
	
//	riporta in una lista di contatti il contenuto di un file CSV in cui la prima
//	riga fornisce l'ordine degli attributi che andranno a formare il contatto.
//	N.B.: è leggermente più veloce ma se la rubrica è incompleta potrebbe dare
//	dei problemi del tipo: campi.size() < 4 o addirittura 0.
		public static List<Contatto> getContatti(File file) throws IOException {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			List<Contatto> listaContatti = new ArrayList<Contatto>();
			
		//lettura riga iniziale e ordine degli attributi di contatto:
			String riga = bufferedReader.readLine();
			String[] campi = riga.split(";"); 
			int a = 0, b = 0, c = 0, d = 0;
			for (int i = 0; i < 4; i++) {
				// a:nome, b:cognome, c:telefono, d:email
				switch (campi[i].trim().toLowerCase()) {
				case "nome":
					a = i;
					break;
				case "cognome":
					b = i;
					break;
				case "telefono":
					c = i;
					break;
				case "email":
				case "e-mail":
					d = i;
					break;
				}
			}
		// riporto i contatti nella lista
			while (bufferedReader.ready()) {
				campi = bufferedReader.readLine().split(";");			
				listaContatti.add(new Contatto(campi[a],campi[b], campi[c],campi[d]));
			}
			return listaContatti;
		}
	
	
//metodo che data un contatto ti ridà la riga in formato csv dentro una String
	public static String contattoRigaCSV (Contatto contatto) {
		StringBuilder riga = new StringBuilder();
		riga.append(contatto.getCognome()).append(';')
			.append(contatto.getNome()).append(';')
			.append(contatto.getEmail()).append(';')
			.append(contatto.getTelefono());
		
		return riga.toString();
	}

// data una List di Contatti crea e scrive un file csv nel pathfile: 
	public static void writeContatti(List<Contatto> contatti, String pathfile) 
		throws IOException {
		File output = new File(pathfile);
		FileWriter writer = new FileWriter(output);
		
		writer.write("COGNOME;NOME;E-MAIL;TELEFONO");
		for (Contatto contatto : contatti) {
			writer.write("\n");
			writer.write(contattoRigaCSV(contatto));
		}
		writer.flush();
		writer.close();
		System.out.println("Rubrica completata!");	
	}
	
	
// -------------- MAIN -----------------
//da un file csv scrivo un file xml
	public static void main (String[] args) 
			throws IOException, ParserConfigurationException, TransformerException {
		//lettura:
		File file = new File(PATH_FILES + "rubrica_lauria.csv");
		if (file.exists()) 
			System.out.println("esiste, lo carico... ");
		else
			System.out.println("il file che si vuole caricare non esiste");
		//metodo per la List di contatti
		List<Contatto> listaContatti = getListContatti(file);  
//		List<Contatto> listaContatti = getContatti(file);  
		
		//scrittura file xml tramite il metodo implementato
		RubricaXML.writeContatti (listaContatti, PATH_FILES + "rubrica_lauria.xml");
	
	}
}
