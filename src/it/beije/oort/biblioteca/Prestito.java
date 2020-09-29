package it.beije.oort.biblioteca;

public class Prestito {
	private int id;
	private Utente utente;
	private String data_inzio;
	private String data_fine;
	private String note;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	public String getData_inzio() {
		return data_inzio;
	}
	public void setData_inzio(String data_inzio) {
		this.data_inzio = data_inzio;
	}
	public String getData_fine() {
		return data_fine;
	}
	public void setData_fine(String data_fine) {
		this.data_fine = data_fine;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
