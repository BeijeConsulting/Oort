package it.beije.oort.franceschi.csvToXml;

import java.io.File;

/**
 * Utility class to keep my program tidy. This class handles the input and the
 * output of the program.
 * 
 * @author Alessio Franceschi
 *
 */
public class InputManager {
	private static final String CSV_PATH = "C:\\Code\\Oort\\csv\\rubriche\\";
	private static final String XML_PATH = "C:\\Code\\Oort\\xml\\rubriche\\";

	private static final String[] cognomi = { "brugaletta", "sala", "mater", "mancuso", "maisto", "madonia", "lauria",
			"gregori", "girardi", "franceschi", "busseni", "bassanelli" };
	private static final int inputAmount = cognomi.length;

	private int counter = 0;
	private int oppositeCounter = 0;

	/**
	 * 
	 * @return the path of the next input.
	 */
	public String getNextInputPath() {
		return CSV_PATH + "rubrica_" + cognomi[counter] + ".csv";
	}

	public String getNextOuputPathReverse() {
		return CSV_PATH + "rubrica_" + cognomi[oppositeCounter++] + "_convertitoDaXML.csv";
	}

	/**
	 * This method also increase the counter to go to the next input/output couple.
	 * 
	 * @return the path of the next output.
	 */
	public String getNextOutputPath() {
		return XML_PATH + "rubrica_" + cognomi[counter++] + ".xml";
	}

	public String getNextInputPathReverse() {
		return XML_PATH + "rubrica_" + cognomi[oppositeCounter] + ".xml";
	}

	public static String getInCSVPath(int i) {
		return CSV_PATH + "rubrica_" + cognomi[i] + ".csv";
	}

	public static String getOutCSVPath(int i) {
		return CSV_PATH + "rubrica_" + cognomi[i] + "_out.csv";
	}

	public static String getInXMLPath(int i) {
		return XML_PATH + "rubrica_" + cognomi[i] + ".xml";
	}

	public static String getOutXMLPath(int i) {
		return XML_PATH + "rubrica_" + cognomi[i] + "_out.xml";
	}

	/**
	 * 
	 * @return the total amount of input files.
	 */
	public static int getInputAmount() {
		return inputAmount;
	}

	public static String getFileExt(File file) {
		String name = file.getName();
		int lastIndexOf = name.lastIndexOf(".");
		if (lastIndexOf == -1) {
			return "";
		}
		return name.substring(lastIndexOf + 1);
	}
}
