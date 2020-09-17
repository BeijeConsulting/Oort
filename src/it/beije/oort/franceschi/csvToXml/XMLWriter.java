package it.beije.oort.franceschi.csvToXml;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Utility class. Currently used only for a single method.
 * 
 * @author Alessio Franceschi
 *
 */
public class XMLWriter{

	/**
	 * Given a List, this static method will write an XML file accordingly.
	 * 
	 * @param list of object to write to file
	 * @param outputPath the path of the output file (needs .xml extension)
	 */
	public static void writeList(List<ContattoBean> list, String outputPath) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document document = builder.newDocument();
			Element docElement = document.createElement("rubrica");
			document.appendChild(docElement);

			for (ContattoBean c : list) {
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

				docElement.appendChild(contatto);

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