package it.beije.oort.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class CsvReader {

	public static void main(String[] args) throws Exception {
		File file = new File("/temp/records.csv");
				
//		System.out.println("file : " + file);
//		System.out.println("isDirectory ? " + file.isDirectory());
//		System.out.println("esiste ? " + file.exists());
		
		FileReader fileReader = new FileReader(file);
		
		while (fileReader.ready()) {
			System.out.print((char)fileReader.read());
		}

//		int c = fileReader.read();
//		while (c >= 0) {
//			System.out.print((char)c);
//			if (c == '\n') System.out.println("ho trovato un 'a capo'");
//			c = fileReader.read();
//		}

//		BufferedReader bufferedReader = new BufferedReader(fileReader);
//		
//		while (bufferedReader.ready()) {
//			//System.out.println(bufferedReader.readLine());
//			String riga = bufferedReader.readLine();
////			String[] campi = riga.split(";");
////			
////			System.out.println("nome : " + campi[0]);
////			System.out.println("cognome : " + campi[1]);
////			System.out.println("tel : " + campi[2]);
////			System.out.println("email : " + campi[3]);
//
////			StringTokenizer tokenizer = new StringTokenizer(riga, ";");
////			
////			System.out.println("nome : " + tokenizer.nextToken());
////			System.out.println("cognome : " + tokenizer.nextToken());
////			System.out.println("tel : " + tokenizer.nextToken());
////			System.out.println("email : " + tokenizer.nextToken());
//
//			System.out.println();
//		}
	}

	
	public static String getContent(File file) throws IOException {
		FileReader fileReader = new FileReader(file);
		StringBuilder builder = new StringBuilder();
		
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		while (bufferedReader.ready()) {
			builder.append(bufferedReader.readLine()).append('\n');
		}
		
		return builder.toString();
	}

}
