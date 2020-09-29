package it.beije.oort.kirolosmater.biblioteca;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "autore")
public class Autore {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idautore")
	private int id;
	
	@Column
	private String cognome;
	
	@Column
	private String nome;
	
	@Column
	private LocalDate data_nascita;
	
	@Column
	private LocalDate data_morte;
	
	@Column
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
	public LocalDate getData_nascita() {
		return data_nascita;
	}
	public void setData_nascita(LocalDate data_nascita) {
		this.data_nascita = data_nascita;
	}
	public LocalDate getData_morte() {
		return data_morte;
	}
	public void setData_morte(LocalDate data_morte) {
		this.data_morte = data_morte;
	}
	public String getBiografia() {
		return biografia;
	}
	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
}
