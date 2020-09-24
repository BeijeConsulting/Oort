package it.beije.oort.gregori.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.beije.oort.rubrica.Contatto;

public class ReaderCsv {

	public static List<Contatto> readContatti(File csvFile) throws IOException {
		List<Contatto> contatti = new ArrayList<Contatto>();
		
		FileReader reader = new FileReader(csvFile);
		BufferedReader buffer = new BufferedReader(reader);
		
		String intestazione = buffer.readLine();
		List<String> intestazioneList = Arrays.asList(intestazione.split(";"));
		
		while(buffer.ready()) {
			contatti.add(checkIntestazione(buffer.readLine().split(";", -1), intestazioneList));
		}
		
		buffer.close();
		
		return contatti;
	}
	
	private static Contatto checkIntestazione(String[] row, List<String> intestazioneList) {
		Contatto c = new Contatto();
		for(int i = 0; i < intestazioneList.size(); i++) {
			if(intestazioneList.get(i).equalsIgnoreCase("nome")) c.setNome(row[i]);
			else if(intestazioneList.get(i).equalsIgnoreCase("cognome")) c.setCognome(row[i]);
			else if(intestazioneList.get(i).equalsIgnoreCase("email") ||
					intestazioneList.get(i).equalsIgnoreCase("mail") ||
					intestazioneList.get(i).equalsIgnoreCase("e-mail")) c.setEmail(row[i]);
			else if(intestazioneList.get(i).equalsIgnoreCase("telefono")) c.setTelefono(row[i]);
		}
		return c;
	}
	
}
