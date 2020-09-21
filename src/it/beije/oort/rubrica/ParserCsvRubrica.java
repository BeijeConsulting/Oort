package it.beije.oort.rubrica;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class ParserCsvRubrica {
	
	public ArrayList<Contatto> contatti;
	
	public List<Contatto> listBuilder(File file) throws IOException {
		FileReader fileReader = new FileReader(file);
     	BufferedReader bufferedReader = new BufferedReader(fileReader);
		String intestazione = bufferedReader.readLine();
		List<String> intestazioneList = Arrays.asList(intestazione.split(";"));
		List<Contatto> contatti = new ArrayList<Contatto>();
		while(bufferedReader.ready()) {
			contatti.add(creaContatto(bufferedReader.readLine().split(";",-1), intestazioneList));
		}
		bufferedReader.close();
		return contatti;
	}
	
	
	public Contatto creaContatto(String[] a, List<String> list) {
		Contatto contatto = new Contatto();
		for(int i=0;i<list.size();i++) {
			if(list.get(i).equalsIgnoreCase("nome")) contatto.setNome(a[i]);
			else if(list.get(i).equalsIgnoreCase("cognome")) contatto.setCognome(a[i]);
			else if(list.get(i).equalsIgnoreCase("email")) contatto.setEmail(a[i]);
			else if(list.get(i).equalsIgnoreCase("telefono")) contatto.setTelefono(a[i]);	
		}
		return contatto;
	}
	
	public void rubricaXmlWriter(List<Contatto> list, String pathfile) throws ParserConfigurationException, TransformerException, DOMException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        Element docElement = document.createElement("rubrica");
        document.appendChild(docElement);
        
        for (Contatto c : list) {
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
        
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(new File(pathfile));

		transformer.transform(source, result);
	}
	
	
	public static void main(String[] args) throws DOMException, ParserConfigurationException, TransformerException, IOException {
		ParserCsvRubrica p = new ParserCsvRubrica();
		Random r = new Random();
		final String PATH = "./src\\it\\beije\\oort\\rubrica\\rubricaNames\\";
		String[] rubriche = {"rubrica_bassanelli", "rubrica_brugaletta", "rubrica_busseni", "rubrica_franceschi", "rubrica_girardi","rubrica_gregori","rubrica_lauria","rubrica_madonia","rubrica_maisto","rubrica_mancuso","rubrica_mater","rubrica_sala"};
		int b=r.nextInt(12); //variabile dichiarata nel caso debba utilizzare il system.out
		File a = new File(PATH + rubriche[b]+".csv");
//		System.out.println(rubriche[b]); //per controllare quale rubrica stia utilizzando
		p.rubricaXmlWriter(p.listBuilder(a),PATH +"rubrica.xml");
	}
}

		


