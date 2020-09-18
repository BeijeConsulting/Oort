package it.beije.oort.gregori.parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import it.beije.oort.rubrica.Contatto;

public class WriterCsv {
	
	public static void writeContatti(List<Contatto> contatti, File filePath) throws IOException {
		
		FileWriter fileWriter = new FileWriter(filePath);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		
		Random random = new Random();
		int randomValue1 = random.nextInt(5) + 1;
		int randomValue2 = random.nextInt(3) + 1;
	
		bufferedWriter.append("NOME;COGNOME;TELEFONO;MAIL\n");
		
		for(int i = 0; i < contatti.size(); i++) {
			String name = contatti.get(i).getNome();
			String surname = contatti.get(i).getCognome();
			String phone = contatti.get(i).getTelefono();
			String email = contatti.get(i).getEmail();
			
			StringBuilder sb = new StringBuilder();
			StringBuilder builder = new StringBuilder();
			sb.append(";").append(phone).append(";").append(email).append("\n");
		
			if(randomValue1 == 1) {
				builder.append(";").append(surname);
			}
			else {
				builder.append(name).append(";").append(surname);
			}
			
			if(randomValue2 == 1) {
				builder.append(name).append(";");
			}
			else if(randomValue1 == 1){
				builder.append(name).append(";").append(surname);
			}	
			
			builder.append(sb);
			bufferedWriter.append(builder);
		}
		
		bufferedWriter.flush();
		bufferedWriter.close();
	}
	
}
