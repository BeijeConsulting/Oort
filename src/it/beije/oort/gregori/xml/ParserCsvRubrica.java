package it.beije.oort.gregori.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class ParserCsvRubrica {
	private ArrayList<Contatto> contatti = new ArrayList<>();
	private List<String> intestazioneList;
	private List<String> pathList = 
			Arrays.asList("rubrica_bassanelli.csv", "rubrica_brugaletta.csv", "rubrica_busseni.csv",
					"rubrica_franceschi.csv", "rubrica_girardi.csv", "rubrica_gregori.csv",
					"rubrica_madonia.csv", "rubrica_maisto.csv", "rubrica_mancuso.csv",
					"rubrica_mater.csv", "rubrica_sala.csv");
	private String generatedPath;
	
	public ParserCsvRubrica() throws IOException {
		leggiContatti();
		generatedPath = getRandomPath();
	}
	
	public String getGeneratedPath() {
		return generatedPath;
	}

	public ArrayList<Contatto> getContatti() {
		return contatti;
	}
	
	private String getRandomPath() {
		Random random = new Random();
		int index = random.nextInt(pathList.size());
		return "/temp/" + pathList.get(index);
	}

	public void leggiContatti() throws IOException {
		
		File file = new File(getRandomPath());
		FileReader reader = new FileReader(file);
		BufferedReader buffer = new BufferedReader(reader);
		
		String intestazione = (buffer.readLine());
		intestazioneList = Arrays.asList(intestazione.split(";"));
		
		while(buffer.ready()) {
			checkIntestazione(buffer.readLine().split(";"));
		}
		
		buffer.close();
	}
	
	public void checkIntestazione(String[] row) {
		Contatto c = new Contatto();
		for(int i = 0; i < intestazioneList.size(); i++) {
			if(intestazioneList.get(i).equalsIgnoreCase("nome")) c.setNome(row[i]);
			else if(intestazioneList.get(i).equalsIgnoreCase("cognome")) c.setCognome(row[i]);
			else if(intestazioneList.get(i).equalsIgnoreCase("email") ||
					intestazioneList.get(i).equalsIgnoreCase("mail") ||
					intestazioneList.get(i).equalsIgnoreCase("e-mail")) c.setEmail(row[i]);
			else if(intestazioneList.get(i).equalsIgnoreCase("telefono")) c.setTelefono(row[i]);
		}
		contatti.add(c);
	}
	
	public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException {
		WriterXmlRubrica.writeContatti();
	}
}
