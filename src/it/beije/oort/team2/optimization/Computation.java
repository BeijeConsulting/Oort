package it.beije.oort.team2.optimization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Computation {
	public static final ArrayList<Contatto> contatti = new ArrayList<>();
	public static final String[] PREFISSI = {"345", "346", "347", "348", "349"};
	public static final String[] DOMINI = {"gmail.com", "hotmail.com", "hotmail.it", 
											"libero.it", "yahoo.com", "virgilio.it", "tim.it", "alice.it"};
	
	public Computation() {
		createContacts();
	}
	
	private void createContacts() {
		for(int i = 0; i < Rubrica.NUM_RECORDS; i++) {
			Contatto c = new Contatto();
			c.setNome(generateNome(MyReader.nomi));
			c.setCognome(generateCognome(MyReader.cognomi));
			c.setTelefono(generateTelefono());
			c.setEmail(generateEmail());
			contatti.add(c);
		}
	}
	
	private String generateNome(ArrayList<String> nomi) {
		Random random = new Random();
		return nomi.get(random.nextInt(nomi.size())).trim();
	}
	
	private String generateCognome(ArrayList<String> cognomi) {
		Random random = new Random();
		return cognomi.get(random.nextInt(cognomi.size())).trim();
	}
	
	private String generateTelefono() {
		Random random = new Random();
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < 7; i++) {
			builder.append(random.nextInt(10));
		}
		builder.append(PREFISSI[random.nextInt(PREFISSI.length)]);
		return builder.toString();
	}
	
	private String generateEmail() {
		Random random = new Random();
		StringBuilder finalDomain = new StringBuilder();
		StringBuilder mail = new StringBuilder();
		String separator;
		String nome = generateNome(MyReader.nomi);
		String cognome = generateCognome(MyReader.cognomi);
		
		finalDomain.append('@').append(DOMINI[random.nextInt(DOMINI.length)]);
		
		int randomValue3 = random.nextInt(10) + 1;

		if(randomValue3 <= 3) {
			separator = "";
		}
		else if(randomValue3 <= 7) {
			separator = ".";
		}
		else {
			separator = "-";
		}
		
		int checkName = random.nextInt(5) + 1;
		int checkSurname = random.nextInt(3) + 1;
		
		if(checkName == 1 && checkSurname == 1) {
			int minAscii = 97;
			int maxAscii = 122;
			int randomIndex = random.nextInt(20 - 6 + 1) + 6;
			StringBuilder localSb = new StringBuilder();
			for(int i = 0; i < randomIndex; i++) {
				char c = (char) (random.nextInt(maxAscii - minAscii + 1) + minAscii);
				localSb.append(c);
			}
			mail.append(localSb).append(finalDomain);
		}
		else if((checkName == 1 && checkSurname != 1) || (checkName != 1 && checkSurname == 1)) {
			int randomValue = random.nextInt(10) + 1;
			if(randomValue != 1) {
				StringBuilder localSb = new StringBuilder();
				int randomValue2 = random.nextInt(101);
				if(randomValue2 < 10) {
					localSb.append("0").append(randomValue2);
				}
				else {
					localSb.append(Integer.toString(randomValue2));
				}
				
				if(checkName != 1) {
					mail.append(nome).append(localSb).append(finalDomain);
				}
				else if(checkSurname != 1) {
					mail.append(cognome).append(localSb).append(finalDomain);
				}
			}
		}
		else if(checkName != 1 && checkSurname != 1) {
			int randomValue1 = random.nextInt(4) + 1;
			if(randomValue1 == 1) {
				mail.append(cognome).append(separator).append(nome).append(finalDomain);
			}
			
			int randomValue2 = random.nextInt(10) + 1;
			if(randomValue2 <= 2) {
				mail.append(nome.charAt(0)).append(separator).append(cognome).append(finalDomain);
			}
			else if (randomValue2 <= 5) {
				List<String> vocali = Arrays.asList("a", "e", "i", "o", "u");
				mail.append(nome.charAt(0));
				for(int i = 1; i < nome.length(); i++) {
					mail.append(nome.charAt(i));
					if(vocali.contains(Character.toString(nome.charAt(i)))) {
						break;
					}
				}
				mail.append(separator).append(cognome).append(finalDomain);
			}
		}
		else if(checkSurname == 1) {
			mail.append(nome).append(finalDomain);
		}
		else if(checkName == 1) {
			mail.append(cognome).append(finalDomain);
		}
		else {
			mail.append(nome).append(separator).append(cognome).append(finalDomain);
		}
		
		return mail.toString();
	}
}
