package it.beije.oort.madonia.biblioteca.ebeans;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "autori")
public class Autore extends MyEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "cognome")
	private String cognome;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "data_nascita")
	private LocalDate dataNascita;
	
	@Column(name = "data_morte")
	private LocalDate dataMorte;
	
	@Column(name = "biografia")
	private String biografia;
	
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
	
	public LocalDate getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}
	
	public LocalDate getDataMorte() {
		return dataMorte;
	}
	public void setDataMorte(LocalDate dataMorte) {
		this.dataMorte = dataMorte;
	}
	
	public String getBiografia() {
		return biografia;
	}
	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder().append("Autore [");
		
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
		
		sb.append(" - ").append("data_nascita: ");
		if(dataNascita == null) {
			sb.append(dataNascita);
		} else {
			sb.append("\"").append(dataNascita).append("\"");
		}
		
		sb.append(" - ").append("data_morte: ");
		if(dataMorte == null) {
			sb.append(dataMorte);
		} else {
			sb.append("\"").append(dataMorte).append("\"");
		}
		
		sb.append(" - ").append("biografia: ");
		if(biografia == null) {
			sb.append(biografia);
		} else {
			sb.append("\"").append(biografia).append("\"");
		}
		
		sb.append("]");
		
		return sb.toString();
	}
}
