package it.beije.oort.files;

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

import it.beije.oort.bru.db.rubrica.Contatto;

public class XmlParser {
	public static List<Contatto> readContatti(File fileXml) throws ParserConfigurationException, SAXException, IOException {
		
		List<Contatto> contatti = new ArrayList<Contatto>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(fileXml);
		doc.getDocumentElement().normalize();
		
		NodeList nodeList = doc.getElementsByTagName("contatto");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Contatto contatto = new Contatto();
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element)node;
				contatto.setNome(element.getElementsByTagName("nome").item(0).getTextContent());
				contatto.setCognome(element.getElementsByTagName("cognome").item(0).getTextContent());
				contatto.setEmail(element.getElementsByTagName("email").item(0).getTextContent());
				contatto.setTelefono(element.getElementsByTagName("telefono").item(0).getTextContent());
			}
			contatti.add(contatto);
		}
		
		return contatti;
	}

	public static void buildContatti(List<Contatto> contatti, File fileDestinazione) throws ParserConfigurationException, TransformerException {
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
	}
}
