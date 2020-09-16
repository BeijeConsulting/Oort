package it.beije.oort.bm.exercises.rubrica;

import java.io.File;
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
}
