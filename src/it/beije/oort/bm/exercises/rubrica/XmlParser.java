package it.beije.oort.bm.exercises.rubrica;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlParser {
	
	public static void writeFile(File output, List<List<Contatto>> lists) throws ParserConfigurationException, TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.newDocument();
        Element docElement = document.createElement("contatti");
        document.appendChild(docElement);
        
        for(List<Contatto> list : lists) {
        	for (Contatto c : list) {
            	Element contatto = document.createElement("contatto");
            	
            	Element nome = document.createElement("nome");
            	Element cognome = document.createElement("cognome");
            	Element telefono = document.createElement("telefono");
            	Element email = document.createElement("email");
            	
            	nome.setTextContent(c.getNome());
            	cognome.setTextContent(c.getCognome());
            	telefono.setTextContent(c.getTelefono());
            	email.setTextContent(c.getEmail());
            	
            	contatto.appendChild(nome);
            	contatto.appendChild(cognome);
            	contatto.appendChild(telefono);
            	contatto.appendChild(email);

            	docElement.appendChild(contatto);
            }
        }
        
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		
		StreamResult result = new StreamResult(output);

		transformer.transform(source, result);
	}
	
	public static List<Contatto> readContatti(File xmlFile) throws ParserConfigurationException, SAXException, IOException {		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
		
        Document document = builder.parse(xmlFile);
        Element element = document.getDocumentElement();
        
        List<Contatto> ret = new ArrayList<Contatto>();
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
                ret.add(beanContatto);
        	}
        }
        
        return ret;
        
	}
}
