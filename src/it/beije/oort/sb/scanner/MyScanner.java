package it.beije.oort.sb.scanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//programma che mi salva gli input in un file quando viene schiacciato invio
public class MyScanner {
	
	public static void writeRow(File file, String content) throws IOException {
		FileWriter writer = new FileWriter(file);
		
		writer.write(content);
		writer.flush();
		writer.close();
	}
	
	public static String getContent(File file) throws IOException {
		FileReader fileReader = new FileReader(file);
		StringBuilder builder = new StringBuilder();
		
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		while (bufferedReader.ready()) {
			builder.append(bufferedReader.readLine()).append('\n');
		}
		bufferedReader.close();
		
		return builder.toString();
	}
	

	
	
	public static void main(String[] args) throws IOException {
		
		File file = new File("./src/it/beije/oort/sb/scanner/scanner.txt"); //punto il file
		
		String content = "";
		if (file.exists()) { 		//se esiste mi copio il suo contenuto in una stringa
			content = getContent(file);
		}
		
		java.io.BufferedReader console = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		String riga = console.readLine();
			if(content=="") { //per evitare di avere la prima linea vuota
				content= riga;
			} else
			content = content + riga;	
			writeRow(file, content);	
	}

}
