package it.beije.oort.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvParser {
	public static List<Contatto> readContatti(File fileCsv) throws IOException {
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
	
	public static void buildContatti(List<Contatto> contatti, File fileDestinazione) throws IOException {
		FileWriter writer = new FileWriter(fileDestinazione);
		writer.write("NOME;COGNOME;EMAIL;TELEFONO;\n");
		for (int i = 0; i < contatti.size(); i++) {
			writer.write(contatti.get(i).toString());
			writer.write("\n");
			writer.flush();
		}
		writer.close();
	}
}
