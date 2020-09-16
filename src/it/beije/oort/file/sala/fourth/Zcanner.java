package it.beije.oort.file.sala.fourth;

import java.util.Scanner;
import java.io.*;

public class Zcanner {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Filename for the output file, with no etension:");
		String filename = s.next();
		File f = new File("C:/Users/Padawan12/Desktop/"+filename.trim()+".txt");
		System.out.println("Write lines here and hit enter to write them to file:");
		String st="";
		
		while (!st.equalsIgnoreCase("Q")) {
			st = s.next();
			System.out.println(st);
			
			//...
		}
		s.close();

	}

}
