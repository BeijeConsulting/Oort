package it.beije.oort.csvToXml;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
	private int posNome;
	private int posCognome;
	private int posCell;
	private int posEmail;
	
	private String filePath;
	private String[] headers;
	
	public CSVReader(String filePath) {
		this.filePath = filePath;
		headers = prepareHeaders();
		assignPositions(headers);
	}
	
	public String[] prepareHeaders(){
		String header = "";
			try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
				if (br.ready()) {
					header = br.readLine();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return header.split(";");
	}
	
	public void assignPositions(String[] headerVal) {
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
	
	public List<ContattoBean> creaListaContatti(){
		String contattoLine = "";
		List<ContattoBean> contatti = new ArrayList<ContattoBean>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))){	
			br.readLine();
			while (br.ready()) {
				ContattoBean c = new ContattoBean();
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
				contatti.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contatti;
	}	

	public int getPosNome() {
		return posNome;
	}
	public void setPosNome(int posNome) {
		this.posNome = posNome;
	}
	public int getPosCognome() {
		return posCognome;
	}
	public void setPosCognome(int posCognome) {
		this.posCognome = posCognome;
	}
	public int getPosCell() {
		return posCell;
	}
	public void setPosCell(int posCell) {
		this.posCell = posCell;
	}
	public int getPosEmail() {
		return posEmail;
	}
	public void setPosEmail(int posEmail) {
		this.posEmail = posEmail;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String[] getHeaders() {
		return headers;
	}

	public void setHeaders(String[] headers) {
		this.headers = headers;
	}
}