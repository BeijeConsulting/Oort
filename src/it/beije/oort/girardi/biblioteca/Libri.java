package it.beije.oort.girardi.biblioteca;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "libri")
public class Libri {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "titolo")
	private String titolo;
	
	@Column(name = "descrizione")
	private String descrizione;
	
	@Column(name = "autore")
	private String autore;
	
	@Column(name = "editore")
	private String editore;
	
	@Column(name = "anno")
	private LocalDate anno;
	
	
	public Libri() {}
	
	public Libri(String titolo, String descrizione, String autore, 
								String editore, LocalDate anno) {
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.autore = autore;
		this.editore = editore;
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
	
	
	public String getAutore() {
		return autore;
	}
	public void setAutore(String autore) {
		this.autore = autore;
	}
	
	
	public String getEditore() {
		return editore;
	}
	public void setId_editore(String editore) {
		this.editore = editore;
	}
	
	
	public LocalDate getAnno() {
		return anno;
	}
	public void setAnno(LocalDate anno) {
		this.anno = anno;
	}
}
