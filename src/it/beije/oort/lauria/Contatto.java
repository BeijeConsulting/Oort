package it.beije.oort.lauria;


public class Contatto {
	
	private int id;
	private String nome;
	private String cognome;
	private String telefono;
	private String email;
	private StringBuilder duplicatiEmail = new StringBuilder();
	
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
	public Contatto(String nome, String cognome, String telefono, String email, String duplicatiEmail) {
		this(nome, cognome, telefono, email);
		this.duplicatiEmail.append(duplicatiEmail);
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
	
	public StringBuilder getDuplicatiEmail() {
		return duplicatiEmail;
	}
	public void setDuplicatiEmail(String duplicatiEmail) {
		this.duplicatiEmail.append(duplicatiEmail);
	}
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder("contatto [");
		builder.append("nome : ").append(this.nome)
			.append(" - cognome : ").append(this.cognome)
			.append(" - telefono : ").append(this.telefono)
			.append(" - email : ").append(this.email).append("] [")
			.append(this.duplicatiEmail.toString()).append("]");
		
		return builder.toString();
	}
	
	public boolean equalsTo(Contatto c2) {
		if(this.nome.equalsIgnoreCase(c2.getNome()) && this.cognome.equalsIgnoreCase(c2.getCognome())&&
				this.telefono.equalsIgnoreCase(c2.getTelefono()) && this.email.equalsIgnoreCase(c2.getEmail())) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean matching(Contatto c2) {
		if(this.equalsTo(c2)) {
			return true;
		}else if(this.email.equalsIgnoreCase(c2.getEmail()) || this.email.equalsIgnoreCase("") || c2.getEmail().equalsIgnoreCase("")){
			if(this.telefono.equalsIgnoreCase(c2.getTelefono()) || this.telefono.equalsIgnoreCase("") || c2.getTelefono().equalsIgnoreCase("")) {
				if(this.cognome.equalsIgnoreCase(c2.getCognome()) || this.cognome.equalsIgnoreCase("") || c2.getCognome().equalsIgnoreCase("")) {
					if(this.nome.equalsIgnoreCase(c2.getNome()) || this.nome.equalsIgnoreCase("") || c2.getNome().equalsIgnoreCase("")) {
						//System.out.println("eccolo");
						return true;
					}
				}
			}
			
		}else { 
			return false;
		}
		return false;

	}
	
}
