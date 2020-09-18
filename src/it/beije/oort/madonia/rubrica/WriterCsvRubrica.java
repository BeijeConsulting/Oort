package it.beije.oort.madonia.rubrica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriterCsvRubrica {
	
	public static void writeCsvFile(String[] intestazione, List<Contatto> contatti, File fileCsv) throws IOException {
		FileWriter writer = new FileWriter(fileCsv);

		writer.write(WriterCsvRubrica.costruisciRiga(intestazione));
		for (Contatto contatto : contatti) {
			writer.write("\n");
			writer.write(WriterCsvRubrica.costruisciRiga(intestazione,contatto));
		}
		
		writer.flush();
		writer.close();
		System.out.println("Rubrica completata!");
	}
	
	public static void writeCsvFile(String[] intestazione, List<Contatto> contatti, String pathfile) throws IOException {
		File fileCsv = new File(pathfile);
		WriterCsvRubrica.writeCsvFile(intestazione, contatti, fileCsv);
	}
	
	public static void writeCsvFileCasuale(String[] intestazione, List<Contatto> contatti, File fileCsv) throws IOException {
		FileWriter writer = new FileWriter(fileCsv);
		contatti.sort(null);

		writer.write(WriterCsvRubrica.costruisciRiga(intestazione));
		for (Contatto contatto : contatti) {
			writer.write("\n");
			writer.write(WriterCsvRubrica.costruisciRigaCasuale(intestazione, contatto));
		}
		
		writer.flush();
		writer.close();
		System.out.println("Rubrica completata!");
	}
	
	public static void writeCsvFileCasuale(String[] intestazione, List<Contatto> contatti, String pathfile) throws IOException {
		File fileCsv = new File(pathfile);
		WriterCsvRubrica.writeCsvFileCasuale(intestazione, contatti, fileCsv);
	}
	
	private static String costruisciRiga(Contatto contatto) {
		return costruisciRiga(contatto.getCognome(), contatto.getNome(), contatto.getTelefono(), contatto.getEmail());
	}
	
	private static String costruisciRiga(String[] intestazione, Contatto contatto) {
		List<String> campi = new ArrayList<String>();
		for(int index = 0; index < intestazione.length; index++) {
			if (intestazione[index].equalsIgnoreCase("nome")) {
				campi.add(contatto.getNome());
			} else if (intestazione[index].equalsIgnoreCase("cognome")) {
				campi.add(contatto.getCognome());
			} else if (intestazione[index].equalsIgnoreCase("telefono")) {
				campi.add(contatto.getTelefono());
			} else if (intestazione[index].equalsIgnoreCase("email")) {
				campi.add(contatto.getEmail());
			}
		}
		
		return costruisciRiga(campi.toArray(new String[0]));
	}
	
	private static String costruisciRiga(String... campi) {
		StringBuilder riga = new StringBuilder();
		for(String campo : campi) {
			riga.append(campo).append(';');
		}
		riga.deleteCharAt(riga.length() - 1);
		return riga.toString();
	}
	
	private static String costruisciRigaCasuale(String[] intestazione, Contatto contatto) {
		List<String> campi = new ArrayList<String>();
		for(int index = 0; index < intestazione.length; index++) {
			if (intestazione[index].equalsIgnoreCase("nome")) {
				if ((int) (Math.random() * 5) + 1 > 1) {
					campi.add(contatto.getNome());
				} else {
					campi.add("");
				}
			} else if (intestazione[index].equalsIgnoreCase("cognome")) {
				if ((int) (Math.random() * 3) + 1 > 1) {
					campi.add(contatto.getCognome());
				} else {
					campi.add("");
				}
			} else if (intestazione[index].equalsIgnoreCase("telefono")) {
				campi.add(contatto.getTelefono());
			} else if (intestazione[index].equalsIgnoreCase("email")) {
				campi.add(contatto.getEmail());
			}
		}
		
		return costruisciRiga(campi.toArray(new String[0]));
	}
}
