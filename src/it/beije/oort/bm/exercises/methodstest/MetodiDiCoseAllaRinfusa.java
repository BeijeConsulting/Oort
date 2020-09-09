package it.beije.oort.bm.exercises.methodstest;

import java.util.Arrays;
import java.util.Random;

public class MetodiDiCoseAllaRinfusa {

	public static void main(String[] args) {
		String s = "Lorem ipsum dolor sit amet, consectetur adipisci elit, sed do eiusmod tempor incidunt ut labore et dolore magna aliqua.";
		System.out.println("############################################");
		System.out.println("##########  Metodi delle stringhe ##########");
		System.out.println("############################################");
		System.out.println();
		System.out.println("Stringa: " + s);
		System.out.println("Lunghezza: " + s.length());
		System.out.println("Carattere in posizione #21: " + s.charAt(21));
		System.out.println("Indice della parola \"dolor\": " + s.indexOf("dolor"));
		System.out.println("Sottostringa [6,11): " + s.substring(6,11));
		System.out.println("MAIUSCOLA: " + s.toUpperCase());
		System.out.println("minuscola: " + s.toLowerCase());
		System.out.println("Confronto tra M e m: " + s.toUpperCase().equals(s.toLowerCase()));
		System.out.println("Confronto tra M e m ignore case: " + s.toUpperCase().equalsIgnoreCase(s.toLowerCase()));
		System.out.println("---> Sopra sono concatenati, bello.");
		System.out.println("Sostituire \"ipsum\" con INANI: " + s.replace("ipsum", "INANI"));
		StringBuilder sb = new StringBuilder(s);
		System.out.println("Al contrario (StringBuilder):" + sb.reverse());
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("############################################");
		System.out.println("#########  Array Multidimensionale #########");
		System.out.println("############################################");
		System.out.println();
		char [][] chars = new char[5][10];
		for(char[] row : chars) {
			for(char cc : row) {
				cc = (char)(Math.random()*10000000%Character.MAX_VALUE);
			}
		}
		for(char[] row : chars) {
			System.out.println(Arrays.toString(row));
		}

	}

}
