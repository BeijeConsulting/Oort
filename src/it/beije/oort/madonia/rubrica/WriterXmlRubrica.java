package it.beije.oort.madonia.rubrica;

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

public class WriterXmlRubrica {
	
	public static void writeXmlFile(List<Contatto> contatti, File fileXml, boolean inOrdineNome) throws ParserConfigurationException, TransformerException {
		
		if(inOrdineNome) {
			contatti.sort(null);
		}
        Document document = costruisciDocumento(contatti);
        
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		
		StreamResult result = new StreamResult(fileXml);

		transformer.transform(source, result);
	}
	
	public static void writeXmlFile(List<Contatto> contatti, String pathfile, boolean inOrdineNome) throws ParserConfigurationException, TransformerException {
		File fileXml = new File(pathfile);
		WriterXmlRubrica.writeXmlFile(contatti,fileXml, inOrdineNome);
	}
	
	private static Document costruisciDocumento(List<Contatto> contatti) throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.newDocument();
        Element docElement = document.createElement("rubrica");
        document.appendChild(docElement);
        
        for (Contatto c : contatti) {
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
        
        return document;
	}
}
