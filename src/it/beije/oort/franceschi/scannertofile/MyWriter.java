package it.beije.oort.franceschi.scannertofile;

import java.io.File;
import java.io.FileWriter;

public class MyWriter {
	public static void writeToFile(File file, String old, String text) throws Exception {
		FileWriter wr = new FileWriter(file);
		old += text;
		
		wr.write(old.toString());
		
		wr.flush();
		wr.close();
	}
}