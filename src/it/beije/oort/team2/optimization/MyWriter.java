package it.beije.oort.team2.optimization;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MyWriter {
	public static final ArrayList<Contatto> contatti = new ArrayList<>();
	
	public MyWriter() throws IOException {
		scritturaFile();
	}

	private void scritturaFile() throws IOException {
		File file = new File("/temp/rubrica.txt");
		FileWriter fileWriter = new FileWriter(file);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		
		for(int i = 0; i < contatti.size(); i++) {
			bufferedWriter.append(contatti.get(i).toString()).append('\n');
		}
	
		bufferedWriter.flush();
		bufferedWriter.close();
	}
}
