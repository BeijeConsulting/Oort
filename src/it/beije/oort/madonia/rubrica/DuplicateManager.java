package it.beije.oort.madonia.rubrica;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicateManager {
	
	private static final String PATH_FILES = "/temp/rubrica/";

	public static void main(String[] args) throws IOException {
		File input = new File(PATH_FILES + "rubrica_bassanelli.csv");
		List<Contatto> contatti = ParserCsvRubrica.creaListaContatti(input);
		
		// Cicliamo sui contatti per tenere conto di quante volte compare una chiave
		// Sono duplicati quelle chiavi che hanno un conteggio 2+
		// "" -> 5
		// "tgh@uu.com" -> 3
		
		Map<String, Integer> map = new HashMap<String, Integer>(); // Email (CHIAVE) -> Quante volte compare (VALORE)
		
		for(Contatto contatto : contatti) {
			String key = contatto.getEmail();
			if ( map.containsKey( key ) ) {
				map.put(key, map.get(key) + 1 );
			} else {
				map.put(key, 1);
			}
		}
		
		System.out.println("Stringa vuota compare queste volte: " + map.get(""));
	}

}
