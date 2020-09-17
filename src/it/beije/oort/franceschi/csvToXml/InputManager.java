package it.beije.oort.franceschi.csvToXml;

/**
 * Utility class to keep my program tidy. This class handles the input and the
 * output of the program.
 * 
 * @author Alessio Franceschi
 *
 */
public class InputManager {
	private final String CSV_PATH = "C:\\Code\\Oort\\csv\\rubriche\\";
	private final String XML_PATH = "C:\\Code\\Oort\\xml\\rubriche\\";
	
	private final String[] cognomi = { "brugaletta", "sala", "mater", "mancuso", "maisto", "madonia", "lauria",
			"gregori", "girardi", "franceschi", "busseni", "bassanelli" };
	private final int inputAmount = cognomi.length;

	private int counter = 0;
	private int oppositeCounter = 0;

	/**
	 * 
	 * @return the path of the next input.
	 */
	public String getNextInputPath() {
		return CSV_PATH  + "rubrica_" + cognomi[counter] + ".csv";
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

	/**
	 * 
	 * @return the total amount of input files.
	 */
	public int getInputAmount() {
		return inputAmount;
	}
}
