package it.beije.oort.madonia;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RecordFile {
	
	private static final String PATH_FILES = "/temp/rubrica/";

	public static void main(String[] args) throws IOException {
		File nomi = new File(PATH_FILES + "nomi_italiani.txt");
		File cognomi = new File(PATH_FILES + "cognomi_italiani.txt");
		File output = new File(PATH_FILES + "rubrica.txt");
		
		// Parte finale del codice
		FileWriter writer = new FileWriter(output);
		
		// TODO mettere condizione for che controlla la lunghezza
		
		boolean isPrimaRiga = true;
		for (int arrayIndex = 0; arrayIndex < 1000; arrayIndex++) {
			if (isPrimaRiga) {
				writer.write("\n");
				isPrimaRiga = false;
			}
			// TODO mettere variabili
			// RecordFile.aggiungiRiga(writer, nome, cognome, numero, email);
		}
		
		writer.flush();
		writer.close();
	}
	
	private static FileWriter aggiungiRiga(FileWriter writer, String nome, String cognome, String numero, String email) throws IOException {
		StringBuilder riga = new StringBuilder();
		riga.append(nome).append(';').append(cognome).append(';').append(numero).append(';').append(email);
		writer.write(riga.toString());
		return writer;
	}
}
