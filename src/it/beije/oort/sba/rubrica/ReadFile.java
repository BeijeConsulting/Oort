package it.beije.oort.sba.rubrica;

import java.io.*;
import java.util.*;

public class ReadFile {
	public List<String> getContent(File file) throws IOException {
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<String> campi = new ArrayList<String>();
		while (bufferedReader.ready()) {
			campi.add(bufferedReader.readLine());
		}
		bufferedReader.close();
		return campi;
	}
}
