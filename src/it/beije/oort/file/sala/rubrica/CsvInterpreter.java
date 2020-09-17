package it.beije.oort.file.sala.rubrica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.beije.oort.rubrica.Contatto;

public class CsvInterpreter {
	
	public static List<Contatto> readCsvToList(String filePath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String[] intestazione = br.readLine().split(";");
		int a=5, b=5, c=5, d=5;
		for(int i=0; i<4; i++) {
			switch(intestazione[i].toUpperCase())
			{
				case "NOME":
					a=i;
					break;
				case "COGNOME":
					b=i;
					break;
				case "EMAIL":
					c=i;
					break;
				case "TELEFONO":
					d=i;
					break;
			}
		}
		
		List<Contatto> contatti = new ArrayList<Contatto>();
		while(br.ready()) {
			String[] temp = br.readLine().split(";");
			contatti.add(new Contatto(temp[a], temp[b], temp[c], temp[d]));
		}
		br.close();
		return contatti;
	
	}
}
