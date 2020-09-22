package it.beije.oort.team.map;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import it.beije.oort.rubrica.Contatto;

public class Duplicates {
	
	
	public static List<Contatto> getDuplicates(List<Contatto> contatti, File pathFile) throws IOException {
		
		HashMap<String,Contatto> map = new HashMap<>();	
		List<Contatto> returnList = new ArrayList<>();
		for(Contatto contatto : contatti) {
			Contatto temp = map.put(contatto.getEmail(), contatto);
			if (temp != null)
				returnList.add(temp);
		}
		getStampaMappa(map);
		System.out.println(returnList.size());
		WriterCsv.writeContatti(returnList, pathFile);
		return returnList;
	}

	public static void getStampaMappa(HashMap<String, Contatto> map) {
		for(Contatto contatti : map.values()) {
			System.out.println(contatti);
		}
	}

	public static void main(String[] args) throws IOException {
		File csvFile = new File("/temp2/rubrica.csv");
		List<Contatto> contatti = ReaderCsv.readContatti(csvFile);
		File pathFile = new File("/temp2/rimossi.csv");
		Duplicates.getDuplicates(contatti, pathFile);
	}

}