package it.beije.oort.bru.db.biblioteca;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "autori")
public class Autore {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column
	private String cognome;
	@Column
	private String nome;
	@Column
	private String data_nascita;
	@Column
	private String data_morte;
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
	public String getData_nascita() {
		return data_nascita;
	}
	public void setData_nascita(String data_nascita) {
		this.data_nascita = data_nascita;
	}
	public String getData_morte() {
		return data_morte;
	}
	public void setData_morte(String data_morte) {
		this.data_morte = data_morte;
	}
	public String getBiografia() {
		return biografia;
	}
	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	@Override
	public String toString() {
		StringBuilder line = new StringBuilder();
		line.append(this.id).append(";").append(this.nome).append(";").append(this.cognome).append(";").append(this.data_nascita).append(";").append(this.data_morte).append(";").append(this.biografia).append(";");
		return line.toString();
	}
}
