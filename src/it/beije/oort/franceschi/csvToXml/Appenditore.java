package it.beije.oort.franceschi.csvToXml;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.beije.oort.file.rubrica.Contatto;
import it.beije.oort.file.rubrica.comparators.ContattoNomeComparator;

public class Appenditore {
	/**
	 * Unisce due file e li salva in un terzo file. Ordina tutto il file.
	 * @param filePathUno
	 * @param filePathDue
	 * @param out
	 */
	public static void unisciFileEOrdina(String filePathUno, String filePathDue, String out) {
		List<Contatto> listaCompleta = unisciListeDaFiles(filePathUno, filePathDue);
		
		
		String ext = InputManager.getFileExt(new File(out));
		if (ext.equalsIgnoreCase("csv")) {
			CSVWriter.writeCSV(listaCompleta, out);
		} else if (ext.equalsIgnoreCase("xml")) {
			try {
				XMLWriter.writeList(listaCompleta, out);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("unused")
	@SafeVarargs
	private static List<Contatto> unisciListe(List<Contatto>... list){
		List<Contatto> listUnita = new ArrayList<>();
		for (List<Contatto> l : list) {
			listUnita.addAll(l);
		}
		return listUnita;	 
	}
	
	private static List<Contatto> unisciListeDaFiles(String file1, String file2) {
		String ext = InputManager.getFileExt(new File(file1));
		List<Contatto> vecchiaList = new ArrayList<>();
		List<Contatto> vecchiaList2 = new ArrayList<>();
		
		if (ext.equalsIgnoreCase("csv")) {
			vecchiaList = new CSVParser(file1).creaListaContatti();
		} else if (ext.equalsIgnoreCase("xml")) {
			try {
				vecchiaList = XMLParser.parseXML(file1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		ext = InputManager.getFileExt(new File(file2));
		if (ext.equalsIgnoreCase("csv")) {
			vecchiaList2 = new CSVParser(file2).creaListaContatti();
		} else if (ext.equalsIgnoreCase("xml")) {
			try {
				vecchiaList2 = XMLParser.parseXML(file2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		for (Contatto c : vecchiaList2) {
			vecchiaList.add(c);
		}
		Collections.sort(vecchiaList, new ContattoNomeComparator());
		return vecchiaList;
	}
	
	public static void append(List<Contatto> list, String out) {
		String ext = InputManager.getFileExt(new File(out));
		if (ext.equalsIgnoreCase("csv")) {
			CSVWriter.overWriteCSV(list, out);
		} else if (ext.equalsIgnoreCase("xml")) {
			try {
				XMLWriter.overwriteList(list, out);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void append(String in, String out) {
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
