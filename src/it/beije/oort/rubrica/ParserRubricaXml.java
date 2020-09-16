package it.beije.oort.rubrica;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class ParserRubricaXml {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
	
//		List<Contatto> rubrica = readContatti("./rubrica.xml");
//		
//        System.out.println("rubrica size : " + rubrica.size());

		Contatto contatto1 = new Contatto("Carlo", "Bianchi", "3337658231");
		Contatto contatto2 = new Contatto("Mario", "Rossi", "3337658390", "mario.rossi@tim.it");
		Contatto contatto3 = new Contatto("Federica", "Verdi", "3457238390", "f.verdi@vodafone.it");
		
		List<Contatto> contatti = new ArrayList<Contatto>();
		contatti.add(contatto1);
		contatti.add(contatto2);
		contatti.add(contatto3);

		writeContatti(contatti, "./contatti.xml");
	}
	
	public static void writeContatti(List<Contatto> contatti, String pathfile) throws ParserConfigurationException, TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.newDocument();
        Element docElement = document.createElement("contatti");
        document.appendChild(docElement);
        
        for (Contatto c : contatti) {
        	Element contatto = document.createElement("contatto");
//        	contatto.setAttribute("nome", c.getNome());
//        	contatto.setAttribute("cognome", c.getCognome());
//        	contatto.setAttribute("telefono", c.getTelefono());
//        	contatto.setAttribute("email", c.getEmail());
        	
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
        
        System.out.println("### -> " + docElement.getChildNodes().getLength());
        
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		
		StreamResult result = new StreamResult(new File(pathfile));

		// Output to console for testing
		//StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);

		System.out.println("File saved!");
	}
	
	

	public static List<Contatto> readContatti(String xmlFilepath) throws ParserConfigurationException, SAXException, IOException {
		File file = new File(xmlFilepath);
		
		return readContatti(file);
	}

	public static List<Contatto> readContatti(File xmlFile) throws ParserConfigurationException, SAXException, IOException {		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
		
        // Load the input XML document, parse it and return an instance of the Document class.
        Document document = builder.parse(xmlFile);
        Element element = document.getDocumentElement();       
        System.out.println(element.getTagName());
        
        List<Contatto> rubrica = new ArrayList<Contatto>();
        NodeList contatti = element.getChildNodes();
        //NodeList contatti = element.getElementsByTagName("contatto");
        //System.out.println(contatti.getLength());
        for (int i = 0; i < contatti.getLength(); i++) {
        	Node node = contatti.item(i);
        	if (node instanceof Element) {
            	Element contatto = (Element) node;
            	Contatto beanContatto = new Contatto();
            	NodeList valori = contatto.getChildNodes();
                //System.out.println(valori.getLength());
                for (int j = 0; j < valori.getLength(); j++) {
                	Node n = valori.item(j);
                	if (n instanceof Element) {
                		Element valore = (Element) n;
                		System.out.println(valore.getTagName() + " : " + valore.getTextContent());
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
                System.out.println(beanContatto);
                rubrica.add(beanContatto);
        	}
        }
        
        return rubrica;
        
	}

}
