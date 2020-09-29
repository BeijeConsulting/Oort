package it.beije.oort.madonia.rubrica;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="rubrica")
public class Contatto implements Comparable<Contatto>, Cloneable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="cognome")
	private String cognome;
	
	@Column(name="telefono")
	private String telefono;
	
	@Column(name="email")
	private String email;
	
	// Si può creare anche una classe di supporto
	@Transient
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

	public String getAlias() {
		return alias.toString();
	}
	public void addAlias(String alias) {
		this.alias.add(alias);
	}
	
	public boolean isEmpty() {
		return !(nome != null && nome.length() > 0
				|| cognome != null && cognome.length() > 0
				|| telefono != null && telefono.length() > 0
				|| email != null && email.length() > 0);
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
	
	public Contatto clone() {
		Contatto contatto = new Contatto();
		contatto.setId(this.getId());
		contatto.setNome(this.getNome());
		contatto.setCognome(this.getCognome());
		contatto.setTelefono(this.getTelefono());
		contatto.setEmail(this.getEmail());
		
		return contatto;
	}

}