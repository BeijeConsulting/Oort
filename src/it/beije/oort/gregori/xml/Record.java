package it.beije.oort.gregori.xml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Record {

	private String nome;
	private String cognome;
	private String telefono;
	private StringBuilder mail = new StringBuilder();
	public static final String[] PREFISSI = {"345", "346", "347", "348", "349"};
	public static final String[] DOMINI = {"gmail.com", "hotmail.com", "hotmail.it", 
											"libero.it", "yahoo.com", "virgilio.it", "tim.it", "alice.it"};
	public static final int MIN = 0000000;
	public static final int MAX = 9999999;
	public static List<String> listTelefono;
	public static List<String> listEmail;
	
	public Record() {}

	public String getNome() {
		return nome;
	}
	
	public String getCognome() {
		return cognome;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getMail() {
		return mail.toString();
	}
	
	public void generateTelefono() {
		Random random = new Random();
		int randomValue = random.nextInt(8) + 1;
		int value = random.nextInt(MAX - MIN + 1) + MIN;
		
		switch(randomValue) {
			case 1:
				break;
			case 2:
				this.telefono = listTelefono.get(random.nextInt(listTelefono.size()));
				break;
			case 3:
			case 4:
				this.telefono = "39" + value;
				break;
			case 5:
			case 6:
			case 7:
			case 8:
				this.telefono = PREFISSI[random.nextInt(PREFISSI.length)] + value;
				break;
			default:
				break;
		}
		
		if(this.telefono != null) listTelefono.add(this.telefono);
	}
	
	public void generateMail() {		
		Random random = new Random();
		
		int randomValueMail = random.nextInt(10) + 1;
		switch(randomValueMail) {
			case 1:
			case 2:
				break;
			case 3:
			case 4:
			case 5:
				this.mail.append(listEmail.get(random.nextInt(listEmail.size())));
				break;
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
				int randomValue3 = random.nextInt(10) + 1;
				String separator = "";
				if(randomValue3 <= 3) {}
				else if(randomValue3 <= 7) {
					separator = ".";
				}
				else {
					separator = "-";
				}
				
				mail.append(nome + separator + cognome + "@" + DOMINI[random.nextInt(DOMINI.length)]);
				int randomValue4 = random.nextInt(5) + 1;
				int nameSurnameSize = nome.length() + 1 + cognome.length();
				if(randomValue4 == 1) {
					StringBuilder sb = new StringBuilder(mail);
					mail.delete(0, mail.length());
					for(int i = 0; i < nameSurnameSize; i++) {
						if(sb.charAt(i) == 'a') {
							mail.append("4");
						}
						else if(sb.charAt(i) == 'e') {
							mail.append("3");
						}
						else if(sb.charAt(i) == 'i') {
							mail.append("1");
						}
						else if(sb.charAt(i) == 'o') {
							mail.append("0");
						}
						else {
							mail.append(sb.charAt(i));
						}
					}
					mail.append("@" + DOMINI[random.nextInt(DOMINI.length)]);
				}
				
				int checkName = random.nextInt(5) + 1;
				if(checkName == 1) {
					mail.delete(0, mail.length());
					mail.append(cognome + "@" + DOMINI[random.nextInt(DOMINI.length)]);
				}
				
				int checkSurname = random.nextInt(3) + 1;
				if(checkSurname == 1) {
					mail.delete(0, mail.length());
					mail.append(nome + "@" + DOMINI[random.nextInt(DOMINI.length)]);
				}
				
				// Formula for random (MAX - MIN + 1) + MIN;
				if(checkName == 1 && checkSurname == 1) {
					int minAscii = 97;
					int maxAscii = 122;
					mail.delete(0, mail.length());
					int randomIndex = random.nextInt(20 - 6 + 1) + 6;
					StringBuilder localSb = new StringBuilder();
					for(int i = 0; i < randomIndex; i++) {
						char c = (char) (random.nextInt(maxAscii - minAscii + 1) + minAscii);
						localSb.append(c);
					}
					mail.append(localSb + "@" + DOMINI[random.nextInt(DOMINI.length)]);
				}
				else if((checkName == 1 && checkSurname != 1) || (checkName != 1 && checkSurname == 1)) {
					int randomValue = random.nextInt(10) + 1;
					if(randomValue != 1) {
						StringBuilder localSb = new StringBuilder();
						int randomValue2 = random.nextInt(101);
						if(randomValue2 < 10) {
							localSb.append("0" + randomValue2);
						}
						else {
							localSb.append(Integer.toString(randomValue2));
						}
						
						if(checkName != 1) {
							mail.delete(0, mail.length());
							mail.append(nome + localSb + "@" + DOMINI[random.nextInt(DOMINI.length)]);
						}
						else if(checkSurname != 1) {
							mail.delete(0, mail.length());
							mail.append(cognome + localSb + "@" + DOMINI[random.nextInt(DOMINI.length)]);
						}
					}
				}
				else if(checkName != 1 && checkSurname != 1) {
					int randomValue1 = random.nextInt(4) + 1;
					if(randomValue1 == 1) {
						mail.delete(0, mail.length());
						mail.append(cognome + separator + nome + "@" + DOMINI[random.nextInt(DOMINI.length)]);
					}
					
					int randomValue2 = random.nextInt(10) + 1;
					if(randomValue2 <= 2) {
						mail.delete(0, mail.length());
						mail.append(nome.charAt(0) + separator + cognome + "@" + DOMINI[random.nextInt(DOMINI.length)]);
					}
					else if (randomValue2 <= 5) {
						mail.delete(0, mail.length());
						List<String> vocali = Arrays.asList("a", "e", "i", "o", "u");
						mail.append(nome.charAt(0));
						for(int i = 1; i < nome.length(); i++) {
							mail.append(nome.charAt(i));
							if(vocali.contains(Character.toString(nome.charAt(i)))) {
								break;
							}
						}
						mail.append(separator + cognome + "@" + DOMINI[random.nextInt(DOMINI.length)]);
					}
				}
				listEmail.add(this.mail.toString());
				break;
			default:
				break;
		}
	}
	
	public void generateNome(ArrayList<String> nomi) {
		Random random = new Random();
		this.nome = nomi.get(random.nextInt(nomi.size())).trim();
	}
	
	public void generateCognome(ArrayList<String> cognomi) {
		Random random = new Random();
		this.cognome = cognomi.get(random.nextInt(cognomi.size())).trim();
	}
}
