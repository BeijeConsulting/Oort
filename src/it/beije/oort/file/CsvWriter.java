package it.beije.oort.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class CsvWriter {

	public static void writeRow(File file, String content) throws IOException {
		FileWriter writer = new FileWriter(file);
		
		writer.write(content);
		
		writer.flush();
		writer.close();
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println(Arrays.toString(args));

		File file = new File("/temp/rubrica.txt");
		
		String content = "";
		
		if (file.exists()) {
			System.out.println("esiste, lo carico... ");
			
			content = CsvReader.getContent(file);
			System.out.println(content);
			
		} else {
			System.out.println("non esiste");
		}

		String row = "";
		for (String arg : args) {
			row += arg + ';';
		}
		
		content = row + '\n' + content;
		
		writeRow(file, content);

	}	

}
