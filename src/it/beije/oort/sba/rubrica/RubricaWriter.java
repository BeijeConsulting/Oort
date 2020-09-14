package it.beije.oort.sba.rubrica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class RubricaWriter {
	
	public void rubricaWriter(File file, int grandezzaRubrica, List<String> nomi, List<String> cognomi, List<String> numeri, List<String> email) throws IOException {
		FileWriter writer = new FileWriter(file);
		String line="";
		ListRandomSelector nome = new ListRandomSelector(nomi);
		ListRandomSelector cognome = new ListRandomSelector(cognomi);
		ListRandomSelector numero = new ListRandomSelector(numeri);
		ListRandomSelector emails = new ListRandomSelector(email);
		PhoneNumberGenerator sette = new PhoneNumberGenerator();
		EmailGenerator emailGen = new EmailGenerator();
		writer.write("NOME;COGNOME;NUMERO;E-MAIL;\n");
		for(int i = 0;i<grandezzaRubrica;i++) {
			String a = nome.getNext();
			String b = cognome.getNext();
			String formattedEmail = emailGen.formatEmail(a, b, emails);
			line+=a+";"+b+";"+numero.getNext()+ sette.nextNumber() + ";" + formattedEmail + ";" + "\n";
		}
		writer.write(line);
		writer.flush();
		writer.close();
	}
}
