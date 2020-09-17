package it.beije.oort.sba.rubrica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class RubricaWriter {
	
	public void rubricaWriter(File file, int grandezzaRubrica, List<String> nomi, List<String> cognomi, List<String> numeri, List<String> email) throws IOException {
		FileWriter writer = new FileWriter(file);
		ListRandomSelector nome = new ListRandomSelector(nomi);
		ListRandomSelector cognome = new ListRandomSelector(cognomi);
		ListRandomSelector emails = new ListRandomSelector(email);
		PhoneNumberGenerator telefono = new PhoneNumberGenerator(numeri);
		EmailGenerator emailGen = new EmailGenerator();
		Random r = new Random();
		Contatto contact = new Contatto();
		writer.write("NOME;COGNOME;NUMERO;E-MAIL;\n");
		for(int i = 0;i<grandezzaRubrica;i++) {
			if(r.nextInt(5)+1 == 1) contact.setNome(nome.getNext());
			else contact.setNome("");
			if(r.nextInt(3)+1 == 1) contact.setCognome(cognome.getNext());
			else contact.setCognome("");
			contact.setTelefono(telefono.nextNumber());
			contact.setEmail(emailGen.formatEmail(contact.getNome(), contact.getCognome(), emails));
			writer.write(contact.toString());
			writer.write("\n");
			writer.flush();
		}
		writer.close();
	}
}
