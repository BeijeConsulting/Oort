package it.beije.oort.kirolosmater.biblioteca;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prestito")
public class Prestito {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idprestito")
	private int id;	
	
	@Column
	private String libro;
	
	@Column
	private String utente;
	
	@Column
	private LocalDate data_inizio;
	
	@Column
	private LocalDate data_fine;
	
	@Column
	private String note;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLibro() {
		return libro;
	}
	public void setLibro(String libro) {
		this.libro = libro;
	}
	public String getUtente() {
		return utente;
	}
	public void setUtente(String utente) {
		this.utente = utente;
	}
	public LocalDate getData_inizio() {
		return data_inizio;
	}
	public void setData_inizio(LocalDate data_inizio) {
		this.data_inizio = data_inizio;
	}
	public LocalDate getData_fine() {
		return data_fine;
	}
	public void setData_fine(LocalDate data_fine) {
		this.data_fine = data_fine;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
