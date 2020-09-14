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
		writer.write("NOME;COGNOME;NUMERO;E-MAIL;\n");
		for(int i = 0;i<grandezzaRubrica;i++) {
			String a = nome.getNext();
			String b = cognome.getNext();
			String formattedEmail = formatEmail(a,b,emails);
			line+=a+";"+b+";"+numero.getNext()+ sette.nextNumber() + ";" + formattedEmail + ";" + "\n";
		}
		writer.write(line);
		writer.flush();
		writer.close();
	}

	private static String formatEmail(String a, String b, ListRandomSelector suffix) {
		Random r = new Random();
		int n1 = r.nextInt(5) + 1;
		int n2 = r.nextInt(3) + 1;
		String ret = "";
		boolean nome = true, cognome = true;
		if(n1==1) nome = false;
		if(n2==1) cognome = false;
		if(!nome && !cognome) {
			for(int i = 0; i<r.nextInt(14)+6; i++) {
				ret+=(char)(r.nextInt(123-97)+97);
			}
		}else if(!nome){
			ret += b;
		}else if(!cognome) {
			ret += a;
		}else {
			ret += a + "." + b;
		}
		ret += "@" + suffix.getNext();
		return ret;
	}
}
