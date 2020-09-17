package it.beije.oort.bm.exercises.rubrica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvContactsWriter {
	private static final String INTESTAZIONE = "COGNOME;NOME;EMAIL;TELEFONO";
	
	public static void writeFile(File csvFile, List<List<Contatto>> lists) throws IOException {
		FileWriter writer = new FileWriter(csvFile);
		writer.write(INTESTAZIONE);
		writer.write('\n');
		for(List<Contatto> l : lists) {
			for(Contatto c : l) {
				StringBuilder sb = new StringBuilder();
				sb.append(c.getCognome()).append(';')
					.append(c.getNome()).append(';')
					.append(c.getEmail()).append(';')
					.append(c.getTelefono()).append(';').append('\n');
				writer.write(sb.toString());
			}
		}
		writer.flush();
		writer.close();
		
	}

}
