package it.beije.oort.file.sala;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rubrica")
public class Contatto {
	
	@Column
	private String nome;
	@Column
	private String cognome;
	@Column
	private String telefono;
	@Column
	private String email;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_rubrica")
	private int id;
	
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
	public Contatto(String nome, String cognome, String telefono, String email, int id) {
		this.nome = nome;
		this.cognome = cognome;
		this.telefono = telefono;
		this.email = email;
		this.id=id;
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
				.append("id: ").append(this.id)
				.append(" | nome : ").append(this.nome)
				.append(" | cognome : ").append(this.cognome)
				.append(" | telefono : ").append(this.telefono)
				.append(" | email : ").append(this.email)
				.append("]").toString();
	}
}
