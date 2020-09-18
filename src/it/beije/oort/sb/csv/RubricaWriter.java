package it.beije.oort.sb.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class RubricaWriter {
	
	public void rubricaWriter(File file, int grandezzaRubrica, List<String> nomi, List<String> cognomi, List<String> numeri, List<String> email) throws IOException {
		FileWriter writer = new FileWriter(file);
		Random r = new Random();
		ListRandomSelector nome = new ListRandomSelector(nomi);
		ListRandomSelector cognome = new ListRandomSelector(cognomi);
		ListRandomSelector emails = new ListRandomSelector(email);
		PhoneNumberGenerator png  = new PhoneNumberGenerator();
		List<String> appoggio = new ArrayList<String>();
		List<String> appoggioEmail = new ArrayList<String>();
		EmailGenerator emailGen = new EmailGenerator();
		Contatto contact = new Contatto();
		writer.write("COGNOME;NOME;EMAIL;TELEFONO;\n");
		int dado, dadoE;
		StringBuilder builder = new StringBuilder();
		for(int i = 0;i<grandezzaRubrica;i++) {
			dado  = r.nextInt(8)+1;
			dadoE = r.nextInt(10)+1;
			contact.setNome(nome.getNext());
			contact.setCognome(cognome.getNext());
			if(dado==1)		contact.setTelefono("");
			else if(dado==2) {
				if(appoggio.size()==0) contact.setTelefono("");
				else contact.setTelefono(appoggio.get(r.nextInt(appoggio.size())));
			}
			else if(dado<=4) {
				png.numeroTel39(numeri, appoggio);	
				contact.setTelefono(appoggio.get(appoggio.size()-1)); //prendo l'ultimo elemento generato e antepongo il +39
			}
			else {
				png.numeroTel(numeri, appoggio);
				contact.setTelefono(appoggio.get(appoggio.size()-1)); //imposto l'ultimo numero generato
			}
			
			if(dadoE<=2) contact.setEmail("");
			else if(dadoE<=5) {
					int a = appoggioEmail.size();
					if(a==0) contact.setEmail("");  //l'ho messo perchè mi dava problemi con size(0) index(0)
					else {
						contact.setEmail(appoggioEmail.get(a-1));
					}
			}
			else {
				appoggioEmail.add(emailGen.formatEmail(contact.getNome(), contact.getCognome(), emails));
				contact.setEmail(appoggioEmail.get(appoggioEmail.size()-1));
			}
			
			if((r.nextInt(3)+1)!=1) builder.append(contact.getCognome());
			builder.append(";");
			if((r.nextInt(5)+1)!=1) builder.append(contact.getNome());
			builder.append(";");
			builder.append(contact.getEmail()).append(";").append(contact.getTelefono()).append(";\n");

			}
		
		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}
}