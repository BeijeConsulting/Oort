package it.beije.oort.madonia.rubrica;

import java.util.ArrayList;
import java.util.List;

public class Contatto implements Comparable<Contatto> {

	private String nome;
	private String cognome;
	private String telefono;
	private String email;
	private List<String> alias = new ArrayList<String>();

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

	//	public String getNominativo() { //nominativo
	//		return this.nome + " " + this.cognome;
	//	}

	public String getAlias() {
		return alias.toString();
	}
	public void addAlias(String alias) {
		this.alias.add(alias);
	}

	public String toString() {
		StringBuilder builder = new StringBuilder("contatto [");
		builder.append("nome : ").append(this.nome)
			.append(" - cognome : ").append(this.cognome)
			.append(" - telefono : ").append(this.telefono)
			.append(" - email : ").append(this.email).append("]");

		return builder.toString();
	}

	public int compareTo(Contatto contatto) {
		int posizione = this.getCognome().toLowerCase().compareTo(contatto.getCognome().toLowerCase());
		
		if (posizione == 0) {
			posizione = this.getNome().toLowerCase().compareTo(contatto.getNome().toLowerCase());
		}
		
		return posizione;
	}

}