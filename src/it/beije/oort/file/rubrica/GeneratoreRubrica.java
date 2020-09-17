package it.beije.oort.file.rubrica;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.ArrayList;
import java.time.*;

public class GeneratoreRubrica {

	private GeneratoreRubrica() {
	}

	private static void creaRubrica(ArrayList<String> nomi, ArrayList<String> cognomi) {
		Random r = new Random();
		for (int i = 0; i < 100; i++) {
			Contatto c = new Contatto(
					cognomi.get(r.nextInt(cognomi.size())), 
					nomi.get(r.nextInt(nomi.size())),
					GeneraNumero.generaNumero(), 
					Valori.getDominio(r.nextInt(8)));

			Valori.contatti.add(c);
		}
	}

	public static void writeRubrica(ArrayList<Contatto> rubrica, String filename) {
		Random r = new Random();
		try (BufferedWriter bf = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(filename, true), StandardCharsets.UTF_8))) {
			bf.write("COGNOME;NOME;TELEFONO;EMAIL;\n");
			for (int i = 0; i < rubrica.size(); i++) {
				StringBuilder s = new StringBuilder();
				Contatto c = rubrica.get(i);
				if ((r.nextInt(3) + 1) != 1) {
					s.append(c.getCognome());
				}
				s.append(";");
				if ((r.nextInt(5) + 1) != 1) {
					s.append(c.getNome());
				}
				s.append(";");
				s.append(c.getCell()).append(";").append(c.getEmail());
				
				bf.write(s.toString() + '\n');
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println(LocalTime.now());
		GeneratoreRubrica.creaRubrica(Valori.getNomi(), Valori.getCognomi());
		GeneratoreRubrica.writeRubrica(Valori.contatti, Valori.getOutputPath());
		System.out.println(LocalTime.now());
	}
}