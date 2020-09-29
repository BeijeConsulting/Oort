package it.beije.oort.gregori.biblioteca;

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
	private int autore;
	
	@Column(name = "id_editore")
	private int editore;
	
	@Column(name = "anno")
	private String anno;

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

	public String getAnno() {
		return anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder("libro [");
		builder.append("id: ").append(this.id)
			.append(" - titolo: ").append(this.titolo)
			.append(" - descrizione: ").append(this.descrizione)
			.append(" - autore: ").append(this.autore)
			.append(" - editore: ").append(this.editore)
			.append(" - anno: ").append(this.anno).append("]");
		
		return builder.toString();
	}

}
