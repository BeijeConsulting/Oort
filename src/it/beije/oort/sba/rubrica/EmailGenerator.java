package it.beije.oort.sba.rubrica;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EmailGenerator {
	private List<String> listaEmail = new ArrayList<>();
	
	public String formatEmail(String paramNome, String paramCognome, ListRandomSelector dominio) {
		Random r = new Random();
		//Genero un numero random da 1 a 10.
		int rand = r.nextInt(10) + 1;
		//Se il numero è nell'intervallo [1,2] NON generare alcuna email e lasciare il campo vuoto.
		if(rand<3) return "";
		//Se il numero è nell'intervallo [3,5] NON generare alcuna email ma pescare in modo randomico una email dalla listaEmail.
		else if(rand<6) return selectRandomFromGenerated();
		//Altrimenti generare email con regole precedenti.
		else {
			//Pescare due numeri random, uno da 1 a 5, un altro da 1 a 3.
			int n1 = r.nextInt(5) + 1;
			int n2 = r.nextInt(3) + 1;
			StringBuilder ret = new StringBuilder();
			boolean nome = true, cognome = true;
			if(n1==1) nome = false;
			if(n2==1) cognome = false;
			/* SE mancano sia NOME che COGNOME nella mail, generare la mail con numero random da 6 a 20 caratteri */
			if(!nome && !cognome) {
				for(int i = 0; i<r.nextInt(14)+6; i++) {
					ret.append((char)(r.nextInt(123-97)+97));
				}
			}else if(!nome){ // manca solo il nome 
				ret.append(paramCognome).append(genNum());
			}else if(!cognome) { //manca solo il cognome
				ret.append(paramNome).append(genNum());
			}else {	//ci sono sia nome che cognome
				String separatore="";
				int n3 = r.nextInt(10)+1;
				if(n3>3 && n3<=7) separatore = ".";
				else if(n3>=8) separatore = "-";
				int n4 = r.nextInt(10)+1;
				if(n4<=2) {
					paramNome = paramNome.substring(0,1);
				} else if(n4<=5) {
					paramNome = paramNome.substring(0,primaVoc(paramNome));
				}			
				if(r.nextInt(4)+1==1) {
					ret.append(paramCognome).append(separatore).append(paramNome);
				} else { 
					ret.append(paramNome).append(separatore).append(paramCognome);				
				}
				ret = replaceVowels(ret);
			}	
			ret.append("@").append(dominio.getNext());
			String converted = ret.toString();
			listaEmail.add(converted);
			return converted;
		}

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
	
	/* il metodo genNum genera un numero random da 00 a 100 nel caso in cui
	 * un numero pescato da 1 a 10 sia diverso da 1. */
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
	
	//Questo metodo restituisce un elemento pescato random dalla listaEmail.
	private String selectRandomFromGenerated() {
		if(listaEmail.isEmpty()) return "";
		else {
			ListRandomSelector lrs = new ListRandomSelector(listaEmail);
			return lrs.getNext();
		}
	}
}