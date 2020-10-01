package it.beije.oort.madonia.biblioteca.ebeans;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "titolo")
	private String titolo;
	
	@Column(name = "descrizione")
	private String descrizione;
	
	@Column(name = "autore")
	private int idAutore;
	
	@Column(name = "editore")
	private int idEditore;
	
	@Column(name = "anno")
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
	
	public int getIdAutore() {
		return idAutore;
	}
	public void setIdAutore(int idAutore) {
		this.idAutore = idAutore;
	}
	
	public int getIdEditore() {
		return idEditore;
	}
	public void setIdEditore(int idEditore) {
		this.idEditore = idEditore;
	}
	
	public int getAnno() {
		return anno;
	}
	public void setAnno(int anno) {
		this.anno = anno;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder().append("Libro [");

		sb.append("titolo: ");
		if(titolo == null) {
			sb.append(titolo);
		} else {
			sb.append("\"").append(titolo).append("\"");
		}

		sb.append(" - ").append("descrizione: ");
		if(descrizione == null) {
			sb.append(descrizione);
		} else {
			sb.append("\"").append(descrizione).append("\"");
		}

		sb.append(" - ").append("autore: ");
		if(idAutore == 0) {
			sb.append("null");
		} else {
			sb.append("\"").append(idAutore).append("\"");
		}

		sb.append(" - ").append("editore: ");
		if(idEditore == 0) {
			sb.append("null");
		} else {
			sb.append("\"").append(idEditore).append("\"");
		}

		sb.append(" - ").append("anno: ");
		if(anno == 0) {
			sb.append("null");
		} else {
			sb.append("\"").append(anno).append("\"");
		}

		sb.append("]");

		return sb.toString();
	}
}
