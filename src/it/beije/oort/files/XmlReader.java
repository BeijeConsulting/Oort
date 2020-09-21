package it.beije.oort.files;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class XmlReader {
	public List<Contatto> readContatti(File fileXml) throws ParserConfigurationException, SAXException, IOException {
		
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
}
