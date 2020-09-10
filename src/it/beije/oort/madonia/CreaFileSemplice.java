package it.beije.oort.madonia;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CreaFileSemplice {

	public static void main(String[] args) throws IOException {
		File file = new File("/temp/creaFileSemplice.txt"); // nessun check sull'esistenza, non ci importa sovrascriverlo
		FileWriter writer = new FileWriter(file);
		
		Scanner sc = new Scanner(System.in);
		StringBuilder s = new StringBuilder();
		
		System.out.println("Scrivi in libertà!");
		System.out.println("\"CTRL + Z\" per terminare e salvare il file.");
		
		// Posso scrivere più righe!
		// next usa tutti i whitespaces come delimitatori
		// nextLine usa solo il nextline (\n) come delimitatore
		while(sc.hasNextLine()) {
			s.append(sc.nextLine());
			s.append("\n");
		}
		
		if (s.length() > 0) {
			s.deleteCharAt(s.length() - 1);
		}

		sc.close();
		writer.write(s.toString());
		writer.flush();
		writer.close();
	}

}
