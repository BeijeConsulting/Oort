package it.beije.oort.team2;


import java.util.ArrayList;
import java.util.Random;

public class Record {

	private String nome;
	private String cognome;
	private String telefono;
	private String mail = "#####";
	public static final String[] PREFISSI = {"345", "346", "347", "348", "349"};
	public static final String[] DOMINI = {"gmail.com", "hotmail.com", "hotmail.it", 
											"libero.it", "yahoo.com", "virgilio.it", "tim.it", "alice.it"};
	public static final int MIN = 0000000;
	public static final int MAX = 9999999;
	
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
		int value = random.nextInt(MAX - MIN + 1) + MIN;
		this.telefono = PREFISSI[random.nextInt(PREFISSI.length)] + value;
	}
	
	public void generateMail() {
		Random random = new Random();
		String sep;
		int csep = 1 + random.nextInt(10);
	    int rnome = 1 + random.nextInt(5);
		int rcognome = 1 + random.nextInt(3);
		if(csep == 3 || csep == 4 || csep == 5 || csep == 6 || csep == 7)
			sep = ".";
		else
			if(csep == 8 || csep == 9 || csep == 10)
				sep ="-";
			else
				sep = "";
		if(rnome == 1 && rcognome == 1) {
			int c2 = 6 + random.nextInt(20);
			String casual  = "";
			   for (int i = 0; i <= c2 ; i++) {
				   	char  c1 = (char) (1 + random.nextInt(100));
			        casual += c1;
		       }
			   this.mail = casual + "@" + DOMINI[random.nextInt(DOMINI.length)];
		}
			   		
		
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
								this.mail = this.getNome() + sep + this.getCognome() + "@" + DOMINI[random.nextInt(DOMINI.length)];
						}
				}	
	}
		public void generateNome(ArrayList<String> nomi) {
		Random random = new Random();
		this.nome = nomi.get(random.nextInt(nomi.size())).trim();
	}
	
	public void generateCognome(ArrayList<String> cognomi) {
		Random random = new Random();
		this.cognome = cognomi.get(random.nextInt(cognomi.size())).trim();
	}

}
