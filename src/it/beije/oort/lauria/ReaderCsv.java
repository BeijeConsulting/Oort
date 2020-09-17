package it.beije.oort.lauria;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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


public class ReaderCsv {
	private static final String PATH_FILES = "C:\\Users\\Padawan06\\Documenti\\temp\\";
	
	public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException, SAXException {
		
		File file = new File(PATH_FILES + "rubrica_gregori.csv");
		
		List<Contatto> listaContattiXml = csvReaderIntestazione(file);
		
		writeContattiXml(listaContattiXml, PATH_FILES+"contatti.xml");
		
		System.out.println("Rubrica XML completata!");
		
		// adesso voglio trasformare contatti.xml in fromato csv
		
		List<Contatto> listaContattiCsv = readContatti(PATH_FILES+"contatti.xml");
		
		
		File output = new File(PATH_FILES + "rubricaCsv.csv");
		FileWriter writer = new FileWriter(output);
		
		writer.write("COGNOME;NOME;TELEFONO;E-MAIL");
		for (Contatto contattoTemp : listaContattiCsv) {
			writer.write("\n");
			writer.write(ReaderCsv.costruisciRiga(contattoTemp));
		}
		
		writer.flush();
		writer.close();
		System.out.println("Rubrica CSV completata!");
		
	}
	
	public static List<Contatto> csvReaderIntestazione(File file) throws IOException{
		List<Contatto> arrayContatti = new ArrayList<Contatto>();
		
		FileReader fileReader = new FileReader(file);			
		BufferedReader bufferedReader = new BufferedReader(fileReader);	
		
		Contatto contatto = new Contatto();
		
		//System.out.println(bufferedReader.readLine());
		String riga0 = bufferedReader.readLine();
		String[] campi0 = riga0.split(";");
		for(int i = 0; i<campi0.length; i++) {
//			System.out.print(campi0[i]+" ");
//			System.out.println(bufferedReader.readLine());
//			if(campi[i].equalsIgnoreCase("nome")) {
//				prova.setNome(campi[i]);
//			}
		}
//		System.out.println();
			
		String Nome ="", Cognome="", Telefono="", Mail="";
	
		while (bufferedReader.ready()) {	
			
			String riga = bufferedReader.readLine();
			//System.out.println(riga);
			String[] campiriga = riga.split(";");
	
			for(int i = 0; i<campi0.length; i++) {
				//System.out.print(" "+campiriga[i]);
				switch(campi0[i].toLowerCase()) {
				case "nome": Nome = campiriga[i]; break;
				case "cognome": Cognome=campiriga[i]; break;
				case "telefono": Telefono=campiriga[i]; break;
				case "email": Mail = campiriga[i]; break;
				}
				
				contatto = new Contatto(Nome, Cognome, Telefono, Mail);
				
			}
	
			arrayContatti.add( contatto);
		}			
		
		fileReader.close();
		bufferedReader.close();
		
		return arrayContatti;
	}
	
	
	
	
	
	public static void writeContattiXml(List<Contatto> contatti, String pathfile) throws ParserConfigurationException, TransformerException {
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
        
        //System.out.println("### -> " + docElement.getChildNodes().getLength());
        
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		
		StreamResult result = new StreamResult(new File(pathfile));

		// Output to console for testing
		//StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);

		//System.out.println("File saved!");
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
        //System.out.println(element.getTagName());
        
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
                		//System.out.println(valore.getTagName() + " : " + valore.getTextContent());
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
                //System.out.println(beanContatto);
                rubrica.add(beanContatto);
        	}
        }
        
        return rubrica;
        
	}	
	
	// scrittura record sui file
	private static String costruisciRiga(Contatto contatto) {
		return costruisciRiga(contatto.getCognome(), contatto.getNome(),  contatto.getTelefono(), contatto.getEmail());
	}
	
	private static String costruisciRiga(String... campi) {
		StringBuilder riga = new StringBuilder();
		for(String campo : campi) {
			riga.append(campo).append(';');
		}
		riga.deleteCharAt(riga.length() - 1);
		return riga.toString();
	}
	
}
	
