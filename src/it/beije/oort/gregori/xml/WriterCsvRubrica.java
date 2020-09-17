package it.beije.oort.gregori.xml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import it.beije.oort.rubrica.Contatto;

public class WriterCsvRubrica {

	public static List<Contatto> readContatti(File xmlFile) throws ParserConfigurationException, SAXException, IOException {		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
		
        // Load the input XML document, parse it and return an instance of the Document class.
        Document document = builder.parse(xmlFile);
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
	
	public static void scritturaCsvFromXml(List<Contatto> records) throws IOException {
		File file = new File("/temp/rubrica-from-xml-to-csv.csv");
		FileWriter fileWriter = new FileWriter(file);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		
		bufferedWriter.append("COGNOME;NOME;EMAIL;TELEFONO\n");
		for(int i = 0; i < records.size(); i++) {
			String name = records.get(i).getNome();
			String surname = records.get(i).getCognome();
			String phone = records.get(i).getTelefono();
			String email = records.get(i).getEmail();
			bufferedWriter.append(surname).append(";").append(name).append(";").append(email).append(";").append(phone).append("\n");
		}
		
		bufferedWriter.flush();
		bufferedWriter.close();
	}
	
	public static void scritturaCsv() {
		for(int i = 0; )
		
	}
	
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		//File fileXml = new File("/temp/rubrica.xml");
		//WriterCsvRubrica.scritturaCsvFromXml(WriterCsvRubrica.readContatti(fileXml));
	}
	
	
	
}
