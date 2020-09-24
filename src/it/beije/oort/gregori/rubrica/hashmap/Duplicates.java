package it.beije.oort.gregori.rubrica.hashmap;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import it.beije.oort.rubrica.Contatto;
import it.beije.oort.gregori.rubrica.parser.ReaderCsv;

public class Duplicates {
	
	public static List<Contatto> checkDuplicates(List<Contatto> contatti, File pathFile) {
		HashMap<String, List<Contatto>> hm = new HashMap<>();	
		List<Contatto> returnList = new ArrayList<>();
		for(Contatto contatto : contatti) {
			// Se il numero di telefono non è vuoto
			if(!contatto.getTelefono().equals("")) {
				// Se la mappa contiene una chiave telfono già esistente
				if(hm.containsKey(contatto.getTelefono())) {
					// Se i contatti con la stessa chiave sono diversi 
					// aggiungili alla lista contatti nella mappa
					if(!hm.get(contatto.getTelefono()).contains(contatto)) {
						hm.get(contatto.getTelefono()).add(contatto);
					}
				}
				else {
					List<Contatto> temp = new ArrayList<>();
					temp.add(contatto);
					hm.put(contatto.getTelefono(), temp);
				}
			}
		}
		
		for(List<Contatto> listContatti : hm.values()) {
			List<String> setNomi = new ArrayList<>();
			List<String> setCognomi = new ArrayList<>();
			List<String> setEmails = new ArrayList<>();	
			String telefono = "";
			if(listContatti.size() > 1) {
				for(Contatto contatto : listContatti) {
					if(!setNomi.contains(contatto.getNome())) setNomi.add(contatto.getNome());
					if(!setCognomi.contains(contatto.getCognome())) setCognomi.add(contatto.getCognome());
					if(!setEmails.contains(contatto.getEmail())) setEmails.add(contatto.getEmail());
					telefono = contatto.getTelefono();
				}
//				System.out.println(setNomi);
//				System.out.println(setCognomi);
//				System.out.println(setEmails);
//				System.out.println("---------------");
				
				Contatto contatto = new Contatto();
				List<String> trash = new ArrayList<>();
				boolean flag = true;
				
				for(int i = 0; i < setNomi.size(); i++) {
					if(!setNomi.get(i).equals("") && flag) {
						contatto.setNome(setNomi.get(i));
						flag = false;
					}
					else if(!setNomi.get(i).equals("")){
						trash.add(setNomi.get(i));
					}
				}
				
				flag = true;
				for(int i = 0; i < setCognomi.size(); i++) {
					if(!setCognomi.get(i).equals("") && flag) {
						contatto.setCognome(setCognomi.get(i));
						flag = false;
					}
					else if(!setCognomi.get(i).equals("")){
						trash.add(setCognomi.get(i));
					}
				}
				
				flag = true;
				for(int i = 0; i < setEmails.size(); i++) {
					if(!setEmails.get(i).equals("") && flag) {
						contatto.setEmail(setEmails.get(i));
						flag = false;
					}
					else if(!setEmails.get(i).equals("")){
						trash.add(setEmails.get(i));
					}
				}
				
				contatto.setTelefono(telefono);
				if(contatto.getNome() == null) { contatto.setNome(""); }
				if(contatto.getCognome() == null) { contatto.setCognome(""); }
				if(contatto.getEmail() == null) { contatto.setEmail(""); }
				returnList.add(contatto);
			}
		}		
		return returnList;
	}
	
	public static void myPrint(HashMap<String, List<Contatto>> map) {
		for(List<Contatto> contatti : map.values()) {
			System.out.println(contatti);
		}
	}

	public static void main(String[] args) throws IOException {
		File csvFile = new File("/temp/rubrica.csv");
		List<Contatto> contatti = ReaderCsv.readContatti(csvFile);
		File pathFile = new File("/temp/duplicates_removed.csv");
		Duplicates.checkDuplicates(contatti, pathFile);
		WriterCsv.writeContatti(Duplicates.checkDuplicates(contatti, pathFile), pathFile);
	}
	
}
