package it.beije.oort.team2.optimization;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MyReader {
	public static final ArrayList<String> nomi = new ArrayList<>();
	public static final ArrayList<String> cognomi = new ArrayList<>();
	
	public MyReader() throws IOException {
		listaNomi();
		listaCognomi();
	}

	private void listaNomi() throws IOException {
		File file = new File("/temp/lista_nomi.txt");
		if(file.exists()) {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
		
			while (bufferedReader.ready()) {
				nomi.add(bufferedReader.readLine());
			}
			
			bufferedReader.close();					
		}
		else System.out.println("Il file non esiste!");
	}
	
	private void listaCognomi() throws IOException {
		File file = new File("/temp/lista_cognomi.txt");
		if(file.exists()) {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
		
			while (bufferedReader.ready()) {
				cognomi.add(bufferedReader.readLine());
			}
			
			bufferedReader.close();					
		}
		else System.out.println("Il file non esiste!");
	}

}
