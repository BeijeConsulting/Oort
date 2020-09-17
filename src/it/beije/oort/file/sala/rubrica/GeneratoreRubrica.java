package it.beije.oort.file.sala.rubrica;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.time.*;
import it.beije.oort.file.sala.Contatto;

public class GeneratoreRubrica {
	
	private GeneratoreRubrica() {}

	private static void writeRubrica(String filename, ArrayList<String> nomi,
			ArrayList<String> cognomi){
		//TODO: sanificare filename
		Random r = new Random();	
		try (BufferedWriter bf = 
				new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(filename, true), StandardCharsets.UTF_8))){
			bf.write("NOME;COGNOME;TELEFONO;EMAIL;\n");
			for(int i=0;i<100;i++) {
				bf.write(new Contatto2(
						nomi.get(r.nextInt(nomi.size())), 
						cognomi.get(r.nextInt(cognomi.size())),
						Valori.getPrefisso(r.nextInt(5)),
						Valori.getDominio(r.nextInt(8))).toFormattedString() + '\n');
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Contatto> generaRubrica(String filename, ArrayList<String> nomi,
			ArrayList<String> cognomi, int dimensione) {
		Random r = new Random();
		
		
		return new ArrayList<Contatto>();
	}
	
	public static List<Contatto> generaRubrica(String filename, ArrayList<String> nomi,
			ArrayList<String> cognomi) {
		return GeneratoreRubrica.generaRubrica(filename, nomi, cognomi, 100);
	}
	
	public static void main(String[] args){
		System.out.println(LocalTime.now());
		//GeneratoreRubrica.generaRubrica();
		System.out.println(LocalTime.now());
	}	
}