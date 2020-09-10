package it.beije.oort.bm.exercises.files;

import java.io.*;

public class WriteWhatUTell {

	public static void main(String[] args) throws IOException {
		File file = new File("./bin/it/beije/oort/bm/exercises/files/WWUT.txt");
		FileWriter wr = new FileWriter(file);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); //Lo scanner legge per tokens, odioso.
		String s = in.readLine();
		while(!s.equalsIgnoreCase("q")) {
			wr.write(s + '\n');
			wr.flush();
			s = in.readLine();
		}
		
		System.out.println("Addio :(");
		wr.close();
		in.close();

	}

}
