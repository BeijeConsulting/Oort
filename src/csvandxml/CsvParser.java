package csvandxml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {

	public static void main(String[] args) throws Exception {
		System.out.println("Start: " + LocalTime.now());
		// TODO Auto-generated method stub
		File file = new File("/temp/records.csv");
//		FileReader fileReader = new FileReader(file);
//		while (fileReader.ready()) {
//			System.out.print((char)fileReader.read());
//		}
		String fileToString = getContent(file);
		String header = fileToString.substring(0,fileToString.indexOf("\n"));
//		System.out.println(header);
		List<String> firstArray = new ArrayList<String>();
		int firstIndex = 0;
		int lastIndex = 0;
		for(int i = 0; i < header.length(); i++) {
			if(header.charAt(i) == ';') {
				lastIndex = i;
				firstArray.add(header.substring(firstIndex, lastIndex));
				firstIndex = lastIndex + 1;
			}
			if(i == (header.length() - 1)) {
				firstArray.add(header.substring(firstIndex, i + 1));
			}
			
		}
		System.out.println(firstArray);
		
		System.out.println("Done records: " + LocalTime.now());
	}
	
	public static String getContent(File file) throws IOException {
		FileReader fileReader = new FileReader(file);
		StringBuilder builder = new StringBuilder();
		
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		while (bufferedReader.ready()) {
			builder.append(bufferedReader.readLine()).append('\n');
		}
		
		return builder.toString();
	}
}
