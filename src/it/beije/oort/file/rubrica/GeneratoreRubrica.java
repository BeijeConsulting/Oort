package it.beije.oort.file.rubrica;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.ArrayList;

public class GeneratoreRubrica {
	
	private final static int VALORI_DA_SCRIVERE = 100;

	private GeneratoreRubrica() {
	}

	public static void creaRubrica(ArrayList<String> nomi, ArrayList<String> cognomi) {
		for (int i = 0; i < VALORI_DA_SCRIVERE; i++) {
			Contatto c = new Contatto(nomi, cognomi);
			Valori.contatti.add(c);
		}
	}

	public static void writeRubrica(ArrayList<Contatto> rubrica, String filename) {
		Random r = new Random();
		try (BufferedWriter bf = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(filename, true), StandardCharsets.UTF_8))) {
			
			bf.write("COGNOME;NOME;TELEFONO;EMAIL;\n");
			
			for (int i = 0; i < rubrica.size(); i++) {
				bf.write(Contatto.getContattoFormattatoToString(rubrica, i, r) + '\n');
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}