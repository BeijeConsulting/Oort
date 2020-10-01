package it.beije.oort.bru.db.biblioteca;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "libri")
public class Libro {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column
	private String titolo;
	@Column
	private String descrizione;
	@Column
	private int id_autore;
	@Column
	private int id_editore;
	@Column
	private int anno;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public int getId_autore() {
		return id_autore;
	}
	public void setId_autore(int id_autore) {
		this.id_autore = id_autore;
	}
	public int getId_editore() {
		return id_editore;
	}
	public void setId_editore(int id_editore) {
		this.id_editore = id_editore;
	}
	public int getAnno() {
		return anno;
	}
	public void setAnno(int anno) {
		this.anno = anno;
	}
	@Override
	public String toString() {
		StringBuilder line = new StringBuilder();
		line.append(this.id).append(";").append(this.titolo).append(";").append(this.descrizione).append(";").append(this.anno);
		return line.toString();
	}
}
