package it.beije.oort.franceschi.csvToXml;

/**
 * Utility class to keep my program tidy. This class handles the input and the
 * output of the program.
 * 
 * @author Alessio Franceschi
 *
 */
public class InputManager {
	private final String IN_PATH = "..\\Oort\\inputs\\rubriche\\";
	private final String OUT_PATH = "C:\\Code\\Oort\\ouputs\\rubriche\\";
	private final String[] cognomi = { "brugaletta", "sala", "mater", "mancuso", "maisto", "madonia", "lauria",
			"gregori", "girardi", "franceschi", "busseni", "bassanelli" };
	private final int inputAmount = cognomi.length;

	private int counter = 0;

	/**
	 * 
	 * @return the path of the next input.
	 */
	public String getNextInputPath() {
		return IN_PATH + "rubrica_" + cognomi[counter] + ".csv";
	}

	/**
	 * This method also increase the counter to go to the next input/output couple.
	 * 
	 * @return the path of the next output.
	 */
	public String getNextOutputPath() {
		return OUT_PATH + "rubrica_" + cognomi[counter++] + ".xml";
	}

	/**
	 * 
	 * @return the total amount of input files.
	 */
	public int getInputAmount() {
		return inputAmount;
	}
}
