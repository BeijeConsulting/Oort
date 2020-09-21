package it.beije.oort.madonia.rubrica;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DuplicateManager {
	
	private static final String PATH_FILES = "/temp/rubrica/";

	public static void main(String[] args) throws IOException {
		File input = new File(PATH_FILES + "rubrica_bassanelli.csv");
		File output = new File(PATH_FILES + "rubrica_output.csv");
		List<Contatto> contatti = ParserCsvRubrica.creaListaContatti(input);
		
//		List<Contatto> contattiSenzaDuplicati = joinContattiSenzaReinserimento(contatti);
		
		// Dichiarazione variabili
				List<Contatto> contattiSenzaDuplicati = new ArrayList<Contatto>();
				Map<String, List<Integer>> indexContatti = new HashMap<String, List<Integer>>();
				
				for (Contatto contatto : contatti) {
					String key = contatto.getEmail();
					// Se abbiamo già una chiave, allora dobbiamo modificare il contatto già inserito
					if (indexContatti.containsKey(key)) {
						// TODO Sistemare errore
						for (int index : indexContatti.get(key)) {
							Contatto contattoDaModificare = contattiSenzaDuplicati.get(index);
							
							// Se il vecchio nome è vuoto o se quello nuovo è vuoto
							boolean isNomeModificabile = contattoDaModificare.getNome().equals("") || contatto.getNome().equals("");
							boolean isCognomeModificabile = contattoDaModificare.getCognome().equals("") || contatto.getCognome().equals("");
							boolean isTelefonoModificabile = contattoDaModificare.getTelefono().equals("") || contatto.getTelefono().equals("");
							
							if(isNomeModificabile && isCognomeModificabile && isTelefonoModificabile) {
								if(contattoDaModificare.getNome().equals("")) {
									contattoDaModificare.setNome(contatto.getNome());
								}
								if (contattoDaModificare.getCognome().equals("")) {
									contattoDaModificare.setCognome(contatto.getCognome());
								}
								if (contattoDaModificare.getTelefono().equals("")) {
									contattoDaModificare.setTelefono(contatto.getTelefono());
								}
							} else {
								contattiSenzaDuplicati.add(contatto);
								List<Integer> list = indexContatti.get(key);
								list.add(contattiSenzaDuplicati.size() - 1);
							}
						}
//						int index = indexContatti.get(key);
//						Contatto contattoDaModificare = contattiSenzaDuplicati.get(index);
//						if(contattoDaModificare.getNome().equals("")) {
//							contattoDaModificare.setNome(contatto.getNome());
//						}
//						if (contattoDaModificare.getCognome().equals("")) {
//							contattoDaModificare.setCognome(contatto.getCognome());
//						}
//						if (contattoDaModificare.getTelefono().equals("")) {
//							contattoDaModificare.setTelefono(contatto.getTelefono());
//						}
//						// Se la chiave non è stata trovata, il contatto è "nuovo" e lo aggiungiamo
					} else {
						contattiSenzaDuplicati.add(contatto);
						List<Integer> list = new ArrayList<Integer>();
						list.add(contattiSenzaDuplicati.size() - 1);
						indexContatti.put(key,list);
					}
				}
		WriterCsvRubrica.writeCsvFile(new String[] {"EMAIL","COGNOME","NOME","TELEFONO"}, contattiSenzaDuplicati, output);
	}

	public static List<Contatto> joinContattiSenzaReinserimento(List<Contatto> contatti) {
		// Dichiarazione variabili
		List<Contatto> contattiSenzaDuplicati = new ArrayList<Contatto>();
		Map<String, Integer> indexContatti = new HashMap<String, Integer>();
		
		for (Contatto contatto : contatti) {
			String key = contatto.getEmail();
			// Se abbiamo già una chiave, allora dobbiamo modificare il contatto già inserito
			if (indexContatti.containsKey(key)) {
				int index = indexContatti.get(key);
				Contatto contattoDaModificare = contattiSenzaDuplicati.get(index);
				if(contattoDaModificare.getNome().equals("")) {
					contattoDaModificare.setNome(contatto.getNome());
				}
				if (contattoDaModificare.getCognome().equals("")) {
					contattoDaModificare.setCognome(contatto.getCognome());
				}
				if (contattoDaModificare.getTelefono().equals("")) {
					contattoDaModificare.setTelefono(contatto.getTelefono());
				}
				// Se la chiave non è stata trovata, il contatto è "nuovo" e lo aggiungiamo
			} else {
				contattiSenzaDuplicati.add(contatto);
				indexContatti.put(key, contattiSenzaDuplicati.size() - 1); // "ppp" -> 0 : "lll" -> 1
			}
		}
		return contattiSenzaDuplicati;
	}

	public static Map<String, Integer> mappaChiavi(List<Contatto> contatti) {
		// Cicliamo sui contatti per tenere conto di quante volte compare una chiave
		// Sono duplicati quelle chiavi che hanno un conteggio 2+
		// "" -> 5
		// "tgh@uu.com" -> 3
		
		Map<String, Integer> map = new HashMap<String, Integer>(); // Email (CHIAVE) -> Quante volte compare (VALORE)
		
		int countDuplicati = 0;
		for(Contatto contatto : contatti) {
			String key = contatto.getEmail();
			if ( map.containsKey( key ) ) {
				if (map.get(key) == 1) { countDuplicati++; }
				map.put(key, map.get(key) + 1 );
			} else {
				map.put(key, 1);
			}
		}
		
		System.out.println("Quante chiavi ci sono: " + map.size());
		System.out.println("Quante chiavi duplicate ci sono: " + countDuplicati);
		//System.out.println("Stringa vuota compare queste volte: " + map.get(""));
		
		return map;
	}

}
