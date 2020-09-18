package it.beije.oort.team2;


import java.util.ArrayList;
import java.util.Random;

public class Record {

	private String nome;
	private String cognome;
	private String telefono;
	private String mail;
	public static final String[] PREFISSI = {"345", "346", "347", "348", "349"};
	public static final String[] DOMINI = {"gmail.com", "hotmail.com", "hotmail.it", 
											"libero.it", "yahoo.com", "virgilio.it", "tim.it", "alice.it"};
	public static final int MIN = 0000000;
	public static final int MAX = 9999999;
	
	public static ArrayList<String> telefon = new ArrayList<>();
	public static ArrayList<String> email = new ArrayList<>();
	
	public Record() {}
	
	public String getNome() {
		return nome;
	}
	
	public String getCognome() {
		return cognome;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getMail() {
		return mail;
	}
	
	public void generateTelefono() {           
		Random random = new Random();
		int ctel = 1 + random.nextInt(8); 
		if(ctel == 1)
			this.telefono = " ";
		else {
			if((ctel == 2) && (email.size() > 0))
				this.telefono = telefon.get(random.nextInt(telefon.size()));
			else {
				if(ctel == 3 || ctel == 4){
					int value = random.nextInt(MAX - MIN + 1) + MIN;
					this.telefono = "+39" + PREFISSI[random.nextInt(PREFISSI.length)] + value;
				}else {
				if (ctel == 5 || ctel == 6 || ctel == 7 || ctel == 8) {
					int value = random.nextInt(MAX - MIN + 1) + MIN;
					this.telefono = PREFISSI[random.nextInt(PREFISSI.length)] + value;
				}else {
					int value2 = random.nextInt(MAX - MIN + 1) + MIN;
					this.telefono = PREFISSI[random.nextInt(PREFISSI.length)] + value2;
				}
				}
			}
		}
		telefon.add(this.telefono);
	}
	
	
	public void generateMail() {
		Random random = new Random();
		String sep;
		int cmail = 1 + random.nextInt(10);
		if (cmail == 1 || cmail == 2) 
			this.mail = " ";
		else {
			if((cmail == 3 || cmail == 4 || cmail == 5 ) && (email.size() > 0)) 
				this.mail = email.get(random.nextInt(email.size()));
			else {				
				if(cmail == 6 || cmail == 7 || cmail == 8 || cmail == 9 || cmail == 10) {
						int csep = 1 + random.nextInt(10);
						int rnome = 1 + random.nextInt(5);
						int rcognome = 1 + random.nextInt(3);
						if(csep == 3 || csep == 4 || csep == 5 || csep == 6 || csep == 7) 
							sep = ".";
						else {
							if(csep == 8 || csep == 9 || csep == 10)
								sep ="-";
						else {
							sep = "";
					if(rnome == 1 && rcognome == 1) {
						int c2 = 6 + random.nextInt(20);
						StringBuilder casual = new StringBuilder();
						for (int i = 0; i <= c2 ; i++) {
							char  c1 = (char) (97 + random.nextInt(123));
							casual.append(c1);
						}
						this.mail = casual + "@" + DOMINI[random.nextInt(DOMINI.length)];
					}else {
						if(rnome == 1) {
							int p1 = 1 + random.nextInt(10);
							if(p1 != 1) {
							int p2 = random.nextInt(100);
							if (p2 < 10)
								this.mail =  sep + this.getCognome() + "0" + p2 + "@" + DOMINI[random.nextInt(DOMINI.length)];
							else
								this.mail =  sep + this.getCognome() + p2 + "@" + DOMINI[random.nextInt(DOMINI.length)];
						}
					}
					else {
						if (rcognome == 1) {
							int pm1 = 1 + random.nextInt(10);
								if(pm1 != 1) {
									int pm2 = random.nextInt(100);
									if (pm2 < 10)
										this.mail = this.getNome() + sep + "0" + pm2 + "@" + DOMINI[random.nextInt(DOMINI.length)];
									else
										this.mail = this.getNome() + sep + pm2 + "@" + DOMINI[random.nextInt(DOMINI.length)];
								}
						}
						else {
							int x1 = 1 + random.nextInt(4);
							int x2 = 1 + random.nextInt(10);
							if(x1 == 1)
								this.mail = this.getCognome() + sep + this.getNome() + "@" + DOMINI[random.nextInt(DOMINI.length)];
							if(x2 == 1 || x2 == 2)
								this.mail += this.nome.charAt(0) + sep + this.getCognome() + "@" + DOMINI[random.nextInt(DOMINI.length)];
							if(x2 == 3 || x2 == 4 || x2 == 5) {
								for(int j=1 ; j<nome.length(); j++) {
									if(nome.charAt(j) == 'a' || nome.charAt(j) == 'e' || nome.charAt(j) == 'i' || nome.charAt(j) == 'o' || nome.charAt(j) =='u') {
										this.mail += this.nome.substring(0, j) + sep + this.getCognome() + "@" + DOMINI[random.nextInt(DOMINI.length)]; 
										break;
									}
								}
							}
						}
					}	
					}
						}
						}
			}
		}
		if(this.mail == null)
			this.mail = this.getNome() + "." + this.getCognome() + "@" + DOMINI[random.nextInt(DOMINI.length)];
		}
		email.add(this.mail);
	}
		
	

		public void generateNome(ArrayList<String> nomi) {
		Random random = new Random();
		int cnome  = 1 + random.nextInt(5);
		if(cnome == 1)
			this.nome = " ";
		else
			this.nome = nomi.get(random.nextInt(nomi.size())).trim();
	}
	
	public void generateCognome(ArrayList<String> cognomi) {
		Random random = new Random();
		int csurn  = 1 + random.nextInt(3);
		if(csurn == 1)
			this.cognome = " ";
		else			
			this.cognome = cognomi.get(random.nextInt(cognomi.size())).trim();
	}
}

	