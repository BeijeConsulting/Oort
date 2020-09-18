package it.beije.oort.bm.exercises.rubrica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvContactsWriter {
	private static final String INTESTAZIONE = "COGNOME;NOME;EMAIL;TELEFONO";
	
	public static void writeFile(File csvFile, List<Contatto> list) throws IOException {
		FileWriter writer = new FileWriter(csvFile);
		writer.write(INTESTAZIONE);
		writer.write('\n');
		for(Contatto c : list) {
			StringBuilder sb = new StringBuilder();
			sb.append(c.getCognome()).append(';')
				.append(c.getNome()).append(';')
				.append(c.getEmail()).append(';')
				.append(c.getTelefono()).append(';').append('\n');
			writer.write(sb.toString());
		}
		writer.flush();
		writer.close();
		
	}

}
