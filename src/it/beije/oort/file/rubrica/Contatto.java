package it.beije.oort.file.rubrica;


public class Contatto {
	private String nome;
	private String cognome;
	private String cell;
	private String email;
	
	public Contatto() {
	}
	
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
		this.nome = nome;
		this.cognome = cognome;
		this.cell = GeneraNumero.generaNumero();
		Valori.cellCompleti.add(cell);
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
		return new StringBuilder("\"").append(cognome).append("\";\"").append(nome).append("\";\"").append(email)
				.append("\";\"").append(cell).append("\"").toString();
	}

	public String toString() {
		return new StringBuilder("Nome: " + this.getNome() + ". Cognome: " + this.getCognome() + ". Email: "
				+ this.getEmail() + ". Telefono: " + this.getCell()).toString();
	}
}
