package it.beije.oort.gregori.biblioteca;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "utenti")
public class Utente {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "cognome")
	private String cognome;
	
	@Column(name = "codice_fiscale")
	private String codiceFiscale;
	
	@Column(name = "indirizzo")
	private String indirizzo;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "telefono")
	private String telefono;

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

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder("utente [");
		builder.append("id: ").append(this.id)
			.append(" - nome: ").append(this.nome)
			.append(" - cognome: ").append(this.cognome)
			.append(" - codice fiscale: ").append(this.codiceFiscale)
			.append(" - indirizzo: ").append(this.indirizzo)
			.append(" - email: ").append(this.email)
			.append(" - telefono: ").append(this.telefono).append("]");
		
		return builder.toString();
	}
}
