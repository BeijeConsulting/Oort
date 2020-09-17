package it.beije.oort.madonia.rubrica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriterCsvRubrica {
	
	public static void writeCsvFile(String[] intestazione, List<Contatto> contatti, File fileCsv) throws IOException {
		FileWriter writer = new FileWriter(fileCsv);

		writer.write(WriterCsvRubrica.costruisciRiga(intestazione));
		for (Contatto contatto : contatti) {
			writer.write("\n");
			writer.write(WriterCsvRubrica.costruisciRiga(contatto));
		}
		
		writer.flush();
		writer.close();
		System.out.println("Rubrica completata!");
	}
	
	public static void writeCsvFile(String[] intestazione, List<Contatto> contatti, String pathfile) throws IOException {
		File fileCsv = new File(pathfile);
		WriterCsvRubrica.writeCsvFile(intestazione, contatti, fileCsv);
	}
	
	private static String costruisciRiga(Contatto contatto) {
		return costruisciRiga(contatto.getCognome(), contatto.getNome(), contatto.getTelefono(), contatto.getEmail());
	}
	
	private static String costruisciRiga(String... campi) {
		StringBuilder riga = new StringBuilder();
		for(String campo : campi) {
			riga.append(campo).append(';');
		}
		riga.deleteCharAt(riga.length() - 1);
		return riga.toString();
	}
}
