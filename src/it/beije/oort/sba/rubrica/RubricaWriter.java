package it.beije.oort.sba.rubrica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class RubricaWriter {
	
	public void rubricaWriter(File file, int grandezzaRubrica, List<String> nomi, List<String> cognomi, List<String> prefissi, List<String> domini) throws IOException {
		FileWriter writer = new FileWriter(file);
		/* Istanzio tre oggetti dalla classe ListRandomSelector. Il costruttore della classe prende in ingresso una Lista di stringhe, in questo
		 * caso la lista di nomi, cognomi, e domini restituiti dal ReadFile che saranno assegnati a una Lista privata all'interno di ListRandomSelector. */
		ListRandomSelector nome = new ListRandomSelector(nomi);
		ListRandomSelector cognome = new ListRandomSelector(cognomi);
		ListRandomSelector dominio = new ListRandomSelector(domini);
		/* Istanzio oggetto dalla classe PhoneNumberGenerator che prende come paramentro del costruttore una lista di stringhe.*/
		PhoneNumberGenerator telefono = new PhoneNumberGenerator(prefissi);
		/* Istanzio un oggetto dalla classe EmailGenerator */
		EmailGenerator email = new EmailGenerator();
		//istanzio un oggetto di tipo Contatto.
		Contatto contatto = new Contatto();
		writer.write("COGNOME;NOME;EMAIL;TELEFONO;\n");
		for(int i = 0;i<grandezzaRubrica;i++) {
			//nome preso random dal file nomi_italiani
			contatto.setNome(nome.getNext());
			//cognome preso random dal file cognomi
			contatto.setCognome(cognome.getNext());
			//numero di telefono generato
			contatto.setTelefono(telefono.nextNumber());
			//email generata
			contatto.setEmail(email.formatEmail(contatto.getNome(), contatto.getCognome(), dominio));
			writer.write(contatto.toString());
			writer.write("\n");
			writer.flush();
		}
		writer.close();
	}
}
