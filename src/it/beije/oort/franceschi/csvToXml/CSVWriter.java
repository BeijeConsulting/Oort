package it.beije.oort.franceschi.csvToXml;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import it.beije.oort.file.rubrica.Contatto;
import it.beije.oort.file.rubrica.comparators.ContattoNomeComparator;

public class CSVWriter{	
	public static void writeCSV(List<Contatto> list, String outputPath, Comparator<Contatto> c) {
		Collections.sort(list, c);
		try (BufferedWriter bf = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(outputPath, false), StandardCharsets.UTF_8))) {
			bf.write("COGNOME;NOME;EMAIL;TELEFONO;\n");
			for (int i = 0; i < list.size(); i++) {
				if (i != list.size() - 1) {
					bf.write(list.get(i).toFormattedString() + '\n');
				} else {
					bf.write(list.get(i).toFormattedString());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeCSV(List<Contatto> list, String outputPath) {
		Collections.sort(list, new ContattoNomeComparator());
		//Collections.sort(list, new ContattoCognomeComparator())
		try (BufferedWriter bf = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(outputPath, false), StandardCharsets.UTF_8))) {
			bf.write("COGNOME;NOME;EMAIL;TELEFONO;\n");
			for (int i = 0; i < list.size(); i++) {
				if (i != list.size() - 1) {
					bf.write(list.get(i).toFormattedString() + '\n');
				} else {
					bf.write(list.get(i).toFormattedString());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void overWriteCSV(List<Contatto> list, String outputPath) {
		Collections.sort(list, new ContattoNomeComparator());
		//Collections.sort(list, new ContattoCognomeComparator())
		try (BufferedWriter bf = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(outputPath, true), StandardCharsets.UTF_8))) {
			bf.write("COGNOME;NOME;EMAIL;TELEFONO;\n");
			for (int i = 0; i < list.size(); i++) {
				if (i != list.size() - 1) {
					bf.write(list.get(i).toFormattedString() + '\n');
				} else {
					bf.write(list.get(i).toFormattedString());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
