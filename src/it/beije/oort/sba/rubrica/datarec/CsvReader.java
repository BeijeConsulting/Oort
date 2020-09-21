package it.beije.oort.sba.rubrica.datarec;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.beije.oort.sba.rubrica.Contatto;

public class CsvReader {
	
	public static List<Contatto> readContacts(File file) throws IOException {
		FileReader fileReader = new FileReader(file);
     	BufferedReader bufferedReader = new BufferedReader(fileReader);
		String intestazione = bufferedReader.readLine();
		List<String> intestazioneList = Arrays.asList(intestazione.split(";"));
		List<Contatto> contatti = new ArrayList<Contatto>();
		while(bufferedReader.ready()) {
			contatti.add(creaContatto(bufferedReader.readLine().split(";",-1), intestazioneList));
		}
		bufferedReader.close();
		return contatti;
	}
	
	
	private static Contatto creaContatto(String[] a, List<String> list) {
		Contatto contatto = new Contatto();
		for(int i=0;i<list.size();i++) {
			if(list.get(i).equalsIgnoreCase("nome")) contatto.setNome(a[i]);
			else if(list.get(i).equalsIgnoreCase("cognome")) contatto.setCognome(a[i]);
			else if(list.get(i).equalsIgnoreCase("email")) contatto.setEmail(a[i]);
			else if(list.get(i).equalsIgnoreCase("telefono")) contatto.setTelefono(a[i]);	
		}
		return contatto;
	}

}
