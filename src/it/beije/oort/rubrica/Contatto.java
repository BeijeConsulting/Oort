package it.beije.oort.rubrica;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contatti")
public class Contatto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="nome")
	private String nome;
	
	@Column
	private String cognome;
	
	@Column
	private String telefono;
	
	@Column
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		StringBuilder builder = new StringBuilder("[");
		builder.append("ID : ").append(this.id).append(" - Nome : ").append(this.nome)
		.append(" - Cognome : ").append(this.cognome)
		.append(" - Telefono : ").append(this.telefono)
		.append(" - Email : ").append(this.email).append("]");
		
		return builder.toString();
	}

	public String toFormattString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.id)
		.append(";").append(this.nome)
		.append(";").append(this.cognome)
		.append(";").append(this.telefono)
		.append(";").append(this.email);
		
		return builder.toString();
	}
	public boolean equals(Contatto c) {
		return(this.nome.equals(c.getNome()) && this.cognome.equals(c.getCognome()) &&
			this.telefono.equals(c.getTelefono()) && this.email.equals(c.getEmail()));
	}


}