package it.beije.oort.lauria;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMap1Test {
	private static final String PATH_FILES = "C:\\Users\\Padawan06\\Documenti\\temp\\";
	private static final String INTESTAZIONE = "NOME;COGNOME;EMAIL;TELEFONO";
	
	public static void main(String[] args) throws IOException {
	
		File fileRubrica = new File(PATH_FILES + "rubricaDuplicatiIvo.csv");
		
		List<Contatto> recordContatti = new ArrayList<>();
		List<Contatto> recordDuplicati = new ArrayList<>();
		
		List<Contatto> recordDuplicatiJoin = new ArrayList<>();
		
		Map<String, Contatto> rubricaMap = new HashMap<>();
	 
		recordContatti = CsvParser.readContattiCsv(fileRubrica);
		
		System.out.println("Numero totale di contatti in rubrica: "+recordContatti.size());  
		
		Contatto duplicato;
		
		for(Contatto contattoTemp : recordContatti) {
			duplicato = rubricaMap.put(contattoTemp.getEmail(), contattoTemp);
			if(duplicato != null) {
				recordDuplicati.add(duplicato);
			}
		}
		
		System.out.println("Numero totale di contatti in mappa: "+rubricaMap.size()); 
		System.out.println("Numero totale di contatti duplicati in rubrica: "+recordDuplicati.size());
		
		Collections.sort(recordDuplicati, new MatchEmail());
		
//		Scanner s = new Scanner(System.in);
		
		int start = 0, end = start+1;
		while(end < recordDuplicati.size()) {
			while(recordDuplicati.get(start).getEmail().equalsIgnoreCase(recordDuplicati.get(end).getEmail())) {
				end++;
			}
//			System.out.println("start = "+start+" end = "+(end-1));
			Contatto c;
			c = new HashMap1Test().joinContacts(recordDuplicati, start, end-1);
			recordDuplicatiJoin.add(c);
//			System.out.println(c);
			start = end; 
			end = start+1;
//			System.out.println("start = "+start+" end = "+end);
			if(end == recordDuplicati.size()) {
				c = new HashMap1Test().joinContacts(recordDuplicati, start, end-1);
				recordDuplicatiJoin.add(c);
//				System.out.println(c);
			}
		}
		
		System.out.println("Numero totale di contatti duplicati join in rubrica: "+recordDuplicatiJoin.size());		
		CsvParser.writeContattiCsv(PATH_FILES + "rubricaDuplicati.csv", INTESTAZIONE, recordDuplicati);
		CsvParser.writeContattiCsv(PATH_FILES + "rubricaDuplicatiJoin.csv", INTESTAZIONE, recordDuplicatiJoin);
		
	}

	public Contatto joinContacts(List<Contatto> recordDuplicati, int start, int end) {
		Contatto result = new Contatto();
		StringBuilder duplicati = new StringBuilder();
		
		int i =  start+1;
		while(i <= end) {
			if(recordDuplicati.get(start).getNome().equalsIgnoreCase("")) {
				if(!recordDuplicati.get(i).getNome().equalsIgnoreCase("")) {
					recordDuplicati.get(start).setNome(recordDuplicati.get(i).getNome());
				}
			}else {
				duplicati.append(recordDuplicati.get(i).getNome());
			}
			if(recordDuplicati.get(start).getCognome().equalsIgnoreCase("")) {
				if(!recordDuplicati.get(i).getCognome().equalsIgnoreCase("")) {
					recordDuplicati.get(start).setCognome(recordDuplicati.get(i).getCognome());
				}
			}else {
				duplicati.append(" ").append(recordDuplicati.get(i).getCognome()).append(",");
			}
			if(recordDuplicati.get(start).getTelefono().equalsIgnoreCase("")) {
				if(!recordDuplicati.get(i).getTelefono().equalsIgnoreCase("")) {
					recordDuplicati.get(start).setTelefono(recordDuplicati.get(i).getTelefono());
				}
			}
			i++;
		}
		
		result = recordDuplicati.get(start);
		//result.setDuplicatiEmail(duplicati.toString());
		return result;
	}

}

