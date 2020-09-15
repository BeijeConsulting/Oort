package it.beije.oort.file.rubrica;

import java.util.Random;

public class Contatto {
	private String nome;
	private String cognome;
	private String cell;
	private String email;
	
	public Contatto(String nome) {
		this(nome, "", "", "");
	}
	
	public Contatto(String nome, String cognome) {
		this(nome, cognome, "", "");
	}
	
	public Contatto(String nome, String cognome, String prefisso) {
		this(nome, cognome, prefisso, "");
	}
	
	public Contatto(String nome, String cognome, String prefisso, String dominio) {
		Random r = new Random();
		StringBuilder suffisso=new StringBuilder();
		for(int i = 0; i < 7; i++) {
			suffisso.append(r.nextInt(10));
		}
		
		this.nome = nome;
		this.cognome = cognome;
		this.cell = prefisso + suffisso.toString();
		this.email = GeneraMail.generaMail(nome, cognome, dominio);
	}
	
	public String getNome() {
		return nome;
	}
	public String getCognome() {
		return cognome;
	}
	public String getCell() {
		return cell;
	}
	public String getEmail() {
		return email;
	}
	
	/**
	 * @return the formatted string following the csv convention with quotes.
	 */
	public String toFormattedString() {
		return "\""+nome+"\";\""+cognome+"\";\""+cell+"\";\""+email+"\""; //TODO: fare con StringBuilder
	}
}
