package it.beije.oort.file.rubrica.input;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Pulitore {

	public static void main(String[] args) throws Exception{
		String fileNomi = "\\nomi.txt";
		String fileCognomi = "\\cognomi.txt";
		
		ArrayList<String> nomi = getValues(fileNomi);
		ArrayList<String> cognomi = getValues(fileCognomi);
		
		writeToFile(nomi, fileNomi);
		writeToFile(cognomi, fileCognomi);
	}
	
	static ArrayList<String> getValues(String filePath ) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		ArrayList<String> valori = new ArrayList<>();
		while (br.ready()) {
			String t = br.readLine();
			 valori.add(allUp(t));
		}
		br.close();
		System.out.println("File letto.");
		return valori;
	}
	
	static void writeToFile(ArrayList<String> valori, String filePath) throws Exception {
		BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
		for (String s : valori) {
			bw.write(s);
			bw.newLine();
			}
		bw.flush();
		bw.close();
		System.out.println("File pulito.");
	} 
	
	static String allUp(String s) {
		s = s.trim().replaceAll(" +", " ").replaceAll("[-+.^:,]","");;
		String[] arr = s.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<arr.length; i++) {
			arr[i] = arr[i].substring(0, 1).toUpperCase() + arr[i].substring(1);
			sb.append(arr[i] + ' ');
		}
		return sb.toString().trim();
	}
}