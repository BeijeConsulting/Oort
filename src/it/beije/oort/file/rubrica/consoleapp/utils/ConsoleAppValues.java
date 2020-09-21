package it.beije.oort.file.rubrica.consoleapp.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.beije.oort.file.rubrica.Contatto;

public class ConsoleAppValues {
	private static List<String> comandi = Arrays.asList("add", "delete", "clean", "list", "load", "getFile", "modify", "sort", "help", "quit");
	public 	static List<Contatto> contatti = new ArrayList<Contatto>();
	private static final String path = "C:\\Code\\Oort\\input\\";
	private static final String out = "consoleOut.csv";
	
	public static List<String> getComandi(){return comandi;}
	public static String getOutPath() {return path+out;}
	public static String getPath() {return path;}
}
