package it.beije.oort.franceschi.csvToXml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import it.beije.oort.file.rubrica.Contatto;

/**
 * 
 * CSVParser reads CSV files and creates a List<> of ContattoBean based on the
 * header of the file. Should be easy to scale to accommodate more of
 * ContattoBean's variables, in case we'll add more.
 * 
 * @author Alessio Franceschi
 *
 */
public class CSVParser {
	private int posNome;
	private int posCognome;
	private int posCell;
	private int posEmail;

	private String filePath;
	private String[] headers;

	/**
	 * I decided to make this class non-static as I didn't want to give the filePath
	 * as parameter every time. So, you have to create a CSVReader object in order
	 * to use these methods. The Constructor will also prepare variables all on his
	 * own.
	 * 
	 * @param filePath
	 */
	public CSVParser(String filePath) {
		this.filePath = filePath;
		headers = prepareHeaders();
		assignPositions(headers);
	}

	public CSVParser(File file) {
		this.filePath = file.getAbsolutePath();
		headers = prepareHeaders();
		assignPositions(headers);
	}

	/**
	 * Assign the correct position to the ContattoBean variables, depending on the
	 * CSV's header.
	 * 
	 * @param headerVal an Array containing the values present in the header
	 */
	private void assignPositions(String[] headerVal) {
		for (int i = 0; i < headerVal.length; i++) {
			switch (headerVal[i].toUpperCase()) {
			case "NOME":
				setPosNome(i);
				break;
			case "COGNOME":
				setPosCognome(i);
				break;
			case "EMAIL":
				setPosEmail(i);
				break;
			case "E-MAIL":
				setPosEmail(i);
				break;
			case "TELEFONO":
				setPosCell(i);
				break;
			case "CELL":
				setPosCell(i);
				break;
			default:
				System.err.println("Errore, header invalido");
			}
		}
	}

	/**
	 * Create and return a List of ContattoBean from the CSV file.
	 * 
	 * @return a List containing all the ContattoBean present in the CSV.
	 */
	public List<Contatto> creaListaContatti() {
		String contattoLine = "";
		List<Contatto> contatti = new ArrayList<Contatto>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			br.readLine();
			while (br.ready()) {
				Contatto c = new Contatto();
				contattoLine = br.readLine();
				String contattoVals[] = contattoLine.split(";");
				for (int i = 0; i < contattoVals.length; i++) {
					if (i == getPosNome()) {
						c.setNome(contattoVals[i]);
					} else if (i == getPosCognome()) {
						c.setCognome(contattoVals[i]);
					} else if (i == getPosCell()) {
						c.setCell(contattoVals[i]);
					} else if (i == getPosEmail()) {
						c.setEmail(contattoVals[i]);
					}
				}
				if (c.getNome() == null) {
					c.setNome("");
				} 
				if (c.getCognome() == null) {
					c.setCognome("");
				}
				if (c.getEmail() == null) {
					c.setEmail("");
				}
				if (c.getCell() == null) {
					c.setCell("");;
				}
				contatti.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contatti;
	}

	public String getFilePath() {
		return filePath;
	}

	public String[] getHeaders() {
		return headers;
	}

	public int getPosCell() {
		return posCell;
	}

	public int getPosCognome() {
		return posCognome;
	}

	public int getPosEmail() {
		return posEmail;
	}

	public int getPosNome() {
		return posNome;
	}

	/**
	 * 
	 * @return an Array containing the CSV header's values
	 */
	private String[] prepareHeaders() {
		String header = "";
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			if (br.ready()) {
				header = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return header.split(";");
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

	public void setPosCell(int posCell) {
		this.posCell = posCell;
	}

	public void setPosCognome(int posCognome) {
		this.posCognome = posCognome;
	}

	public void setPosEmail(int posEmail) {
		this.posEmail = posEmail;
	}

	public void setPosNome(int posNome) {
		this.posNome = posNome;
	}
}