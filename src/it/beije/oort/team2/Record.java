package it.beije.oort.team2;


import java.util.ArrayList;
import java.util.Random;

public class Record {

	private String nome;
	private String cognome;
	private String telefono;
	private String mail;
	public static final String[] PREFISSI = {"345", "346", "347", "348", "349"};
	public static final String[] DOMINI = {"gmail.com", "hotmail.com", "hotmail.it", 
											"libero.it", "yahoo.com", "virgilio.it", "tim.it", "alice.it"};
	public static final int MIN = 0000000;
	public static final int MAX = 9999999;
	
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
		return mail;
	}
	
	public void generateTelefono() {
		Random random = new Random();
		int value = random.nextInt(MAX - MIN + 1) + MIN;
		this.telefono = PREFISSI[random.nextInt(PREFISSI.length)] + value;
	}
	
	public void generateMail() {
		Random random = new Random();
		this.mail = this.getNome() + "." + this.getCognome() + "@" + DOMINI[random.nextInt(DOMINI.length)];
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
