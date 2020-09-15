package it.beije.oort.sba.rubrica;

import java.util.Random;

public class EmailGenerator {
	
	
	public String formatEmail(String a, String b, ListRandomSelector suffix) {
		Random r = new Random();
		int n1 = r.nextInt(5) + 1;
		int n2 = r.nextInt(3) + 1;
		StringBuilder ret = new StringBuilder();
		boolean nome = true, cognome = true;
		if(n1==1) nome = false;
		if(n2==1) cognome = false;
		if(!nome && !cognome) { //mancano sia nome che cognome
			for(int i = 0; i<r.nextInt(14)+6; i++) {
				ret.append((char)(r.nextInt(123-97)+97));
			}
		}else if(!nome){ //manca solo il nome
			ret.append(b).append(genNum());
		}else if(!cognome) { //manca solo il cognome
			ret.append(a).append(genNum());
		}else {	//ci sono sia nome che cognome
			String separatore="";
			int n3 = r.nextInt(10)+1;
			if(n3>3 && n3<=7) separatore = ".";
			else if(n3>=8) separatore = "-";
			int n4 = r.nextInt(10)+1;
			if(n4<=2) {
				a = a.substring(0,1);
			} else if(n4<=5) {
				a=a.substring(0,primaVoc(a));
			}			
			if(r.nextInt(4)+1==1) {
				ret.append(b).append(separatore).append(a);
			} else { 
				ret.append(a).append(separatore).append(b);				
			}
			ret = replaceVowels(ret);
		}	
		ret.append("@").append(suffix.getNext());
		return ret.toString();
	}
	private static StringBuilder replaceVowels(StringBuilder sb) {
		for(int i =0; i < sb.length(); i++) {
			switch(sb.charAt(i)) {
			case 'a':
				sb.replace(i, i+1, "4");
				break;
			case 'e':
				sb.replace(i, i+1, "3");
				break;
			case 'i':
				sb.replace(i, i+1, "1");
				break;
			case 'o':
				sb.replace(i, i+1, "0");
				break;
			default:
			}
		}
		return sb;
	}
	private static String genNum() {
		StringBuilder genStr = new StringBuilder();
		Random r = new Random();
		if ((r.nextInt(10)+1) != 1) {
			int num = r.nextInt(101);
			if (num < 10) {
				genStr.append("0");
			}
			genStr.append(num);
		}
		return genStr.toString();
	}

	private static boolean isVowel(char a) {
		String vocali = "aeiou";
		return vocali.contains(a + "");
	}
	
	private static int primaVoc(String a) {
		int ind;
		for(ind = 1; ind < a.length(); ind++) {
			if(isVowel(a.charAt(ind))) {
				ind++; 
				break;
			}
							
		}
		return ind;
	}

}
