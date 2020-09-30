package it.beije.oort.lauria.biblioteca;

import java.time.LocalDate;

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
	@Column(name = "id")
	private int id;
	
	@Column(name = "titolo")
	private String titolo;
	
	@Column(name = "descrizione")
	private String descrizione;
	
	@Column(name = "id_autore")
	private int id_autore;
	
	@Column(name = "id_editore")
	private int id_editore;
	
	@Column(name = "anno")
	private String anno;
	
	
	public Libro() {}
	
	public Libro(int id, String titolo, String descrizione, int id_autore, int id_editore, String anno) {
		this.id = id;
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.id_autore = id_autore;
		this.id_editore = id_editore;
		this.anno = anno;
	}


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
	
	
	public String getAnno() {
		return anno;
	}
	public void setAnno(String anno) {
		this.anno = anno;
	}
	
	
}
