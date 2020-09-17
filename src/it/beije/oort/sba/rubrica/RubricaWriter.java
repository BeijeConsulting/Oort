package it.beije.oort.sba.rubrica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RubricaWriter {
	
	public void rubricaWriter(File file, int grandezzaRubrica, List<String> nomi, List<String> cognomi, List<String> numeri, List<String> email) throws IOException {
		FileWriter writer = new FileWriter(file);
		ListRandomSelector nome = new ListRandomSelector(nomi);
		ListRandomSelector cognome = new ListRandomSelector(cognomi);
		ListRandomSelector numero = new ListRandomSelector(numeri);
		ListRandomSelector emails = new ListRandomSelector(email);
		PhoneNumberGenerator sette = new PhoneNumberGenerator();
		EmailGenerator emailGen = new EmailGenerator();
		Contatto contact = new Contatto();
		writer.write("NOME;COGNOME;NUMERO;E-MAIL;\n");
		for(int i = 0;i<grandezzaRubrica;i++) {
			contact.setNome(nome.getNext());
			contact.setCognome(cognome.getNext());
			contact.setTelefono(numero.getNext() + sette.nextNumber());
			contact.setEmail(emailGen.formatEmail(contact.getNome(), contact.getCognome(), emails));
			writer.write(contact.toString());
			writer.write("\n");
			writer.flush();
			//line.append(a).append(";").append(b).append(";").append(numero.getNext()).append(sette.nextNumber()).append(";").append(formattedEmail).append(";\n");
			//line.append(a+";"+b+";"+numero.getNext()+ sette.nextNumber() + ";" + formattedEmail + ";" + "\n");
		}
		writer.close();
	}
}
