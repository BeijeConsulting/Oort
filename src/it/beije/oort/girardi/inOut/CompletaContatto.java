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

	private static final Map<String,List<Contatto>> MAPPA_TELEFONI = new HashMap<>();
	
	private static final List<Contatto> NO_TELEFONO = new ArrayList<>();
	
	
// ------------ METODI ------------

	
//data una List di Contatti, riempie la HashMap con il numero di telefono come key
//e riempie la List di Conttatti NO_TELEFONO con i contatti senza numero.
	public static void fillHashMap (List<Contatto> listaContatti) {
		for (Contatto contatto : listaContatti) {
			if (contatto.getTelefono() == "" || contatto.getTelefono() == null) //no telefono
				NO_TELEFONO.add(contatto);
			else if (MAPPA_TELEFONI.containsKey(contatto.getTelefono())) { //telefono doppio
				if (CompletaContatto.areCompatible(c1, c2)) {
					
				}
				else { //mette in coda nella lista i contatti con = tel e != campi
					List <Contatto> listStessoTel = MAPPA_TELEFONI.get(contatto.getTelefono());
					listStessoTel.add(contatto);
					MAPPA_TELEFONI.put(contatto.getTelefono(), listStessoTel); 
				}
			}
			else { // telefono diverso dai precedenti
				List <Contatto> listNewTel = new ArrayList<>();
				listNewTel.add(contatto);
				MAPPA_TELEFONI.put(contatto.getTelefono(), listNewTel); 
			}	
		}
	}
	
	
//metodo che verifica se due contatti incompleti sono compatibili
	public static boolean areCompatible(Contatto c1, Contatto c2) {
		if (c1.getNome() == "" || c2.getNome() == "") {}
//		if (c1.getNome() == null || c2.getNome() == null) {}
		else if (c1.getName() == c2.getName()) {}
		else return false;
		
		if (c1.getCognome() == "" || c2.getCognome() == "") {}
//		if (c1.getCognome() == null || c2.getCognome() == null) {}
		else if (c1.getCognome() == c2.getCognome()) {}
		else return false;
		
		if (c1.getEmail() == "" || c2.getEmail() == "") {}
//		if (c1.getEmail() == null || c2.getEmail() == null) {}
		else if (c1.getEmail() == c2.getEmail()) {}
		else return false;
		
		if (c1.getTelefono() == "" || c2.getTelefono() == "") {}
		else if (c1.getTelefono() == null || c2.getTelefono() == null) {}
		else if (c1.getTelefono() == c2.getTelefono()) {}
		else return false;
		
		return true;
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
		File file = new File(PATH_FILES + "rubricaNonCompleta.csv");
		if (file.exists()) 
			System.out.println("esiste, lo carico... ");
		else
			System.err.println("il file che si vuole caricare non esiste");
		
		//metodo per la List di contatti
		List<Contatto> listaContatti = RubricaCSV.getListContatti(file); 
		System.out.println(listaContatti.size());
//		RubricaCSV.writeContatti(listaContatti, PATH_FILES + "rubricaNonComplProvv.csv");
		
		//metodo per riempire l'HashMap
		CompletaContatto.fillHashMap (listaContatti);
		
		//metodo per trasformare l' HashMap in una List di Contatti
		List<Contatto> listaContattiCompleta = CompletaContatto.fromHmToList(MAPPA_TELEFONI);
		System.out.println(listaContattiCompleta.size());
		
		//data una List di Contatti crea e scrive un file csv nel pathfile: 
		RubricaCSV.writeContatti(listaContattiCompleta, PATH_FILES + "rubricaCompleta.csv");
		
	}
}
