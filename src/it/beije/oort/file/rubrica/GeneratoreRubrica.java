package it.beije.oort.file.rubrica;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.ArrayList;
import java.time.*;

public class GeneratoreRubrica {
	
	private GeneratoreRubrica() {}

	private static void writeRubrica(String filename, ArrayList<String> nomi, ArrayList<String> cognomi){
		//TODO: sanificare filename
		Random r = new Random();	
		try (BufferedWriter bf = 
				new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(filename, true), StandardCharsets.UTF_8))){
			bf.write("COGNOME;NOME;TELEFONO;EMAIL;\n");
			for(int i=0;i<100;i++) {
				bf.write(new Contatto(
						cognomi.get(r.nextInt(cognomi.size())),
						nomi.get(r.nextInt(nomi.size())), 
						Valori.getPrefisso(r.nextInt(5)),
						Valori.getDominio(r.nextInt(8))).toFormattedString() + '\n');
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void generaRubrica(){
		GeneratoreRubrica.writeRubrica(Valori.getOutputPath(), Valori.getNomi(), Valori.getCognomi());
		// TODO al momento generaRubrica chiama writeRubrica e basta. Volendo possiamo toglierlo
	}
	
	public static void main(String[] args){
		System.out.println(LocalTime.now());
		GeneratoreRubrica.generaRubrica();
		System.out.println(LocalTime.now());
	}	
}