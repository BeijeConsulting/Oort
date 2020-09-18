package it.beije.oort.franceschi.csvToXml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import it.beije.oort.file.rubrica.Contatto;

/**
 * Utility class. Currently used only for a single method.
 * 
 * @author Alessio Franceschi
 *
 */
public class XMLWriter {
	
	public static void overwriteList(List<Contatto> list, String in) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(in);
        Element root = document.getDocumentElement();    
        
        for (Contatto c : list) {
			Element contatto = document.createElement("contatto");

			Element nome = document.createElement("nome");
			Element cognome = document.createElement("cognome");
			Element telefono = document.createElement("telefono");
			Element email = document.createElement("email");

			nome.setTextContent(c.getNome());
			cognome.setTextContent(c.getCognome());
			telefono.setTextContent(c.getCell());
			email.setTextContent(c.getEmail());

			contatto.appendChild(nome);
			contatto.appendChild(cognome);
			contatto.appendChild(telefono);
			contatto.appendChild(email);

			root.appendChild(contatto);
		}
        DOMSource source = new DOMSource(document);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        StreamResult result = new StreamResult(in);
        transformer.transform(source, result);
	}

	/**
	 * Given a List, this static method will write an XML file accordingly.
	 * 
	 * @param list       of object to write to file
	 * @param outputPath the path of the output file (needs .xml extension)
	 */
	public static void writeList(List<Contatto> list, String outputPath) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document document = builder.newDocument();
			Element root = document.createElement("rubrica");
			document.appendChild(root);

			for (Contatto c : list) {
				Element contatto = document.createElement("contatto");

				Element nome = document.createElement("nome");
				Element cognome = document.createElement("cognome");
				Element telefono = document.createElement("telefono");
				Element email = document.createElement("email");

				nome.setTextContent(c.getNome());
				cognome.setTextContent(c.getCognome());
				telefono.setTextContent(c.getCell());
				email.setTextContent(c.getEmail());

				contatto.appendChild(nome);
				contatto.appendChild(cognome);
				contatto.appendChild(telefono);
				contatto.appendChild(email);

				root.appendChild(contatto);

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(document);

				StreamResult result = new StreamResult(new File(outputPath));
				
				transformer.transform(source, result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}