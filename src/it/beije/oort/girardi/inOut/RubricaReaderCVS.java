package it.beije.oort.girardi.inOut;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.beije.oort.rubrica.Contatto;


public class RubricaReaderCVS {
	
	private static final String PATH_FILES = "C:\\Users\\Padawan05\\Desktop\\file_testo\\";

// ------------ METODI ------------
	
//riporta in una lista di contatti il contenuto di un file CSV in cui la prima
//riga fornisce l'ordine degli attributi che andranno a formare il contatto.
	public static List<Contatto> getContatto(File file) throws IOException {
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
			listaContatti.add(new Contatto(campi[a],campi[b],campi[c],campi[d]));
			System.gc();	//garbage collector
		}
		
		return listaContatti;
	}

	
	
// -------------- MAIN -----------------
	public static void main (String[] args) throws IOException {
		
		File file = new File(PATH_FILES + "rubrica_lauria.csv");
		List<Contatto> listaContatti = new ArrayList<Contatto>();
		if (file.exists()) 
			System.out.println("esiste, lo carico... ");
		
		listaContatti = getContatto(file);
		int i = 0;
		System.out.println(listaContatti.size());
		System.out.println(listaContatti.get(i).toString());

	
	}
}
