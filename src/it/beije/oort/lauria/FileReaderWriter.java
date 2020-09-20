package it.beije.oort.lauria;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileReaderWriter {
		
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
	
	public static void writeRow(File file, String content) throws IOException {
		FileWriter writer = new FileWriter(file);
		
		writer.write(content);
		
		writer.flush();
		writer.close();
	}
	

	public static void main(String[] args) throws IOException {
		
		File file = new File("C:\\Users\\Padawan06\\Documenti\\prova.txt");
		
		System.out.println("esiste ? "+file.exists());
		
		String content = "";
		
		if (file.exists()) {
			System.out.println("esiste, lo carico... ");
			
			content = FileReaderWriter.getContent(file);
			System.out.println(content);
			
		} else {
			System.out.println("non esiste");
		}

		
		
		Scanner s = new Scanner(System.in);
		String st = s.nextLine();
		
		
		while (!st.equalsIgnoreCase("Q")) {
			
			content = content + '\n' + st;
			writeRow(file, content);
			System.out.println(st);
			st = s.nextLine();
			
		}
		
		
		
		System.gc();
		System.out.println("BYE!!");
		s.close();


	}

}
