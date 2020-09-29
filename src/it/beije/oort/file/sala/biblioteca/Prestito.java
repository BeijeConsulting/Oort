package it.beije.oort.file.sala.biblioteca;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prestiti")
public class Prestito implements Databasable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_prestito")
	private Integer id_prestito;
	@Column
	private Integer id_libro;
	@Column
	private Integer id_utente;
	@Column
	private LocalDate data_inizio;
	@Column
	private LocalDate data_fine;
	@Column
	private String note;
	
	public Prestito() {
		this(null, null, null, null, null, "");
	}
	
	public Prestito(Integer id_prestito, Integer id_libro, Integer id_utente,
			LocalDate data_inizio, LocalDate data_fine, String note) {
		super();
		this.id_prestito = id_prestito;
		this.id_libro = id_libro;
		this.id_utente = id_utente;
		this.data_inizio = data_inizio;
		this.data_fine = data_fine;
		this.note = note;
	}

	public Integer getId_prestito() {
		return id_prestito;
	}

	public void setId_prestito(Integer id_prestito) {
		this.id_prestito = id_prestito;
	}

	public Integer getId_libro() {
		return id_libro;
	}

	public void setId_libro(Integer id_libro) {
		this.id_libro = id_libro;
	}

	public Integer getId_utente() {
		return id_utente;
	}

	public void setId_utente(Integer id_utente) {
		this.id_utente = id_utente;
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

	public String toString() {
		return "Prestito [id_libro=" + id_libro + ", id_utente=" + id_utente + ", data_inizio=" + data_inizio
				+ ", data_fine=" + data_fine + "]";
	}
}
