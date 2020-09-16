package it.beije.oort.madonia.rubrica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
		String[] campi = rigaCsv.split(";");
		for(int index = 0; index < colonne.length; index++) {
			if (colonne[index].equals("nome")) {
				contatto.setNome(campi[index]);
			} else if (colonne[index].equals("cognome")) {
				contatto.setCognome(campi[index]);
			} else if (colonne[index].equals("telefono")) {
				contatto.setTelefono(campi[index]);
			} else if (colonne[index].contains("mail")) {
				contatto.setEmail(campi[index]);
			}
		}
		
		return contatto;
	}
	
	private static String[] ottieniColonne(String intestazioneCsv) {
		return intestazioneCsv.toLowerCase().split(";");
	}
	
}
