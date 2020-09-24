package it.beije.oort.rubrica;

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

public class ParserXmlRubrica {
	
	
	public static void rubricaCsvWriter(File file, List<Contatto> contatti) throws IOException {
		FileWriter writer = new FileWriter(file);
		writer.write("COGNOME;NOME;EMAIL;TELEFONO\n");	
		StringBuilder builder = new StringBuilder();
		for(int i = 0;i<contatti.size();i++) {
			builder.append(contatti.get(i).getCognome()).append(";")
					.append(contatti.get(i).getNome()).append(";")
					.append(contatti.get(i).getEmail()).append(";")
					.append(contatti.get(i).getTelefono()).append("\n");
			}
		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}
	
	
	public static List<Contatto> readContattiXml(File csvFile) throws ParserConfigurationException, SAXException, IOException {		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
		
        // Load the input XML document, parse it and return an instance of the Document class.
        Document document = builder.parse(csvFile);
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

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		final String PATH = "./src\\it\\beije\\oort\\rubrica\\";
		File filecsv = new File(PATH+"rubrica2.csv");
		File filexml = new File(PATH+"rubrica.xml");
		rubricaCsvWriter(filecsv, readContattiXml(filexml));
	}

}
