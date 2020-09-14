package it.beije.oort.file.rubrica.input;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/*
 * Classe utility per sanificare gli input prima dell'utilizzo.
 * Rimuove spazi in eccesso, caratteri speciali ed eventuali caratteri pre e post parola.
 */

public class Pulitore {
	public static void main(String[] args) throws Exception{
		String fileNomi = "\\nomi.txt";
		String fileCognomi = "\\cognomi.txt";
		
		writeToFile(getValues(fileNomi), fileNomi);
		writeToFile(getValues(fileCognomi), fileCognomi);
	}
	
	private static ArrayList<String> getValues(String filePath) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		ArrayList<String> valori = new ArrayList<>();
		while (br.ready()) {
			String t = br.readLine();
			valori.add(correctUpperCase(t));
		}
		br.close();
		System.out.println("File " + filePath + " letto.");
		return valori;
	}
	
	private static void writeToFile(ArrayList<String> valori, String filePath) throws Exception {
		BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
		for (String s : valori) {
			bw.write(s);
			bw.newLine();
		}
		bw.flush();
		bw.close();
		System.out.println("File " + filePath + " pulito.");
	} 
	
	private static String correctUpperCase(String s) {
		String[] arr = s.trim().replaceAll(" +", " ").replaceAll("[-+.^:,]","").split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i].substring(0, 1).toUpperCase() + arr[i].substring(1);
			sb.append(arr[i] + ' ');
		}
		return sb.toString().trim();
	}
}