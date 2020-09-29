package it.beije.oort.file.sala.biblioteca;

import java.time.Year;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "libri")
public class Libro implements Databasable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_libro")
	private Integer id_libro;
	@Column
	private Integer id_autore;
	@Column
	private Integer id_editore;
	@Column
	private String titolo;
	@Column
	private String descrizione;
	@Column
	private Year anno;
	
	public Libro() {
		this(null, null, null, "", "", null);
	}
	
	public Libro(Integer id_libro, String titolo) {
		this(id_libro, null, null, titolo, "", null);
	}
	
	public Libro(Integer id_libro, Integer id_autore,Integer id_editore,
			String titolo, String descrizione, Year anno) {
		this.id_libro=id_libro;
		this.id_autore=id_autore;
		this.id_editore=id_editore;
		this.titolo=titolo;
		this.descrizione=descrizione;
		this.anno=anno;
	}
	
	public Integer getId_libro() {
		return id_libro;
	}
	public void setId_libro(Integer id_libro) {
		this.id_libro = id_libro;
	}
	public Integer getId_autore() {
		return id_autore;
	}
	public void setId_autore(Integer id_autore) {
		this.id_autore = id_autore;
	}
	public Integer getId_editore() {
		return id_editore;
	}
	public void setId_editore(Integer id_editore) {
		this.id_editore = id_editore;
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
	public Year getAnno() {
		return anno;
	}
	public void setAnno(Year anno) {
		this.anno = anno;
	}
	
	public String toString() {
		return new StringBuilder("Titolo: ").append(this.titolo)
				.append(" - Autore: ").append(this.id_autore)
				.append(" - Anno: ").append(this.anno.toString()).toString();
	}
}
