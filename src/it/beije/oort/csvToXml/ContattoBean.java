package it.beije.oort.csvToXml;

public class ContattoBean {
	private String nome;
	private String cognome;
	private String cell;
	private String email;
	

	
	// Per ora non necessito i Costruttori
//	public ContattoBean(String nome) {
//		this(nome, "", "", "");
//	}
//	
//	public ContattoBean(String nome, String cognome) {
//		this(nome, cognome, "", "");
//	}
//	
//	public ContattoBean(String nome, String cognome, String prefisso) {
//		this(nome, cognome, prefisso, "");
//	}
//	
//	public ContattoBean(String nome, String cognome, String prefisso, String dominio) {
//		Random r = new Random();
//		StringBuilder suffisso=new StringBuilder();
//		for(int i = 0; i < 7; i++) {
//			suffisso.append(r.nextInt(10));
//		}
//		
//		this.nome = nome;
//		this.cognome = cognome;
//		this.cell = prefisso + suffisso.toString();
//		this.email = GeneraMail.generaMail(nome, cognome, dominio);
//	}
	
	public String getNome() {
		return nome;
	}
	public String getCognome() {
		return cognome;
	}
	public String getCell() {
		return cell;
	}
	public String getEmail() {
		return email;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public void setCell(String cell) {
		this.cell = cell;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the formatted string following the csv convention with quotes.
	 */
	public String toFormattedString() {
		return new StringBuilder("\"")
				.append(nome)
				.append("\";\"")
				.append(cognome)
				.append("\";\"").append(cell).append("\";\"").append(email).append("\"").toString();
	}

	public String toString() {
		return new StringBuilder("Nome: " + this.getNome()
		+ ". Cognome: " + this.getCognome()
		+ ". Email: " + this.getEmail()
		+ ". Telefono: " + this.getCell())
		.toString();
	}
}
