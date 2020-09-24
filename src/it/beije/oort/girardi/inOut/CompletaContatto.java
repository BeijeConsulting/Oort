package it.beije.oort.girardi.inOut;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import it.beije.oort.rubrica.Contatto;

public class CompletaContatto {
	
	private static final String PATH_FILES = "C:\\Users\\Padawan05\\Desktop\\file_testo\\";

	private static final Map<String,List<Contatto>> MAP_TELEFONI = new HashMap<>();
	
	private static final List<Contatto> NO_TELEFONO = new ArrayList<>();
	
	
// ------------ METODI ------------

	
//data una List di Contatti, riempie la HashMap con il numero di telefono come key
//e riempie la List di Conttatti NO_TELEFONO con i contatti senza numero.
	public static void fillHashMap (List<Contatto> listaContatti) {
		for (Contatto contatto : listaContatti) {
			//no telefono:
			if ( contatto.getTelefono() == null || contatto.getTelefono().equals("null") ) 
				NO_TELEFONO.add(contatto);
			
			//telefono doppio:
			else if (MAP_TELEFONI.containsKey(contatto.getTelefono())) { 
				//confronto con ogni contatto salvato con lo stesso telefono
				boolean giaAggiunto = false;
				for (Contatto c : MAP_TELEFONI.get(contatto.getTelefono())) {
					int k = 0;
					if (CompletaContatto.areCompatible(c, contatto)) {
						//riempio i campi vuoti del contatto già salvato e lo salvo.
						List <Contatto> listStessoTel = MAP_TELEFONI.get(contatto.getTelefono());
						listStessoTel.remove(k);
						listStessoTel.add(k, CompletaContatto.matchCampi(c, contatto));
						MAP_TELEFONI.put(contatto.getTelefono(), listStessoTel); 
						//fa il procedimento mentre crea la mappa, quindi non ci sono
						//due contatti con lo stesso numero e compatibili.
						giaAggiunto = true;
						break; 
					}
					k++;
				}
				//mette in coda nella lista i contatti con = tel e != campi
				if (!giaAggiunto){ 
					List <Contatto> listStessoTel = MAP_TELEFONI.get(contatto.getTelefono());
					listStessoTel.add(contatto);
					MAP_TELEFONI.put(contatto.getTelefono(), listStessoTel); 
				}
			}
			
			// telefono diverso dai precedenti
			else { 
				List <Contatto> listNewTel = new ArrayList<>();
				listNewTel.add(contatto);
				MAP_TELEFONI.put(contatto.getTelefono(), listNewTel); 
			}
		}
	}
	
	
//metodo che verifica se due contatti incompleti sono compatibili
	public static boolean areCompatible(Contatto c1, Contatto c2) {
		if (c1.getNome() == "" || c2.getNome() == "") {}
//		else if (c1.getNome() == null || c2.getNome() == null) {}
		else if (c1.getNome() == c2.getNome()) {}
		else return false;
		
		if (c1.getCognome() == "" || c2.getCognome() == "") {}
//		else if (c1.getCognome() == null || c2.getCognome() == null) {}
		else if (c1.getCognome() == c2.getCognome()) {}
		else return false;
		
		if (c1.getEmail() == "" || c2.getEmail() == "") {}
//		else if (c1.getEmail() == null || c2.getEmail() == null) {}
		else if (c1.getEmail() == c2.getEmail()) {}
		else return false;
		
		if (c1.getTelefono() == "" || c2.getTelefono() == "") {}
		else if (c1.getTelefono() == null || c2.getTelefono() == null) {}
		else if (c1.getTelefono() == c2.getTelefono()) {}
		else return false;
		
		return true;
	}
	
	
//metodo che riempie i campi vuoti del primo contatto in ingresso con i 
//campi del secondo contatto in ingresso. (anche se non compatibili)
	public static Contatto matchCampi(Contatto c1, Contatto c2) {
		Contatto unione = c1;
		// se il campo di c1 è vuoto lo riempio:
		//il campo di c1 lo ho già, aggiungo sempre il campo di c2 quindi.
		if (c1.getNome() == "" || c1.getNome() == null) 
			unione.setNome(c2.getNome());
		if (c1.getCognome() == "" || c1.getCognome() == null) 
			unione.setCognome(c2.getCognome());
		if (c1.getEmail() == "" || c1.getEmail() == null) 
			unione.setEmail(c2.getEmail());
		if (c1.getTelefono() == "" || c1.getTelefono() == null) 
			unione.setTelefono(c2.getTelefono());
		
		return unione;
	}
	
	
//metodo per trasformare l' HashMap<String,List<Contatto>> in una List di Contatti
	public static List<Contatto> fromHmToList(Map<String,List<Contatto>> mappa) {
		List<Contatto> listaCompleta = new ArrayList<>();
		// mappa.values() ridà una collection ---> Collection<List<Contatto>>
		for (List<Contatto> listaKey : mappa.values()) {
			for (Contatto contatto : listaKey) {
				listaCompleta.add(contatto);
			}
		}
		return listaCompleta;
	}
	
	
// -------------- MAIN -----------------
/*data una rubrica con contatti incompleti, combina i contatti con gli stessi 
numeri se compatibili, e se non compatibili li riporta entrambi.
Come risultato ridà un file csv il più possibile completo ed ordinato in base 
numero telefonico.
 */
	public static void main(String[] args) 
			throws IOException {
		//lettura:
//		File file = new File(PATH_FILES + "rubricaNonCompleta.csv");
		File file = new File(PATH_FILES + "rubricaNonComplProvv.csv");
		if (file.exists()) 
			System.out.println("esiste, lo carico... ");
		else
			System.err.println("il file che si vuole caricare non esiste");
		
		//metodo per la List di contatti
		List<Contatto> listaContatti = RubricaCSV.getListContatti(file); 
		System.out.println("# contatti file partenza: " + listaContatti.size());
		for(int i=0; i < listaContatti.size(); i++) {
			System.out.println(listaContatti.get(i));
		}
//		System.out.println(listaContatti.get(4).getTelefono().equals("null"));
		
//		RubricaCSV.writeContatti(listaContatti, PATH_FILES + "rubricaNonComplProvv.csv");
		
		//metodo per riempire l'HashMap
		CompletaContatto.fillHashMap (listaContatti);
		System.out.println("");
		for(int i=0; i < NO_TELEFONO.size(); i++) {
			System.out.println(NO_TELEFONO.get(i));
		}
		
		//metodo per trasformare l' HashMap in una List di Contatti
		List<Contatto> listaContattiCompleta = CompletaContatto.fromHmToList(MAP_TELEFONI);
		System.out.println("# contatti file destinazione: " + listaContattiCompleta.size());
		
		//data una List di Contatti crea e scrive un file csv nel pathfile: 
		RubricaCSV.writeContatti(listaContattiCompleta, PATH_FILES + "rubricaCompleta.csv");
		
	}
}
