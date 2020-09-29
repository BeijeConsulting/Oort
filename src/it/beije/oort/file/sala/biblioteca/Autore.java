package it.beije.oort.file.sala.biblioteca;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "autori")
public class Autore implements Databasable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_autore")
	private Integer id_autore;
	@Column
	private String nome;
	@Column
	private String cognome;
	@Column
	private String biografia;
	@Column
	private LocalDate data_nascita;
	@Column
	private LocalDate data_morte;
	
	public Autore() {
		this(null, "", "", "", null, null);
	}
	
	public Autore(Integer id_autore, String nome, String cognome, String biografia, LocalDate data_nascita,
			LocalDate data_morte) {
		super();
		this.id_autore = id_autore;
		this.nome = nome;
		this.cognome = cognome;
		this.biografia = biografia;
		this.data_nascita = data_nascita;
		this.data_morte = data_morte;
	}
	
	public Integer getId_autore() {
		return id_autore;
	}
	public void setId_autore(Integer id_autore) {
		this.id_autore = id_autore;
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
	public String getBiografia() {
		return biografia;
	}
	public void setBiografia(String biografia) {
		this.biografia = biografia;
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
	
	public String toString() {
		return this.nome+" "+this.cognome;
	}
}
