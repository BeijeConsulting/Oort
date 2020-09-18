package it.beije.oort.sba.rubrica;

import java.io.*;
import java.util.*;

public class ReadFile {
	/* Il metodo getContent legge un file in ingresso e restituisce una Lista di Stringhe. */
	public List<String> getContent(File file) throws IOException {
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<String> campi = new ArrayList<String>();
		while (bufferedReader.ready()) {
			/* Aggiungo alla lista di stringhe campi la riga che lego dal File in ingresso al metodo 
			 * Sarà ad esempio un nome, un cognome, o un dominio etc. */
			campi.add(bufferedReader.readLine());
		}
		bufferedReader.close();
		//restituisco la lista di stringhe con i rispettivi campi del file.
		return campi;
	}
}
