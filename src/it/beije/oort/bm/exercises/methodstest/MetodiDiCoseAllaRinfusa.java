package it.beije.oort.bm.exercises.methodstest;

import java.util.ArrayList;
import java.util.Arrays;

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
		System.out.println("Regular:");
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		char [][] chars = new char[5][10];
		for(char[] row : chars) {
			for (int i = 0; i < row.length; i++) {
				row[i] = alphabet.charAt((int)(Math.random()*1000%alphabet.length()));
			}
		}
		for(char[] row : chars) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println();
		System.out.println("Not regular (probably):");
		chars = new char[5][];
		for(int i = 0; i<chars.length; i++) {
			chars[i] = new char[(int)(Math.random()*10)];
		}
		for(char[] row : chars) {
			for (int i = 0; i < row.length; i++) {
				row[i] = alphabet.charAt((int)(Math.random()*1000%alphabet.length()));
			}
		}
		for(char[] row : chars) {
			System.out.println(Arrays.toString(row));
		}
		System.out.println();
		System.out.println();
		System.out.println("############################################");
		System.out.println("############  ArrayList Methods ############");
		System.out.println("############################################");
		System.out.println();
		System.out.println();
		ArrayList<String> lista = new ArrayList<>();
		System.out.println("Nuova lista:");
		System.out.println(lista);
		String el1 = "INANI", el2 = "LEMANI", el3 = "LENARI";
		System.out.println("Aggiungo " + el1 + ":");
		lista.add(el1);
		System.out.println(lista);
		System.out.println("Aggiungo " + el2 + ":");
		lista.add(el2);
		System.out.println(lista);
		System.out.println("Aggiungo " + el3 + " nel mezzo:");
		lista.add(1,el3);
		System.out.println(lista);
		System.out.println("Elimino il primo elemento:");
		lista.remove(0);
		System.out.println(lista);
		System.out.println("Pulizia!");
		lista.clear();
		System.out.println(lista);
		System.out.println("E' vuota? " + (lista.size() == 0 ? "Sì":"No"));
		System.out.println("Aggiungo " + el1 + ":");
		lista.add(el1);
		System.out.println(lista);
		System.out.println("Sostituisco " + el1 + " con " + el2 + ":");
		lista.set(0, el2);
		System.out.println(lista);
		System.out.println("E' vuota? " + (lista.size() == 0 ? "Sì":"No"));
		System.out.println("Contiene " + el1 + "? " + (lista.contains(el1) ? "Sì":"No"));
		System.out.println("Contiene " + el1 + "? " + (lista.contains(el1) ? "Sì":"No"));
		System.out.println("Creo nuova lista con " + el2 + ":");
		ArrayList<String> lista2 = new ArrayList<>();
		lista2.add(el2);
		System.out.println(lista2);
		System.out.println("Le due liste sono uguali? " + (lista.equals(lista2) ? "Sì":"No"));
		System.out.println("Aggiungo " + el1 + " alla prima lista:");
		lista.add(el1);
		System.out.println("Sono ancora uguali? " + (lista.equals(lista2) ? "Sì":"No"));
		System.out.println(lista + " | " + lista2);

	}

}
