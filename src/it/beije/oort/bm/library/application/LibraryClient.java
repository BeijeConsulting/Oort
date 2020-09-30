package it.beije.oort.bm.library.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import it.beije.oort.bm.library.database.*;

public class LibraryClient {
	
	private static Database db;
	private static BufferedReader in;
	
	public static void main(String[] args) throws IOException {
		db = ConcreteDatabase.getDatabase();
		String command;
		in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome to Brando's Rubrica");
		while(true) {
			System.out.println();
			System.out.println("1 - Visualize records");
			System.out.println("2 - Search record");
			System.out.println("3 - Add record");
			System.out.println("4 - Update record");
			System.out.println("5 - Delete record");
			System.out.println("0 - Exit");
			command = in.readLine();
			switch(command) {
				case "1":
					break;
				case "2":
					break;
				case "3":
					break;
				case "4":
					break;
				case "5":
					break;
				case "0":
					System.out.println("See ya!");
					System.exit(0);
					break;
				default:
					System.out.println("What do you mean?");
			}
		}
	}
}
