package it.beije.oort.file.sala.biblioteca;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "editore")
public class Editore implements Databasable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_editore")
	private Integer id_editore;
	@Column
	private String denominazione;
	@Column
	private String descrizione;
	
	public Editore() {
		this(null, "", "");
	}
	
	public Editore(Integer id_editore, String denominazione, String descrizione) {
		super();
		this.id_editore = id_editore;
		this.denominazione = denominazione;
		this.descrizione = descrizione;
	}
	public Integer getId_editore() {
		return id_editore;
	}
	public void setId_editore(Integer id_editore) {
		this.id_editore = id_editore;
	}
	public String getDenominazione() {
		return denominazione;
	}
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String toString() {
		return this.denominazione;
	}
	
}
