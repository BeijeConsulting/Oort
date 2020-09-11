package it.beije.oort.madonia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecordFile {
	
	private static final String PATH_FILES = "/temp/rubrica/";
	
//	// lettura file
//	public static String getContent(File file) throws IOException {
//		FileReader fileReader = new FileReader(file);
//		StringBuilder builder = new StringBuilder();		
//		BufferedReader bufferedReader = new BufferedReader(fileReader);
//		
//		while (bufferedReader.ready()) {
//			builder.append(bufferedReader.readLine()).append('\n');
//		}
//		
//		return builder.toString();	
//	}
	
	public static void main(String[] args) throws IOException {
		
		File fileNomi = new File(PATH_FILES + "nomi_italiani.txt");
		File fileCognomi = new File(PATH_FILES + "cognomi_italiani.txt");
		
		// Dichiarazione variabili 
		List<String> recordNomitemp = new ArrayList<>();
		List<String> recordCognomitemp = new ArrayList<>();
		
		List<String> recordNomi = new ArrayList<>();
		List<String> recordCognomi = new ArrayList<>();	
		List<String> recordTel = new ArrayList<>();
		List<String> recordMail = new ArrayList<>();
		
		// lettura e memorizzazione dei record di nomi e cognomi
		recordNomitemp = RecordFile.memContent(fileNomi, recordNomitemp);
		recordCognomitemp = RecordFile.memContent(fileCognomi, recordCognomitemp); 
		
		for(int k = 0; k < 1000; k++) {
			int i = (int) (Math.random()*recordNomitemp.size()); 
			int j = (int) (Math.random()*recordCognomitemp.size());			
			recordNomi.add(recordNomitemp.get(i));
			recordCognomi.add(recordCognomitemp.get(j));
			recordTel.add(RecordFile.generaNumero());
			recordMail.add(RecordFile.generaMail(recordNomi.get(k), recordCognomi.get(k)));
		}
		
		// fase di scrittura	
		File output = new File(PATH_FILES + "rubrica.txt");
		FileWriter writer = new FileWriter(output);
		
		writer.write("NOME;COGNOME;TELEFONO;E-MAIL");
		for (int arrayIndex = 0; arrayIndex < recordNomi.size(); arrayIndex++) {
			writer.write("\n");
			RecordFile.aggiungiRiga(
					writer,
					recordNomi.get(arrayIndex),
					recordCognomi.get(arrayIndex),
					recordTel.get(arrayIndex),
					recordMail.get(arrayIndex));
		}
		
		writer.flush();
		writer.close();
		System.out.println("Rubrica completata!");
	}
	
	// lettura e memorizzazione record	
	public static List<String> memContent(File file, List<String> record ) throws IOException {			
		FileReader fileReader = new FileReader(file);			
		BufferedReader bufferedReader = new BufferedReader(fileReader);			
		
		while (bufferedReader.ready()) {
			record.add(bufferedReader.readLine());
		}
		
		fileReader.close();
		bufferedReader.close();

		return record;
	}
	
	// genera Numero di telefono
	private static String generaNumero() {
		String[] prefissi = {"345", "346", "347", "348", "349"};
		StringBuilder s = new StringBuilder();
		
		int indexPrefisso = (int) (Math.random() * prefissi.length);
		s.append(prefissi[indexPrefisso]);
		
		for(int i = 0; i < 7; i++) {
			s.append((int) (Math.random() * 9));
		}
		
		return s.toString();
	}
	
	private static String generaMail(String nome, String cognome) {
		String[] dominio = {"gmail.com", "hotmail.com", "hotmail.it", "libero.it", "yahoo.com", "virgilio.it", "tim.it", "alice.it"};
		StringBuilder s = new StringBuilder();
		
		nome = nome.replace(" ", "").replace("'", "");
		cognome = cognome.replace(" ", "").replace("'", "");
		
		s.append(nome.toLowerCase()).append('.').append(cognome.toLowerCase()).append('@');
		
		int indexDominio = (int) (Math.random() * dominio.length);
		s.append(dominio[indexDominio]);
		
		return s.toString();
	}
	
//	private static String generaMail(String nome, String cognome) {
//		String[] dominio = {"gmail.com", "hotmail.com", "hotmail.it", "libero.it", "yahoo.com", "virgilio.it", "tim.it", "alice.it"};
//		StringBuilder s = new StringBuilder();
//		
//		nome = nome.replace(" ", "").replace("'", "").toLowerCase();
//		cognome = cognome.replace(" ", "").replace("'", "").toLowerCase();
//		
//		if((int) (Math.random() * 5) + 1 != 1) {
//			s.append(nome);
//		}
//		
//		if((int) (Math.random() * 3) + 1 != 1) {
//			if (s.length() > 0) {
//				s.append('.');
//			}
//			s.append(cognome);
//		}
//		
//		if(s.length() == 0) {
//			int numero = (int) (Math.random() * 14) + 6;
//			for (int index = 0; index < numero; index++) {
//				
//			}
//		}
//		
//		return "";
//	}
	
	// scrittura record sui file
	private static FileWriter aggiungiRiga(FileWriter writer, String nome, String cognome, String numero, String email) throws IOException {
		StringBuilder riga = new StringBuilder();
		riga.append(nome).append(';').append(cognome).append(';').append(numero).append(';').append(email);
		writer.write(riga.toString());
		return writer;
	}

}