package it.beije.oort.bassanelli.exercises.phonebookgenerator;

public class Contact {
	
	private String name;
	private String surname;
	private String mobile;
	private String email;
	
	public Contact() {}
	
	public Contact(String name, String surname, String mobile) {
		this(name, surname, mobile, "");
	}
	
	public Contact(String name, String cognome, String mobile, String email) {
		this.name = name;
		this.surname = cognome;
		this.mobile = mobile;
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
		
	public String toString() {
		StringBuilder builder = new StringBuilder("CONTACT [");
		builder.append("NAME : ").append(this.name)
			.append(" - SURNAME : ").append(this.surname)
			.append(" - MOBILE : ").append(this.mobile)
			.append(" - EMAIL : ").append(this.email).append("]");
		
		return builder.toString();
	}
	
	public String toCsvRow() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.mobile).append(";")
			.append(this.email).append(";")
			.append(this.name).append(";")
			.append(this.surname).append("\n");
		
		return builder.toString();
	}
	
}
