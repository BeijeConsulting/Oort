package it.beije.oort.franceschi.scannertofile;

import java.io.File;
import java.util.Scanner;

public class MyProgram {

	public static void main(String[] args) throws Exception  {	
		MyProgram.run();
	}
	
	public static void run() throws Exception {
		String filePath = "C:\\Code\\Oort\\cose\\scannerWriter.txt";
		Scanner sc = new Scanner(System.in);
		File file = new File(filePath);
		StringBuilder input = new StringBuilder();
		String in = "", 
				old = MyReader.readFile(file);
		
		System.out.println("Write to file. Q to stop.");
		
		while (true) {
			in = sc.nextLine();
			if (in.equalsIgnoreCase("Q")) break;
			input.append(in +'\n');
		}
		
		MyWriter.writeToFile(file, old, input.toString());
		sc.close();
		System.out.println("File written.");
	}
}
