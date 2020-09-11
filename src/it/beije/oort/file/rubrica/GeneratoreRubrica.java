package it.beije.oort.file.rubrica;

import java.io.*;
import java.util.Random;
import java.util.ArrayList;

public class GeneratoreRubrica {
		
	private static String removeSpace(String s) {
		if(s.indexOf(" ")!=-1) {	
			StringBuilder sb = new StringBuilder(s);
			s = sb.deleteCharAt(sb.indexOf(" ")).toString();
		}
		return s;
	}
	
	private static String generaIdentita(String nome, String cognome,
			String prefisso, String dominio) {
		Random rr = new Random();
		String suffisso="";
		for(int i=0;i<7;i++) {
			suffisso += rr.nextInt(10);
		}
		return "\""+nome+"\";\""+cognome+"\";\""+prefisso+suffisso+
				"\";\""+GeneratoreRubrica.removeSpace(nome)+"."+
				GeneratoreRubrica.removeSpace(cognome)+"@"+dominio+"\"\n";
	}
	
	private static void writeRubrica(String filename, ArrayList<String> nomi, ArrayList<String> cognomi) 
			throws IOException{
		//sanificare filename
		Random r = new Random();
		String[] prefissi = {"345", "346", "347", "348","349"};
		String[] domini = {"gmail.com", "hotmail.com", "hotmail.it", 
				"libero.it", "yahoo.com", "virgilio.it", "tim.it", "alice.it"};
		//try-catch per IOException
		BufferedWriter bf = new BufferedWriter(new FileWriter(filename, true));
		bf.write("NOME;COGNOME;TELEFONO;EMAIL;\n");
		for(int i=0;i<1000;i++) {
			bf.write(GeneratoreRubrica.generaIdentita(nomi.get(r.nextInt(nomi.size())),
					cognomi.get(r.nextInt(cognomi.size())), prefissi[r.nextInt(5)],
					domini[r.nextInt(8)]));
		}
		bf.close();
	}
	
	private static ArrayList<String> getListFromFile(String path) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(path));
		ArrayList<String> valori = new ArrayList<>();
		while (br.ready()) {
			valori.add(br.readLine());
		}
		br.close();
		return valori;
	}
	
	public static void generaRubrica(String filename, String pathNomi, String pathCognomi) 
			throws IOException{
		ArrayList<String> nomi = GeneratoreRubrica.getListFromFile(pathNomi);
		ArrayList<String> cognomi = GeneratoreRubrica.getListFromFile(pathCognomi);
		GeneratoreRubrica.writeRubrica(filename, nomi, cognomi);
	}
	
	public static void main(String[] args) throws IOException{	
		GeneratoreRubrica.generaRubrica("C:/Users/Padawan12/Desktop/rubrica.txt",
				"C:/Users/Padawan12/Desktop/nomi.txt",
				"C:/Users/Padawan12/Desktop/cognomi.txt");
	}
	
	
	private static String generaMail(String nome, String cognome, String dominio) {
		String email = "";
		Random r = new Random();
		int rand = r.nextInt(10);
		if (!usaNome() && !usaCognome()) {
			for(int i =0;i<r.nextInt(16);i++) {
				
			}
		} else if (!usaNome()) {
			if (rand != 1) email = GeneratoreRubrica.removeSpace(cognome) + r.nextInt(10) + r.nextInt(10) + "@" + dominio;
			else email = GeneratoreRubrica.removeSpace(cognome) + "@" + dominio;
		} else if (!usaCognome()){
			if (rand != 1) email = GeneratoreRubrica.removeSpace(nome) + r.nextInt(10) + r.nextInt(10) + "@" + dominio;
			else email = GeneratoreRubrica.removeSpace(nome) + "@" + dominio;
		}
	}
	
	static boolean usaNome() {
		Random r = new Random();
		int v = r.nextInt(5);
		return (v == 1) ? false : true;
	}
	
	static boolean usaCognome() {
		Random r = new Random();
		int v = r.nextInt(3);
		return (v == 1) ? false : true;
	}
}