package it.beije.oort.franceschi.csvToXml;

import java.util.Random;

import it.beije.oort.file.rubrica.GeneraMail;

/**
 * Bean of the Contatto Object.
 * 
 * @author Alessio Franceschi
 *
 */
public class ContattoBean {
	private String nome;
	private String cognome;
	private String cell;
	private String email;

	public ContattoBean() {
	}

	public ContattoBean(String nome) {
		this(nome, "", "", "");
	}

	public ContattoBean(String nome, String cognome) {
		this(nome, cognome, "", "");
	}

	public ContattoBean(String nome, String cognome, String prefisso) {
		this(nome, cognome, prefisso, "");
	}

	public ContattoBean(String nome, String cognome, String prefisso, String dominio) {
		Random r = new Random();
		StringBuilder suffisso = new StringBuilder();
		for (int i = 0; i < 7; i++) {
			suffisso.append(r.nextInt(10));
		}

		this.nome = nome;
		this.cognome = cognome;
		this.cell = prefisso + suffisso.toString();
		this.email = GeneraMail.generaMail(nome, cognome, dominio);
	}

	public String getCell() {
		return cell;
	}

	public String getCognome() {
		return cognome;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the formatted string following the CSV convention with quotes.
	 */
	public String toFormattedString() {
		return new StringBuilder("\"").append(nome).append("\";\"").append(cognome).append("\";\"").append(cell)
				.append("\";\"").append(email).append("\"").toString();
	}

	public String toString() {
		return new StringBuilder("Nome: " + this.getNome() + ". Cognome: " + this.getCognome() + ". Email: "
				+ this.getEmail() + ". Telefono: " + this.getCell()).toString();
	}
}
