package it.beije.oort.gregori.rubrica.hashmap;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import it.beije.oort.rubrica.Contatto;
import it.beije.oort.gregori.rubrica.parser.*;

public class Duplicates {
	
	public static List<Contatto> checkDuplicates(List<Contatto> contatti, File pathFile) {
		HashMap<String, List<Contatto>> hm = new HashMap<>();	
		List<Contatto> returnList = new ArrayList<>();
		for(Contatto contatto : contatti) {
			if(hm.containsKey(contatto.getTelefono())) {
				for(int i = 0; i < hm.get(contatto.getTelefono()).size(); i++) {
					if(!hm.get(contatto.getTelefono()).get(i).equals(contatto)) {
						hm.get(contatto.getTelefono()).add(contatto);
					}
				}
			}
			else {
				List<Contatto> temp = new ArrayList<>();
				temp.add(contatto);
				hm.put(contatto.getTelefono(), temp);
			}
			
		}
		
		myPrint(hm);
		
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
		File pathFile = new File("duplicates_removed.csv");
		Duplicates.checkDuplicates(contatti, pathFile);
	}
	
}
