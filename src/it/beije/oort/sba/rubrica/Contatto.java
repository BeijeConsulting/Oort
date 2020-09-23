package it.beije.oort.sba.rubrica;

public class Contatto {
	
	private String nome = "";
	private String cognome = "";
	private String telefono = "";
	private String email = "";
	
	public Contatto() {}
	
	public Contatto(String nome, String cognome, String telefono) {
		this(nome, cognome, telefono, "");
	}
	
	public Contatto(String nome, String cognome, String telefono, String email) {
		this.nome = nome;
		this.cognome = cognome;
		this.telefono = telefono;
		this.email = email;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.nome).append(';')
			.append(this.cognome).append(';')
			.append(this.telefono).append(';')
			.append(this.email).append(';');
		
		return builder.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		Contatto c = (Contatto)o;
		return (this.getNome().equalsIgnoreCase(c.getNome())&&
				this.getCognome().equalsIgnoreCase(c.getCognome())&&
				this.getTelefono().equals(c.getTelefono())&&
				this.getEmail().equals(c.getEmail()));		
	}
	
}
