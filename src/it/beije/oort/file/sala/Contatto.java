package it.beije.oort.file.sala;

public class Contatto {
	
	private String nome;
	private String cognome;
	private String telefono;
	private String email;
	private String id_db;
	
	public Contatto() {}
	
	public Contatto(String nome, String cognome, String telefono) {
		this(nome, cognome, telefono, "");
	}
	
	public Contatto(String nome, String cognome, String telefono, String email) {
		this.nome = nome;
		this.cognome = cognome;
		this.telefono = telefono;
		this.email = email;
		this.id_db="";
	}
	public Contatto(String nome, String cognome, String telefono, String email, String id_db) {
		this.nome = nome;
		this.cognome = cognome;
		this.telefono = telefono;
		this.email = email;
		this.id_db=id_db;
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
	
	public String toString() {
		StringBuilder builder = new StringBuilder("contatto [");
		builder.append("nome : ").append(this.nome)
			.append(" - cognome : ").append(this.cognome)
			.append(" - telefono : ").append(this.telefono)
			.append(" - email : ").append(this.email).append("]");
		
		return builder.toString();
	}
	public String toXml() {
		return new StringBuilder("<contatto>")
				.append("<nome>").append(this.nome).append("</nome>")
				.append("<cognome>").append(this.cognome).append("</cognome>")
				.append("<telefono>").append(this.telefono).append("</telefono>")
				.append("<email>").append(this.email).append("</email>")
				.append("</contatto>").toString();
	}
	
	public String toCsvDoubleQuote() {
		return new StringBuilder("\"")
				.append(this.cognome).append("\";\"")
				.append(this.nome).append("\";\"")
				.append(this.email).append("\";\"")
				.append(this.telefono).append("\"")
				.append("\n").toString();
	}
	
	public String toCsvSimple() {
		return new StringBuilder("")
				.append(this.cognome).append(";")
				.append(this.nome).append(";")
				.append(this.email).append(";")
				.append(this.telefono)
				.append("\n").toString();
	}
	
	public String toCsvSimpleNoNome() {
		return new StringBuilder("")
				.append(this.cognome).append(";")
				.append(";")
				.append(this.email).append(";")
				.append(this.telefono)
				.append("\n").toString();
	}
	
	public String toCsvSimpleNoCognome() {
		return new StringBuilder("")
				.append(";")
				.append(this.nome).append(";")
				.append(this.email).append(";")
				.append(this.telefono)
				.append("\n").toString();
	}
	public String toStringFromDatabase() {
		return new StringBuilder("contatto [")
				.append("id: ").append(this.id_db)
				.append(" | nome : ").append(this.nome)
				.append(" | cognome : ").append(this.cognome)
				.append(" | telefono : ").append(this.telefono)
				.append(" | email : ").append(this.email)
				.append("]").toString();
	}
}
