package it.beije.oort.files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvBuilder {
	/* Il metodo buildContatti prende in ingresso una lista di Contatti e un file di destinazione,
	 * scrivendo in esso i Contatti in formato csv. */
	public void buildContatti(List<Contatto> contatti, File fileDestinazione) throws IOException {
		FileWriter writer = new FileWriter(fileDestinazione);
		writer.write("NOME;COGNOME;EMAIL;TELEFONO;\n");
		for (int i = 0; i < contatti.size(); i++) {
			String nome = contatti.get(i).getNome();
			String cognome = contatti.get(i).getCognome();
			String email = contatti.get(i).getEmail();
			String telefono = contatti.get(i).getTelefono();
			writer.write(contatti.get(i).toString());
			writer.write("\n");
			writer.flush();
		}
		writer.close();
		System.out.println("Done creating CSV file!");
	}
}
