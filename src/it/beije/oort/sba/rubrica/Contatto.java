package it.beije.oort.sba.rubrica;

import java.util.Random;

public class Contatto {
	
	private String nome;
	private String cognome;
	private String telefono;
	private String email;
	
	public Contatto() {}
	
	public Contatto(String nome, String cognome, String telefono) {
		this(nome, cognome, telefono, "");
	}
	
	public Contatto(String nome, String cognome, String telefono, String email) {
		this.nome = nome;
		this.cognome = cognome;
		this.telefono = telefono;
		this.email = email;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		Random r = new Random();
		if(r.nextInt(5)+1 == 1) builder.append("");
		else builder.append(this.nome);
		builder.append(';');
		if(r.nextInt(3)+1 == 1) builder.append("");
		else builder.append(this.cognome);
		builder.append(';')
			.append(this.telefono).append(';')
			.append(this.email).append(';');
		
		return builder.toString();
	}
	
}
