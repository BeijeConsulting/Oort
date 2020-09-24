package it.beije.oort.girardi.inOut;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;

/*
* 1) creazione o lettura del file:
* 2) prende parole da tastiera tramite scanner
* 3) Il file vecchio è perso nel momento in cui si scrive
*	 qualcosa di nuovo: scriviamo quindi il vecchio 
*    contenuto assieme al nuovo.
*/

public class MyScannerCSV {
	
//scrive in un file di teto il contenuto di un unica stringa.
	public static void writeStuff(File file, String contenuto) throws IOException {
		FileWriter writer = new FileWriter(file);
		
		writer.write(contenuto);
		
		writer.flush(); //senza non scrive sul foglio, anche se...
		writer.close(); //...close scrive anche oltre a chiudere
	}
	
	
//riporta in un unica stringa il contenuto di un file di testo.
	public static String getContent(File file) throws IOException {
		FileReader fileReader = new FileReader(file);
		StringBuilder builder = new StringBuilder();
		
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		while (bufferedReader.ready()) {
			builder.append(bufferedReader.readLine()).append('\n');
		}
		
		return builder.toString();
	}
	
	
// --------------- main -----------------
	public static void main(String[] args) throws IOException {
		
		// 1) creazione o lettura del file:
		File file = new File("C:\\Users\\Padawan05\\Desktop\\file_txt\\rubrica.txt");
		
//		File file = new File("C:\\Users\\Padawan05\\eclipse-work"
//				+ "space\\FirstProject\\src\\inOut\\rubrica.txt");
		
		String content = "";
		
		if (file.exists()) {
			System.out.println("esiste, lo carico... ");	
			content = getContent(file);
		}
		else 
			System.out.println("non esiste, lo creo...");
		
			
		// 2) prende parole da tastiera tramite scanner
		System.out.println("Prego scrivere ciò che si vuole aggiungere al file:");
		Scanner myInput = new Scanner(System.in);
		String newContent = "";
		String str = "";
		while (true) {
			//per interrompere digitare "q" o "Q" e fare invio
			str = myInput.nextLine();
			System.out.println(str);
			if (str.equalsIgnoreCase("Q"))
				break;
			newContent += str + "\n";
		}
		
		System.out.println("BYE!!");
		myInput.close();
		System.gc();	//garbage collector
		
		// 3) Il file vecchio è perso nel momento in cui si scrive
		//	  qualcosa di nuovo: scriviamo quindi il vecchio 
		//    contenuto assieme al nuovo.
		content += newContent;
		writeStuff(file, content);
		
	}
		
}
