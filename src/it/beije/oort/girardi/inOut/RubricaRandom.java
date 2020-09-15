package it.beije.oort.girardi.inOut;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;

public class RubricaRandom {
	
	public static String[] elencoFromFile (File file) throws IOException {
		//riporta in un array di stringhe il contenuto di un file di testo,
		//riga per riga. 
		// N.B.: parte dalla riga successiva alla prima riga vuota!!
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String[] arrayString = new String[];
		
		//porto il bufferedReader alla riga successiva alla riga vuota.
		boolean cond = true;
		while (cond) {
			String line = bufferedReader.readLine();
			line = line.trim();
			cond = (line != "");
		}
		
		int i = 0;
		while (bufferedReader.ready()) {
			arrayString(i) = bufferedReader.readLine();
			i++;
		}
		
		return arrayString;
	}
	

	public static void main(String[] args) {
		File fileNomi = new File("C:\\Users\\Padawan05\\Desktop\\file_txt"
				+ "\\nomi_italiani.txt");
		
		String [] nomi = elencoFromFile(fileNomi);
		System.out.println(nomi.length);
	}

}
