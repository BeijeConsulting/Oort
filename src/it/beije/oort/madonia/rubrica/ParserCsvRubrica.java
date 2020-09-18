package it.beije.oort.madonia.rubrica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParserCsvRubrica {
	
	public static List<Contatto> creaListaContatti(File fileCsv) throws IOException {
		List<Contatto> contattiRubrica = new ArrayList<Contatto>();
		
		FileReader fileReader = new FileReader(fileCsv);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		String[] colonne = new String[0];
		if (bufferedReader.ready()) {
			colonne = ParserCsvRubrica.ottieniColonne(bufferedReader.readLine());
		}
		
		while (bufferedReader.ready()) {
			String riga = bufferedReader.readLine();
			contattiRubrica.add( ParserCsvRubrica.ottieniContatto(riga, colonne));
		}
		
		fileReader.close();
		bufferedReader.close();
		
		return contattiRubrica;
	}
	
	public static List<Contatto> creaListaContatti(String pathfile) throws IOException {
		File fileCsv = new File(pathfile);
		return ParserCsvRubrica.creaListaContatti(fileCsv);
	}
	
	private static Contatto ottieniContatto(String rigaCsv, String[] colonne) {
		Contatto contatto = new Contatto();
		String[] campi = ParserCsvRubrica.creaArrayCampi(rigaCsv);
		for(int index = 0; index < colonne.length; index++) {
			if (colonne[index].equalsIgnoreCase("nome")) {
				contatto.setNome(campi[index]);
			} else if (colonne[index].equalsIgnoreCase("cognome")) {
				contatto.setCognome(campi[index]);
			} else if (colonne[index].equalsIgnoreCase("telefono")) {
				contatto.setTelefono(campi[index]);
			} else if (colonne[index].equalsIgnoreCase("email")) {
				contatto.setEmail(campi[index]);
			}
		}
		
		return contatto;
	}
	
	private static String[] ottieniColonne(String intestazioneCsv) {
		return intestazioneCsv.toLowerCase().split(";");
	}
	
	private static String[] creaArrayCampi(String rigaCsv) {
		StringBuilder s = new StringBuilder(rigaCsv);
		List<String> campi = new ArrayList<String>();
		while (s.length() > 0) {
			int indexSeparatore = s.indexOf(";");
			if (indexSeparatore < 0) {
				campi.add(s.toString());
				s.delete(0, s.length());
			} else if (indexSeparatore == 0) {
				campi.add("");
				if (indexSeparatore + 1 == s.length()) {
					campi.add("");
				}
				s.deleteCharAt(indexSeparatore);
			} else {
				campi.add(s.substring(0,indexSeparatore));
				if (indexSeparatore + 1 == s.length()) {
					campi.add("");
				}
				s.delete(0, indexSeparatore + 1);
			}
		}
		
		return campi.toArray(new String[0]);
	}
	
	public static void main(String[] args) throws IOException {
		List<Contatto> contatti = creaListaContatti("/temp/rubrica/rubrica_madonia.csv");
		for(Contatto contatto : contatti) {
			System.out.println(contatto.toString());
		}
	}
}
