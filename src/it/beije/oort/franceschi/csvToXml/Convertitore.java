package it.beije.oort.franceschi.csvToXml;

public class Convertitore {
	public static void csvToXml(String input, String output) {
		CSVParser r = new CSVParser(input);
		XMLWriter.writeList(r.creaListaContatti(), output);
	}

	public static void csvToXml(int i) {
		CSVParser r = new CSVParser(InputManager.getInCSVPath(i));
		XMLWriter.writeList(r.creaListaContatti(), InputManager.getInXMLPath(i));
	}

	public static void xmlToCsv(String input, String output) {
		try {
			CSVWriter.writeCSV(XMLParser.parseXML(input), output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void xmlToCsv(int i) {
		try {
			CSVWriter.writeCSV(XMLParser.parseXML(InputManager.getInXMLPath(i)), InputManager.getOutCSVPath(i));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
