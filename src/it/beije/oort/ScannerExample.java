package it.beije.oort;

import java.util.Arrays;
import java.util.Scanner;

public class ScannerExample {
	
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		String st = s.next();
		while (!st.equalsIgnoreCase("Q")) {
			System.out.println(st);
			st = s.next();
			
			//...
		}
		
		System.gc();
		System.out.println("BYE!!");
		s.close();

	}

}
