package it.beije.oort.team2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import it.beije.oort.rubrica.Contatto;

public class Rubrica {
	public static final int NUM_RECORDS = 100;
	public static ArrayList<String> nomi = new ArrayList<>();
	public static ArrayList<String> cognomi = new ArrayList<>();
	public static ArrayList<Record> records = new ArrayList<>();
	public static ArrayList<String> contatto = new ArrayList<>();
		
	public static void listaNomi() throws IOException {
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
	
	public static void listaCognomi() throws IOException {
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
	
		public static void listaGeneralizzata() throws IOException{
		int ij = 0;
		File file = new File("/temp/rubrica_gregori.csv");
		if(file.exists()) {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while(bufferedReader.ready()) {
				contatto.add(bufferedReader.readLine());
				String[] stringArray1 = contatto.toArray(new String[ij++]);
				if(stringArray1[0].contains("COGNOME;NOME;TELEFONO;EMAIL")) 
					Rubrica.scritturaFile();
				else {
					if(stringArray1[0].contains("COGNOME;NOME;EMAIL;TELEFONO")) 
						Rubrica.scritturaFile3();
					else {
						if(stringArray1[0].contains("TELEFONO;EMAIL;NOME;COGNOME")) 
							Rubrica.scritturaFile2();
					}
				}					
			}
			bufferedReader.close();
			}
		}
		
		public static void scritturaFile() throws IOException {
			File file = new File("/temp/rubrica_generalizzata.cvs");
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.append("COGNOME" + ";" + "NOME" + ";" + "TELEFONO" + ";" + "EMAIL" + "\n");
			for(int i=0; i<records.size(); i++) {
				String name = records.get(i).getNome();
				String surname = records.get(i).getCognome();
				String phone = records.get(i).getTelefono();
				String email = records.get(i).getMail();
				bufferedWriter.append(surname + ";" + name + ";" + phone + ";" + email + "\n");
		}
		
		bufferedWriter.flush();
		bufferedWriter.close();
	}
		
		public static void scritturaFile2() throws IOException {
			File file = new File("/temp/rubrica_generalizzata.cvs");
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.append("TELEFONO" + ";" + "EMAIL" + ";" + "NOME" + ";" + "COGNOME" + "\n");
			for(int i=0; i<records.size(); i++) {
				String name = records.get(i).getNome();
				String surname = records.get(i).getCognome();
				String phone = records.get(i).getTelefono();
				String email = records.get(i).getMail();
				bufferedWriter.append(phone + ";" + email + ";" + name + ";" + surname + "\n");
		}
		
		bufferedWriter.flush();
		bufferedWriter.close();
	}
		
		public static void scritturaFile3() throws IOException {
			File file = new File("/temp/rubrica_generalizzata.csv");
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.append("COGNOME" + ";" + "NOME" + ";" + "EMAIL" + ";" + "TELEFONO" + "\n");
			for(int i=0; i<records.size(); i++) {
				String name = records.get(i).getNome();
				String surname = records.get(i).getCognome();
				String phone = records.get(i).getTelefono();
				String email = records.get(i).getMail();
				bufferedWriter.append(surname + ";" + name + ";" + email + ";" + phone + "\n");
		}
		
		bufferedWriter.flush();
		bufferedWriter.close();
	}

	public static void main(String[] args) throws IOException {		
		Rubrica.listaNomi();
		Rubrica.listaCognomi();
		Rubrica.listaGeneralizzata();
		
		for(int i = 0; i < NUM_RECORDS; i++) {
			Record record = new Record();
			record.generateNome(nomi);
			record.generateCognome(cognomi);
			record.generateTelefono();
			record.generateMail();
			records.add(record);
		}
	}
}