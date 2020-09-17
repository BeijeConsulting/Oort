package csvandxml;
import phonebookgenerator.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CsvToXml {

	public static void main(String[] args) throws Exception {
		System.out.println("Start: " + LocalTime.now());
		// TODO Auto-generated method stub
		File file = new File("/temp/records.csv");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String fileToString = getContent(file);
//		System.out.println(fileToString);
		String header = fileToString.substring(0,fileToString.indexOf("\n"));
		String body = fileToString.substring(fileToString.indexOf("\n") + 1);
//		System.out.println(header);
		List<String> firstArray = stringToArrayList(header);
		List<String> dataArray = stringToArrayList(body);
//		System.out.println(dataArray);
		int namePosition = firstArray.indexOf("NOME");
		int surnamePosition = firstArray.indexOf("COGNOME");
		int mobilePosition = firstArray.indexOf("TELEFONO");
		int emailPosition = firstArray.indexOf("EMAIL");
//		System.out.println(namePosition);
//		System.out.println(dataArray.get(surnamePosition));
		List<String> namesList = listSplitter(dataArray, namePosition);
		List<String> surnamesList = listSplitter(dataArray, surnamePosition);
		List<String> mobileList = listSplitter(dataArray, mobilePosition);
		List<String> emailList = listSplitter(dataArray, emailPosition);
//		System.out.println(namesList);
//		System.out.println(surnamesList);
//		System.out.println(mobileList);
//		System.out.println(emailList);
		Contact contact = new Contact();
		

		
//		System.out.println(firstArray);
		
		
		
		
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
	
	public static List<String> listSplitter(List<String> list, int index) throws Exception {
		List<String> campi = new ArrayList<String>();
		for (int i = index; i < list.size(); i+=4) {
			campi.add(list.get(i));
		}
		return campi;
	}
	
	public static List<String> stringToArrayList (String str) {
		List<String> arraylist = new ArrayList<String>();
		int firstIndex = 0;
		int lastIndex = 0;
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == ';') {
				lastIndex = i;
				arraylist.add(str.substring(firstIndex, lastIndex));
				firstIndex = lastIndex + 1;
			}
			if(str.charAt(i) == '\n') {
				lastIndex = i;
				arraylist.add(str.substring(firstIndex, lastIndex));
				firstIndex = lastIndex + 1;
			}
			if(i == (str.length() - 1)) {
				arraylist.add(str.substring(firstIndex, i + 1));
			}
			
		}
		return arraylist;
	}
	

}
