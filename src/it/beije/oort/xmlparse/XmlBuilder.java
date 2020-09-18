package it.beije.oort.xmlparse;

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
 
public class XmlBuilder {
	//public static final String XML_FILE_PATH = "/temp/rubricaXml.xml";
	
	public void build(List<Contatto> contatti, File fileDestinazione) throws ParserConfigurationException, TransformerException {
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();
		
		Element root = document.createElement("rubrica");
		document.appendChild(root);
		for (Contatto c : contatti) {
			Element contatto = document.createElement("contatto");
			root.appendChild(contatto);
			
			Element nome = document.createElement("nome");
			nome.appendChild(document.createTextNode(c.getNome()));
			contatto.appendChild(nome);
			
			Element cognome = document.createElement("cognome");
			cognome.appendChild(document.createTextNode(c.getCognome()));
			contatto.appendChild(cognome);
			
			Element email = document.createElement("email");
			email.appendChild(document.createTextNode(c.getEmail()));
			contatto.appendChild(email);
			
			Element telefono = document.createElement("telefono");
			telefono.appendChild(document.createTextNode(c.getTelefono()));
			contatto.appendChild(telefono);
		}
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(fileDestinazione.getPath());
        transformer.transform(domSource, streamResult);
        System.out.println("Done creating XML file!");
	}
}
