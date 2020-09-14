package it.beije.oort.sba.rubrica;

import java.util.Random;

public class EmailGenerator {
	public String formatEmail(String a, String b, ListRandomSelector suffix) {
		Random r = new Random();
		int n1 = r.nextInt(5) + 1;
		int n2 = r.nextInt(3) + 1;
		String ret = "";
		boolean nome = true, cognome = true;
		if(n1==1) nome = false;
		if(n2==1) cognome = false;
		if(!nome && !cognome) { //mancano sia nome che cognome
			for(int i = 0; i<r.nextInt(14)+6; i++) {
				ret+=(char)(r.nextInt(123-97)+97);
			}
		}else if(!nome){ //manca solo il nome
			ret += b + genNum();
		}else if(!cognome) { //manca solo il cognome
			ret += a + genNum();
		}else { //ci sono sia nome che cognome
			ret += a + "." + b;
		}
		ret += "@" + suffix.getNext();
		return ret;
	}
	private static String genNum() {
		String genStr = "";
		Random r = new Random();
		if ((r.nextInt(10)+1) != 1) {
			int num = r.nextInt(101);
			if (num < 10) {
				genStr += "0";
			}
			genStr += num;
		}
		return genStr;
	}
}
