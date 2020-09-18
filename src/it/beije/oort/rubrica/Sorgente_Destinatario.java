package it.beije.oort.rubrica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Sorgente_Destinatario {
	
	private ArrayList<Contatto> contatti = new ArrayList<>();
	private List<String> intestazioneList;
	public static String path = "/temp/sorgente_destinatario.csv";

	
	public void Sorgente_Destinatrio () throws IOException {
		leggiContattiCsv();
	}
	
	public void leggiContattiCsv() throws IOException {
		if (path.contains(".csv")) {

		File file = new File(path);
		FileReader reader = new FileReader(file);
		BufferedReader buffer = new BufferedReader(reader);
		
		String intestazione = (buffer.readLine());
		intestazioneList = Arrays.asList(intestazione.split(";"));

		while(buffer.ready()) {
			checkIntestazione(buffer.readLine().split(";"));
		}
				
		buffer.close();
	
		}
	}
	
	public static List<Contatto> leggiContattiXml (File path) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		Document document = builder.parse(path);
		Element element = document.getDocumentElement();       
		        
		List<Contatto> rubrica = new ArrayList<Contatto>();
		NodeList contatti = element.getChildNodes();

		for (int i = 0; i < contatti.getLength(); i++) {
			Node node = contatti.item(i);
			if (node instanceof Element) {
				Element contatto = (Element) node;
				Contatto beanContatto = new Contatto();
				NodeList valori = contatto.getChildNodes();
				for (int j = 0; j < valori.getLength(); j++) {
					Node n = valori.item(j);
					if (n instanceof Element) {
						Element valore = (Element) n;
						switch (valore.getTagName()) {
						case "nome":
							beanContatto.setNome(valore.getTextContent());
							break;
						case "cognome":
							beanContatto.setCognome(valore.getTextContent());
							break;
						case "telefono":
							beanContatto.setTelefono(valore.getTextContent());
							break;
						case "email":
							beanContatto.setEmail(valore.getTextContent());
							break;
								
						default:
							System.out.println("elemento in contatto non riconosciuto");
							break;
						}
					}
				}
				rubrica.add(beanContatto);
			}
		}
		return rubrica;
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

	
	
	
	
	public static void main(String[] args) {
	

	}

}
