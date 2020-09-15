package it.beije.oort.file;

import java.util.Arrays;
import java.util.Scanner;

public class ScannerExample {
	
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		String st = s.nextLine();
		while (!st.equalsIgnoreCase("Q")) {
			st = s.nextLine();
			System.out.println(st);
			
			//...
		}
		
		System.gc();
		System.out.println("BYE!!");
		s.close();

	}

}
