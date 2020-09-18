package it.beije.oort.franceschi.csvToXml;

import java.io.File;
import java.util.List;

import it.beije.oort.file.rubrica.Contatto;

public class Sovrascrittore {
	public static void overwrite(List<Contatto> list, String out) {
		String ext = InputManager.getFileExt(new File(out));
		if (ext.equalsIgnoreCase("csv")) {
			CSVWriter.overWriteCSV(list, out);
			System.out.println("Sovrascritto CSV.");
		} else if (ext.equalsIgnoreCase("xml")) {
			try {
				XMLWriter.overwriteList(list, out);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Sovrascritto XML.");
		}
	}

	public static void overwrite(String in, String out) {
		String extIn = InputManager.getFileExt(new File(in)).toLowerCase();
		String extOut = InputManager.getFileExt(new File(out)).toLowerCase();

		// Caso se In e Out sono uguali
		if (extIn.equals(extOut)) {
			if (extIn.equals("csv")) {
				CSVWriter.overWriteCSV(new CSVParser(in).creaListaContatti(), out);
			} else if (extIn.equals("xml")) {
				try {
					XMLWriter.overwriteList(XMLParser.parseXML(in), out);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("Sovrascritto XML.");
			}
		} else { // Caso se In e Out sono diversa
			if (extIn.equals("csv")) {
				try {
					XMLWriter.overwriteList((new CSVParser(in).creaListaContatti()), out);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				try {
					CSVWriter.overWriteCSV(XMLParser.parseXML(in), out);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
