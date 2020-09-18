package it.beije.oort.file.sala.rubrica;

import java.util.Random;

import it.beije.oort.file.sala.GeneraMail;

public class Contatto2 {
	private String nome;
	private String cognome;
	private String cell;
	private String email;
	
	public Contatto2(String nome) {
		this(nome, "", "", "");
	}
	
	public Contatto2(String nome, String cognome) {
		this(nome, cognome, "", "");
	}
	
	public Contatto2(String nome, String cognome, String prefisso) {
		this(nome, cognome, prefisso, "");
	}
	
	public Contatto2(String nome, String cognome, String prefisso, String dominio) {
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
		return new StringBuilder("\"")
				.append(nome)
				.append("\";\"")
				.append(cognome)
				.append("\";\"").append(cell).append("\";\"").append(email).append("\"").toString();
	}
	
	public String toFormattedString2() {
		return new StringBuilder("\"")
				.append(cell).append("\";\"")
				.append(email).append("\";\"")
				.append(nome).append("\";\"")
				.append(cognome).append("\"").toString();
	}
	
	
}