package it.beije.oort.maisto;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class FirtsFileProve {
	
	



	public static void main(String[] args) throws IOException {
		File file = new File("/temp/fileDiProva2.csv"); 
		FileWriter writer = new FileWriter(file);

		Scanner sc = new Scanner(System.in);
		Scanner sch = new Scanner(System.in);
		StringBuilder build = new StringBuilder();
		System.out.println("ATTENZIONE! Lanciare il programma significa sovrascrivere il file già esistente, se presente!");
//		System.out.print("Continuare lo stesso? (Y/n) :  ");
//		String scelta = sch.next();
//		    if (scelta != "n") {
		System.out.println("Digitare QUIT per terminare e salvare il file.");
		System.out.println("Start write:");
		
		
	
		while(!sc.hasNext("QUIT")) {
			build.append(sc.nextLine());
			build.append("\n");
		}

		if (build.length() > 0) {
			build.deleteCharAt(build.length() - 1);
		}

		sc.close();
		writer.write(build.toString());
		writer.flush();
		writer.close();
//		    }
//		    else 
//		    	System.out.println("Chiusura..");
		
		

	}

}