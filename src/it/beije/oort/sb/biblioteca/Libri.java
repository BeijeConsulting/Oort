package it.beije.oort.sb.biblioteca;


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
	@Column(name="id")
	private int id;

	@Column
	private String titolo;
	
	@Column
	private String descrizione;
	
	@Column
	private int autore;
	
	@Column
	private int editore;
	
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

	public int getAutore() {
		return autore;
	}

	public void setAutore(int autore) {
		this.autore = autore;
	}

	public int getEditore() {
		return editore;
	}

	public void setEditore(int editore) {
		this.editore = editore;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}
}
