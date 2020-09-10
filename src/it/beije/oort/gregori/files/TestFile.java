package it.beije.oort.gregori.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TestFile {

	public static void main(String[] args) throws IOException {
		
		File file = new File("/temp/values.txt");
		
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
	
		StringBuilder sb = new StringBuilder();
		
		if(file.exists()) {
			while(bufferedReader.ready()) {
				sb.append(bufferedReader.readLine()).append("\n");
			}
		}
		
		System.out.println("Il contenuto attuale del file è: ");
		System.out.println(sb);
		
		bufferedReader.close();
		
		FileWriter fileWriter = new FileWriter(file);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		
		String scelta = "s";
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Inizio scrittura su file...");
		System.out.println("Premere 'n' per terminare");
		do {
			scelta = sc.nextLine();
			if(!scelta.equals("n")) sb.append(scelta).append("\n");
		} while(!scelta.equals("n"));
		
		bufferedWriter.write(sb.toString());
		
		bufferedWriter.flush();
		bufferedWriter.close();
		sc.close();
		
		System.out.println("Il contenuto del file dopo la scrittura è: ");
		System.out.println(sb);
	}

}
