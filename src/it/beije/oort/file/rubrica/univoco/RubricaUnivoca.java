package it.beije.oort.file.rubrica.univoco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.beije.oort.file.rubrica.Contatto;
import it.beije.oort.file.rubrica.comparators.ContattoEmailComparator;
import it.beije.oort.franceschi.csvToXml.CSVParser;
import it.beije.oort.franceschi.csvToXml.CSVWriter;
import it.beije.oort.franceschi.csvToXml.InputManager;

public class RubricaUnivoca {
    static List<Contatto> duplicatiList = new ArrayList<>();
    static Map<String, Contatto> univociMap = new HashMap<>();

	
	public static void main(String[] args) {
		String in = InputManager.getDuplicatiPath() + "rubrica.csv";
		
		List<Contatto> lista = new CSVParser(in).creaListaContatti();
		
		trovaDuplicatiMap(lista);
		accorpatore(duplicatiList);
		
		CSVWriter.writeCSV(new ArrayList<Contatto>(univociMap.values()), 
				InputManager.getDuplicatiPath() + "rubrica_univoci.csv",
				new ContattoEmailComparator());
		CSVWriter.writeCSV(duplicatiList,
				InputManager.getDuplicatiPath() + "rubrica_duplicati.csv",
				new ContattoEmailComparator());
	}

	
	@SuppressWarnings("unused")
	private static void trovaDuplicatiMap(List<Contatto> rubrica) {
	    for(Contatto c: rubrica) {
	    	Contatto temp = univociMap.put(c.getEmail(), c);
	        if(temp != null) {
	        	compareContacts(c, temp);
	        }
	    }
	}
	
	public static void accorpatore(List<Contatto> dupes) {
		for (int i = 1; i < dupes.size(); i++) {
			tentaAccorpamentoTraDupes(dupes.get(i-1), dupes.get(i));
		}
	}
	
	public static void tentaAccorpamentoTraDupes(Contatto c, Contatto b) {
		Contatto nuovo = new Contatto();
		if (!c.getNome().equals(b.getNome()) || 
			!c.getCognome().equals(b.getCognome()) ||
			!c.getCell().equals(b.getCell())) {
			return;
		}
		if (b.getNome() != "") {
			nuovo.setNome(b.getNome());
		}
		if (b.getCognome() != ""){
			nuovo.setCognome(b.getCognome());
		}
		if (b.getCell() != "") {
			nuovo.setCell(b.getCell());
		}
		duplicatiList.remove(b);
		duplicatiList.remove(c);
		duplicatiList.add(nuovo);
	}
	
	public static void compareContacts(Contatto c, Contatto espulso) {
		if (!c.getNome().equals(espulso.getNome()) ||
			!c.getCognome().equals(espulso.getCognome()) ||
			!c.getCell().equals(espulso.getCell())) {
			duplicatiList.add(espulso);
			return;
		}
		
		if (espulso.getNome() != "") {
			c.setNome(espulso.getNome());
		}
		if (espulso.getCognome() != ""){
			c.setCognome(espulso.getCognome());
		}
		if (espulso.getCell() != "") {
			c.setCell(espulso.getCell());
		}
		//duplicatiMap.add(c);
		univociMap.put(c.getEmail(), c);
	}
}
