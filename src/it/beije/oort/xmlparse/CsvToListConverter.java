package it.beije.oort.xmlparse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvToListConverter {
	public List<Contatto> convert(File fileCsv) throws IOException {
		List<Contatto> contatti = new ArrayList<Contatto>();
		List<String> fields = new ArrayList<String>();
		
		FileReader fileReader = new FileReader(fileCsv);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = bufferedReader.readLine();
		String[] header = line.split(";");
		fields.addAll(Arrays.asList(header));

		int nomePos = fields.indexOf("NOME");
		int cognomePos = fields.indexOf("COGNOME");
		int emailPos = fields.indexOf("EMAIL");
		int telefonoPos = fields.indexOf("TELEFONO");
		
		while (bufferedReader.ready()) {
			Contatto contatto = new Contatto();
			String lineNext = bufferedReader.readLine();
			String[] campi = lineNext.split(";");
			contatto.setNome(campi[nomePos]);
			contatto.setCognome(campi[cognomePos]);
			contatto.setEmail(campi[emailPos]);
			contatto.setTelefono(campi[telefonoPos]);
			contatti.add(contatto);
		}
		bufferedReader.close();
		return contatti;
	}
}
