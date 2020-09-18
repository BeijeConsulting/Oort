package it.beije.oort.franceschi.csvToXml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import it.beije.oort.file.rubrica.Contatto;

/*
 * se tutti i campi vuoti, almeno uno deve essere inserito
alla fine, vuoi salvarlo, modificarlo o scaricarlo?

 */

public class ConsoleApp {

	static List<String> comandi = Arrays.asList("add", "delete", "get", "getFile", "modify", "quit");
	static List<Contatto> contatti = new ArrayList<Contatto>();
	static int counter = -1;
	static final String out = "C:\\Code\\Oort\\input\\consoleOut.csv";

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line = "";
		System.out.println("Input possibili:");
		for (String s : comandi)
			System.out.print(s + " ");
		System.out.println();
		while (!line.equalsIgnoreCase("quit")) {
			System.out.println("Cosa desideri fare?");
			line = sc.nextLine().toLowerCase();

			if (!isValidInput(line)) {
				System.out.println("Input non valido");
				continue;
			}

			switch (line) {
			case "add":
				// add
				addContatto(sc);
				continue;
			case "delete":
				delete(sc);
				continue;
			case "get":
				get();
				continue;
			case "modify":

				continue;
			case "getfile":
				getFile();
				continue;
			}
		}
		System.out.println(contatti.toString());
		System.out.println("Programma terminato");
		sc.close();
	}

	private static void getFile() {
		CSVWriter.writeCSV(contatti, out);
		System.out.println("Contatti salvati su CSV a questo indirizzo:");
		System.out.println(out);
	}

	public static boolean isValidInput(String s) {
		for (String string : comandi) {
			if (string.equalsIgnoreCase(s)) {
				return true;
			}
		}
		return false;

	}

	// TODO: verificare sempre l'input
	private static void addContatto(Scanner sc) {
		Contatto c = new Contatto();
		System.out.print("Nome: ");
		c.setNome(sc.nextLine());
		System.out.print("Cognome: ");
		c.setCognome(sc.nextLine());
		System.out.print("Telefono: ");
		c.setCell(sc.nextLine());
		System.out.print("Email: ");
		c.setEmail(sc.nextLine());

		if (isEmpty(c)) {
			System.out.println("Input invalido. Inserire almeno un campo.");
		} else {
			contatti.add(c);
			counter++;
			System.out.println("Contatto aggiunto.");
		}
	}

	private static void get() {
		for (Contatto c : contatti) {
			System.out.println(c.toString());
		}
	}

	private static void delete(Scanner sc) {
		if (!(contatti.size() > 0)) {
			System.out.println("Non puoi cancellare nulla se la lista è vuota. " + "Aggiungi qualcosa!");
			return;
		}
		System.out.println(
				"Inserisci l'indice del contatto che vuoi eliminare. " + "Scrivi \"last\" per eliminare l'ultimo");
		String s = sc.nextLine();
		if (s.equalsIgnoreCase("last")) {
			contatti.remove(counter--);
			System.out.println("Ultimo Contatto aggiunto eliminato.");
		} else {
			int i = 0;
			try {
				i = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				System.out.println("Non hai inserito un numero.");
				delete(sc);
				return;
			}
			if (i < 0 || i > contatti.size()) {
				System.out.println("Indice non valido");
				return;
			} else {
				contatti.remove(i);
				System.out.println("Contatto in posizione " + i + " eliminato.");
			}

		}
	}

	private static boolean isEmpty(Contatto c) {
		if (c.getNome().equals("") & c.getCognome().equals("") & c.getCell().equals("") & c.getEmail().equals("")) {
			return true;
		} else
			return false;
	}

}
