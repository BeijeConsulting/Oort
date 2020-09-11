package it.beije.oort.lauria;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RecordFile {
		
	private static final String PATH_FILES = "C:\\Users\\Padawan06\\Documenti\\temp\\";
	
	// lettura file
	public static String getContent(File file) throws IOException {
		FileReader fileReader = new FileReader(file);
		StringBuilder builder = new StringBuilder();
		
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		while (bufferedReader.ready()) {
			builder.append(bufferedReader.readLine()).append('\n');
		}
		
		return builder.toString();	
	}

	// lettura e memorizzazione record	
	public static List<String> memContent(File file, List<String> record ) throws IOException {
			
		FileReader fileReader = new FileReader(file);
			
		BufferedReader bufferedReader = new BufferedReader(fileReader);
			
		while (bufferedReader.ready()) {
			record.add(bufferedReader.readLine());
		}

		return record;
	}
	
	public static void main(String[] args) throws IOException {
		
		File fileNomi = new File(PATH_FILES + "nomi_italiani.txt");
		File fileCognomi = new File(PATH_FILES + "cognomi.txt");
		
		// lettura e memorizzazione dei record
		List<String> recordNomitemp = new ArrayList<>();
		List<String> recordCognomitemp = new ArrayList<>();
		List<String> recordNomi = new ArrayList<>();
		List<String> recordCognomi = new ArrayList<>();
		
//		List<String> recordPrefissi = new ArrayList<>();
//		List<String> recordTel = new ArrayList<>();
//		List<String> recordMail = new ArrayList<>();
		
		recordNomitemp = RecordFile.memContent(fileNomi, recordNomitemp);
		recordCognomitemp = RecordFile.memContent(fileCognomi, recordCognomitemp);
		
//		recordNomi = RecordFile.memContent(fileNomi, recordNomi);
//		recordNomi = RecordFile.memContent(fileNomi, recordNomi);
//		recordNomi = RecordFile.memContent(fileNomi, recordNomi);
		
		System.out.println("numero nomi disponibili: "+recordNomitemp.size());
		System.out.println("numero cognomi disponibili: "+recordCognomitemp.size());
		
		int i;
		int j; 
		
		for(int k = 0; k < 1000; k++) {
			i = (int) (Math.random()*recordNomitemp.size()); 
			j = (int) (Math.random()*recordCognomitemp.size());			
			recordNomi.add(recordNomitemp.get(i));
			recordCognomi.add(recordCognomitemp.get(j));
			
			
		}
		System.out.println("numero nomi disponibili : "+recordNomi.size());
		System.out.println("numero cognomi disponibili : "+recordCognomi.size());
		
		// fase di scrittura	
		File output = new File(PATH_FILES + "rubrica.txt");
		FileWriter writer = new FileWriter(output);
		
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
	
	// scrittura record sui file
	private static FileWriter aggiungiRiga(FileWriter writer, String nome, String cognome, String numero, String email) throws IOException {
		StringBuilder riga = new StringBuilder();
		riga.append(nome).append(';').append(cognome).append(';').append(numero).append(';').append(email);
		writer.write(riga.toString());
		return writer;
	}

}


//
//
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.Scanner;
//
//import it.beije.oort.file.CsvReader;
//
//public class ReadAndWrite {
//	
//	
//	
//	public static String getContent(File file) throws IOException {
//		FileReader fileReader = new FileReader(file);
//		StringBuilder builder = new StringBuilder();
//		
//		BufferedReader bufferedReader = new BufferedReader(fileReader);
//		
//		while (bufferedReader.ready()) {
//			builder.append(bufferedReader.readLine()).append('\n');
//		}
//		
//		return builder.toString();
//	}
//	
//	public static void writeRow(File file, String content) throws IOException {
//		FileWriter writer = new FileWriter(file);
//		
//		writer.write(content);
//		
//		writer.flush();
//		writer.close();
//	}
//	
//
//	public static void main(String[] args) throws IOException {
//		
//		File file = new File("C:\\Users\\Padawan06\\Documenti\\prova.txt");
//		
//		System.out.println("esiste ? "+file.exists());
//		
//		String content = "";
//		
//		if (file.exists()) {
//			System.out.println("esiste, lo carico... ");
//			
//			content = CsvReader.getContent(file);
//			System.out.println(content);
//			
//		} else {
//			System.out.println("non esiste");
//		}
//
//		
//		
//		Scanner s = new Scanner(System.in);
//		String st = s.nextLine();
//		
//		
//		while (!st.equalsIgnoreCase("Q")) {
//			
//			content = content + '\n' + st;
//			writeRow(file, content);
//			System.out.println(st);
//			st = s.nextLine();
//			
//		}
//		
//		
//		
//		System.gc();
//		System.out.println("BYE!!");
//		s.close();
//
//
//	}
//
//}