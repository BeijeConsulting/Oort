package it.beije.oort.franceschi.csvToXml;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import it.beije.oort.file.rubrica.Contatto;

public class CSVWriter {
	public static void writeCSV(List<Contatto> list, String outputPath) {
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
