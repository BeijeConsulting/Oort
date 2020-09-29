package it.beije.oort.gregori.biblioteca;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "editori")
public class Editore {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "denominazione")
	private String denominazione;
	
	@Column(name = "descrizione")
	private String descrizione;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		StringBuilder builder = new StringBuilder("editore [");
		builder.append("id: ").append(this.id)
			.append(" - denominazione: ").append(this.denominazione)
			.append(" - descrizione: ").append(this.descrizione).append("]");
		
		return builder.toString();
	}

}
