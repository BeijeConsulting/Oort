package it.beije.oort.file.rubrica.utils;

import java.util.ArrayList;

import it.beije.oort.file.rubrica.Contatto;

/**
 * Classe di gestione di valori, tra cui nomi, cognomi, prefissi cellulare e suffissi email
 */
public class Valori {
	private Valori() {}
		
	private static final String pathNomi = "C:\\Code\\Oort\\input\\nomi.txt";
	private static final String pathCognomi = "C:\\Code\\Oort\\input\\cognomi.txt";
	private static final String outputPath = "C:\\Code\\Oort\\input\\rubrica.txt";
	
	private static ArrayList<String> nomi = Pulitore.getValues(pathNomi);
	private static ArrayList<String> cognomi = Pulitore.getValues(pathCognomi);
	private static String[] prefissi = {"345", "346", "347", "348","349"};
	private static String[] domini = {"gmail.com", "hotmail.com", "hotmail.it", 
			"libero.it", "yahoo.com", "virgilio.it", "tim.it", "alice.it"};
	
	public static ArrayList<String> emailComplete = new ArrayList<>();
	public static ArrayList<String> cellCompleti  = new ArrayList<>();
	public static ArrayList<Contatto> contatti  = new ArrayList<>();
	
	public static ArrayList<String> getNomi() {
		return nomi;
	}
	public static ArrayList<String> getCognomi() {
		return cognomi;
	}
	public static String getPrefisso(int index) {
		return prefissi[index];
	}
	public static String getDominio(int index) {
		return domini[index];
	}
	public static String getOutputPath() {
		return outputPath;
	}
}
