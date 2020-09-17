package it.beije.oort.franceschi.csvToXml;

import java.io.File;
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

public class XMLParser {
	public static List<ContattoBean> parseXML(String filePath)
			throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.parse(new File(filePath));
		Element element = document.getDocumentElement();

		List<ContattoBean> rubrica = new ArrayList<ContattoBean>();
		NodeList contatti = element.getChildNodes();
		for (int i = 0; i < contatti.getLength(); i++) {
			Node node = contatti.item(i);
			if (node instanceof Element) {
				Element contatto = (Element) node;
				ContattoBean beanContatto = new ContattoBean();
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
							beanContatto.setCell(valore.getTextContent());
							break;
						case "email":
							beanContatto.setEmail(valore.getTextContent());
							break;
						}
					}
				}
				rubrica.add(beanContatto);
			}
		}
		return rubrica;
	}
}