package it.beije.oort.file.rubrica;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.ArrayList;
import java.time.*;

public class GeneratoreRubrica {
	
	private GeneratoreRubrica() {}
		
//	private static String removeSpace(String s) {
//		StringBuilder sb = new StringBuilder(s);
//		while(sb.indexOf(" ")!=-1) {
//			sb.deleteCharAt(sb.indexOf(" "));
//		}
//		return sb.toString();
//	}
//	
//	private static String removeSingleQuote(String s) {
//		StringBuilder sb = new StringBuilder(s);
//		while(sb.indexOf("'")!=-1) {
//			sb.deleteCharAt(sb.indexOf("'"));
//		}
//		return sb.toString();
//	}
//	
//	private static boolean usaNome() {
//		Random r = new Random();
//		int v = r.nextInt(5);
//		return (v == 1) ? false : true;
//	}
//	
//	private static boolean usaCognome() {
//		Random r = new Random();
//		int v = r.nextInt(3);
//		return (v == 1) ? false : true;
//	}
//	
//	private static String abbreviaVocale(String s) {
//		StringBuilder temp = new StringBuilder();
//		temp.append(s.charAt(0));
//		for (int i = 1; i < s.length(); i++) {
//			if ("AEIOUaeiou".indexOf(s.charAt(i)) != -1) {
//				temp.append(s.charAt(i));
//				break;
//			}
//			temp.append(s.charAt(i));
//		}
//		return temp.toString();
//	}
//	
//	private static String sostituisciVocali(String s) {
//		StringBuilder temp = new StringBuilder();
//		for (int i = 0; i < s.length(); i++) {
//			char c = s.charAt(i);
//			if (c == 'a' || c == 'A') {
//				temp.append(4);
//			} else if (c == 'e' || c == 'E') {
//				temp.append(3);
//			} else if (c == 'i' || c == 'I') {
//				temp.append(1);
//			} else if (c == 'o' || c == 'O') {
//				temp.append(0);
//			} else {
//				temp.append(c);
//			}
//		}
//		return temp.toString();
//	}
//
//	private static String mailNomeCognome(String nome, String cognome, String dominio) {
//		Random r = new Random();
//		StringBuilder email = new StringBuilder();
//		
//		// random 1-10 : valori 1-2 abbreviare il nome alla sola iniziale; 
//		int abbrevia = r.nextInt(9) + 1;
//		if (abbrevia <= 2) {
//			nome = nome.substring(0,1);
//		} else if (abbrevia <= 5) {
//		// 3-5 abbreviare fino alla prima vocale successiva alla prima lettera (compresa)
//			nome = abbreviaVocale(nome);
//		}
//		
//		/*
//		 *  separatore deciso da ulteriore random 1-10:
//			 1-3  nessuno
//			 3-7  . (punto)
//			 8-10 - (trattino) (edited) 
//		 */
//		String separatore = "";
//		int rSeparatore = r.nextInt(10) + 1;
//		if(rSeparatore > 3 && rSeparatore <= 7) {
//			separatore = ".";
//		} else if( rSeparatore <= 10) {
//			separatore = "-";
//		}
//		
//		if (r.nextInt(5) + 1 == 1) {
//			nome = sostituisciVocali(nome);
//			cognome = sostituisciVocali(cognome);
//		}
//		
//		// - random 1-4 : se esce 1 invertire nome con cognome
//		if ((r.nextInt(3)+1) == 1) {
//			email.append(cognome).append(separatore).append(nome);
//		} else {
//			email.append(nome).append(separatore).append(cognome);
//		}
//		
//		//Unisco
//		return GeneratoreRubrica.removeSpace(email.append("@")
//				.append(dominio).toString().toLowerCase());
//}
//	
//	private static String generaMail(String nome, String cognome, String dominio) {
//		StringBuilder email = new StringBuilder();
//		Random r = new Random();
//		int rand = r.nextInt(10);
//		boolean usaNome = GeneratoreRubrica.usaNome();
//		boolean usaCognome = GeneratoreRubrica.usaCognome();
//		
//		if (!usaNome && !usaCognome) {
//			for(int i=0;i<r.nextInt(15)+6;i++) {
//				email.append(r.nextInt(10));
//			}
//			email.append("@"+dominio);
//		} else if (!usaNome) {
//			if (rand != 1) email.append(GeneratoreRubrica.removeSpace(cognome) + r.nextInt(10) +
//					r.nextInt(10) + "@" + dominio);
//			else email.append(GeneratoreRubrica.removeSpace(cognome) + "@" + dominio);
//		} else if (!usaCognome){
//			if (rand != 1) email.append(GeneratoreRubrica.removeSpace(nome) + r.nextInt(10) +
//					r.nextInt(10) + "@" + dominio);
//			else email.append(GeneratoreRubrica.removeSpace(nome) + "@" + dominio);
//		} else {
//			email.append(GeneratoreRubrica.mailNomeCognome(nome, cognome, dominio));
//			//bonus track goes here
//		}
//		
//		return GeneratoreRubrica.removeSingleQuote(email.toString()).toLowerCase();
//	}
//	
//	private static String generaIdentita(String nome, String cognome,
//			String prefisso, String dominio) {
//		Random rr = new Random();
//		StringBuilder suffisso=new StringBuilder();
//		for(int i=0;i<7;i++) {
//			suffisso.append(rr.nextInt(10));
//		}
//
//		return "\""+nome+"\";\""+cognome+"\";\""+prefisso+suffisso.toString()+
//				"\";\""+GeneratoreRubrica.generaMail(nome, cognome, dominio)+"\"\n";
//		
//		return "\""+nome+"\";\""+cognome+"\";\""+prefisso+suffisso+
//		"\";\""+GeneratoreRubrica.removeSpace(nome)+"."+
//		GeneratoreRubrica.removeSpace(cognome)+"@"+dominio+"\"\n";
//	}
//	
	private static void writeRubrica(String filename, ArrayList<String> nomi, 
			ArrayList<String> cognomi) throws IOException{
		//sanificare filename
		Random r = new Random();
		//String[] prefissi = {"345", "346", "347", "348","349"};
		//String[] domini = {"gmail.com", "hotmail.com", "hotmail.it", 
		//		"libero.it", "yahoo.com", "virgilio.it", "tim.it", "alice.it"};
		//try-catch per IOException
		//BufferedWriter bf = new BufferedWriter(new FileWriter(filename, true));
		
		BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(filename, true), StandardCharsets.UTF_8));
		
		bf.write("NOME;COGNOME;TELEFONO;EMAIL;\n");
		ArrayList<Contatto> contatti = new ArrayList<>();
		for(int i=0;i<10_000_000;i++) {
			//bf.write(GeneratoreRubrica.generaIdentita(nomi.get(r.nextInt(nomi.size())),
			//		cognomi.get(r.nextInt(cognomi.size())), prefissi[r.nextInt(5)],
			//		domini[r.nextInt(8)]));
			bf.write(new Contatto(
					nomi.get(r.nextInt(nomi.size())), 
					cognomi.get(r.nextInt(cognomi.size())),
					Valori.getPrefisso(r.nextInt(5)),
					Valori.getDominio(r.nextInt(8))).toFormattedString() + '\n');
		}
		bf.close();
	}
	
//	private static ArrayList<String> getListFromFile(String path) throws IOException{
//		BufferedReader br = new BufferedReader(new FileReader(path));
//		ArrayList<String> valori = new ArrayList<>();
//		while (br.ready()) {
//			valori.add(br.readLine());
//		}
//		br.close();
//		return valori;
//	}
	
	public static void generaRubrica() throws IOException{
		GeneratoreRubrica.writeRubrica(Valori.getOutputPath(), Valori.getNomi(), Valori.getCognomi());
	}
	
	public static void main(String[] args) throws IOException{
		System.out.println(LocalTime.now());
		GeneratoreRubrica.generaRubrica();
		System.out.println(LocalTime.now());
	}	
}