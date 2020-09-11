package it.beije.oort.sba.rubrica;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RubricaWriter {
	
	public void rubricaWriter(File file, int grandezzaRubrica, List nomi, List cognomi, List numeri, List email) throws IOException {
		FileWriter writer = new FileWriter(file);
		String line="";
		ListRandomSelector nome = new ListRandomSelector(nomi);
		ListRandomSelector cognome = new ListRandomSelector(cognomi);
		ListRandomSelector numero = new ListRandomSelector(numeri);
		ListRandomSelector emails = new ListRandomSelector(email);
		PhoneNumberGenerator sette = new PhoneNumberGenerator();
		for(int i = 0;i<grandezzaRubrica;i++) {
			String a = nome.getNext();
			String b = cognome.getNext();
			line+=a+";"+b+";"+numero.getNext()+ sette.nextNumber() + ";" + a +"." + b + "@"+ emails.getNext()+"\n";
		}
		writer.write(line);
		
		writer.flush();
		writer.close();
	}
	


	public static void main(String[] args) {


	}

}
