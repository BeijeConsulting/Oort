package it.beije.oort.madonia.biblioteca.ebeans;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "cognome")
	private String cognome;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "codice_fiscale")
	private String codiceFiscale;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "telefono")
	private String telefono;
	
	@Column(name = "indirizzo")
	private String indirizzo;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
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

	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder().append("Utente [");

		sb.append("cognome: ");
		if(cognome == null) {
			sb.append(cognome);
		} else {
			sb.append("\"").append(cognome).append("\"");
		}

		sb.append(" - ").append("nome: ");
		if(nome == null) {
			sb.append(nome);
		} else {
			sb.append("\"").append(nome).append("\"");
		}

		sb.append(" - ").append("codice_fiscale: ");
		if(codiceFiscale == null) {
			sb.append(codiceFiscale);
		} else {
			sb.append("\"").append(codiceFiscale).append("\"");
		}

		sb.append(" - ").append("email: ");
		if(email == null) {
			sb.append(email);
		} else {
			sb.append("\"").append(email).append("\"");
		}

		sb.append(" - ").append("telefono: ");
		if(telefono == null) {
			sb.append(telefono);
		} else {
			sb.append("\"").append(telefono).append("\"");
		}

		sb.append(" - ").append("indirizzo: ");
		if(indirizzo == null) {
			sb.append(indirizzo);
		} else {
			sb.append("\"").append(indirizzo).append("\"");
		}

		sb.append("]");

		return sb.toString();
	}
}