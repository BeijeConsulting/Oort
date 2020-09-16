package it.beije.oort.lauria;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

import it.beije.oort.rubrica.Contatto;

public class ReaderCsv {
	private static final String PATH_FILES = "C:\\Users\\Padawan06\\Documenti\\temp\\";
	
	public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException {
		File file = new File(PATH_FILES + "rubrica_gregori.csv");
		
		List<Contatto> listaContatti = csvReaderIntestazione(file);
		for(int i = 0; i < listaContatti.size(); i++)
			System.out.println(listaContatti.get(i));
		
		writeContatti(listaContatti, PATH_FILES+"contatti.xml");
		
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
			System.out.print(campi0[i]+" ");
//			System.out.println(bufferedReader.readLine());
//			if(campi[i].equalsIgnoreCase("nome")) {
//				prova.setNome(campi[i]);
//			}
		}
		System.out.println();
			
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

	
	
}
	
