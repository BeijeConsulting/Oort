package it.beije.oort.madonia.rubrica;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicateManager {

	private static final String PATH_FILES = "/temp/rubrica/";

	public static void main(String[] args) throws IOException {
		File input = new File(PATH_FILES + "rubrica_prova_duplicati.csv");
		File output = new File(PATH_FILES + "rubrica_output.csv");
		List<Contatto> contatti = ParserCsvRubrica.creaListaContatti(input);

//		List<Contatto> contattiSenzaDuplicati = joinContattiSenzaReinserimento(contatti);
//		List<Contatto> contattiSenzaDuplicati = DuplicateManager.joinContatti(contatti);
		List<Contatto> contattiSenzaDuplicati = DuplicateManager.joinContattiConAlias(contatti);
		
		WriterCsvRubrica.writeCsvFile(new String[] {"EMAIL","COGNOME","NOME","TELEFONO","ALIAS"}, contattiSenzaDuplicati, output);
	}
	
	public static List<Contatto> joinContattiConAlias(List<Contatto> contatti) {
		List<Contatto> contattiSenzaDuplicati = new ArrayList<Contatto>();
		Map<String, Integer> indexContatti = new HashMap<String, Integer>();
		
		for (Contatto contatto : contatti) {
			String key = contatto.getEmail();
			if (indexContatti.containsKey(key)) {
				int index = indexContatti.get(key);
				Contatto contattoDaModificare = contattiSenzaDuplicati.get(index);
				
				boolean isNomeModificabile = 
						contattoDaModificare.getNome().equals("")
						|| contatto.getNome().equals("")
						|| contattoDaModificare.getNome().equals(contatto.getNome());
				boolean isCognomeModificabile = 
						contattoDaModificare.getCognome().equals("") 
						|| contatto.getCognome().equals("")
						|| contattoDaModificare.getCognome().equals(contatto.getCognome());
				boolean isTelefonoModificabile = 
						contattoDaModificare.getTelefono().equals("") 
						|| contatto.getTelefono().equals("")
						|| contattoDaModificare.getTelefono().equals(contatto.getTelefono());
				
				if (isNomeModificabile && isCognomeModificabile) {
					if(contattoDaModificare.getNome().equals("")) {
						contattoDaModificare.setNome(contatto.getNome());
					}
					if (contattoDaModificare.getCognome().equals("")) {
						contattoDaModificare.setCognome(contatto.getCognome());
					}
				} else {
					StringBuilder s = new StringBuilder()
							.append(contatto.getNome())
							.append(" ")
							.append(contatto.getCognome());
					contattoDaModificare.addAlias(s.toString().trim());
				}
				
				if(isTelefonoModificabile) {
					if (contattoDaModificare.getTelefono().equals("")) {
						contattoDaModificare.setTelefono(contatto.getTelefono());
					}
				}
			} else {
				contattiSenzaDuplicati.add(contatto);
				indexContatti.put(key, contattiSenzaDuplicati.size() - 1);
			}
		}
		return contattiSenzaDuplicati;
	}

 	public static List<Contatto> joinContatti(List<Contatto> contatti) {
		// Dichiarazione variabili
		List<Contatto> contattiSenzaDuplicati = new ArrayList<Contatto>();
		Map<String, List<Integer>> indexContatti = new HashMap<String, List<Integer>>();

		for (Contatto contatto : contatti) {
			String key = contatto.getEmail();
			// Se abbiamo già una chiave, allora dobbiamo modificare il contatto già inserito
			if (indexContatti.containsKey(key)) {
				boolean isContattoJoined = false;
				for (int index : indexContatti.get(key)) {
					Contatto contattoDaModificare = contattiSenzaDuplicati.get(index);
					
//					System.out.println("Contatto precedente: " + contattoDaModificare);
//					System.out.println("Contatto nuovo: " + contatto);

					// Se il vecchio nome è vuoto o se quello nuovo è vuoto
					boolean isNomeModificabile = 
							contattoDaModificare.getNome().equals("")
							|| contatto.getNome().equals("")
							|| contattoDaModificare.getNome().equals(contatto.getNome());
					boolean isCognomeModificabile = 
							contattoDaModificare.getCognome().equals("") 
							|| contatto.getCognome().equals("")
							|| contattoDaModificare.getCognome().equals(contatto.getCognome());
					boolean isTelefonoModificabile = 
							contattoDaModificare.getTelefono().equals("") 
							|| contatto.getTelefono().equals("")
							|| contattoDaModificare.getTelefono().equals(contatto.getTelefono());

					if(isNomeModificabile && isCognomeModificabile && isTelefonoModificabile) {
						isContattoJoined = true;
						if(contattoDaModificare.getNome().equals("")) {
							contattoDaModificare.setNome(contatto.getNome());
						}
						if (contattoDaModificare.getCognome().equals("")) {
							contattoDaModificare.setCognome(contatto.getCognome());
						}
						if (contattoDaModificare.getTelefono().equals("")) {
							contattoDaModificare.setTelefono(contatto.getTelefono());
						}
//						System.out.println("Contatto modificato: " + contattoDaModificare);
						break;
					}
				}
				if(!isContattoJoined) {
					contattiSenzaDuplicati.add(contatto);
					List<Integer> list = indexContatti.get(key);
					list.add(contattiSenzaDuplicati.size() - 1);
				}
			} else {
				contattiSenzaDuplicati.add(contatto);
				List<Integer> list = new ArrayList<Integer>();
				list.add(contattiSenzaDuplicati.size() - 1);
				indexContatti.put(key,list);
			}
//			System.out.println("K: " + key + "; V: " + indexContatti.get(key));
		}
		return contattiSenzaDuplicati;
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
