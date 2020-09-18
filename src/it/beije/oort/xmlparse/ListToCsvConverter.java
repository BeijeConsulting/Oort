package it.beije.oort.xmlparse;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ListToCsvConverter {
	public void buildCsv(List<Contatto> contatti, File fileDestinazione) throws IOException {
		FileWriter writer = new FileWriter(fileDestinazione);
		writer.write("COGNOME;NOME;EMAIL;TELEFONO;\n");
		for (int i = 0; i < contatti.size(); i++) {
			String nome = contatti.get(i).getNome();
			String cognome = contatti.get(i).getCognome();
			String email = contatti.get(i).getEmail();
			String telefono = contatti.get(i).getTelefono();
			writer.write(cognome + ";" + nome + ";" + email + ";" + telefono + ";" + "\n");
			writer.flush();
		}
		writer.close();
	}
}
