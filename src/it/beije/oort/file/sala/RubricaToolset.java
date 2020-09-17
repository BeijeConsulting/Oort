package it.beije.oort.file.sala;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
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



public class RubricaToolset {
	
	public static List<Contatto> readCsvToList(String filePath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String[] intestazione = br.readLine().split(";");
		int a=5, b=5, c=5, d=5;
		for(int i=0; i<4; i++) {
			switch(intestazione[i].toUpperCase())
			{
				case "NOME":
					a=i;
					break;
				case "COGNOME":
					b=i;
					break;
				case "TELEFONO":
					c=i;
					break;
				case "EMAIL":
					d=i;
					break;
			}
		}
		
		List<Contatto> contatti = new ArrayList<Contatto>();
		while(br.ready()) {
			String[] temp = br.readLine().split(";");
			contatti.add(new Contatto(temp[a], temp[b], temp[c], temp[d]));
		}
		br.close();
		return contatti;
	
	}

	public static List<Contatto> readXmlToList(String filePath) 
			throws IOException, ParserConfigurationException, SAXException{
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    Document document = builder.parse(filePath);
	    Element element = document.getDocumentElement();
	    
	    List<Contatto> rubrica = new ArrayList<Contatto>();
	    NodeList contatti = element.getChildNodes();
	    for (int i = 0; i < contatti.getLength(); i++) {
	    	Node node = contatti.item(i);
	    	if (node instanceof Element) {
	        	Element contatto = (Element) node;
	        	Contatto beanContatto = new Contatto();
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
	            rubrica.add(beanContatto);
	    	}
	    }
	    return rubrica;
	}

	public static void contattoToXml (List<Contatto> list, String xmlPath) throws IOException {
		BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(xmlPath, true), StandardCharsets.UTF_8));
		bf.write("<contatti>");
		for(Contatto c : list) {
			bf.write(c.toXml());
		}
		bf.write("</contatti>");
		bf.close();
	}
	
	public static void contattoToXml (Contatto contatto, String xmlPath) throws IOException {
		List<Contatto> temp = new ArrayList<Contatto>();
		temp.add(contatto);
		contattoToXml(temp, xmlPath);
	}

	public static void contattoToCsv (List<Contatto> list, String csvPath) throws IOException {
		BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(csvPath, true), StandardCharsets.UTF_8));
		bf.write("NOME;COGNOME;TELEFONO;EMAIL;\n");
		for(Contatto c : list) {
			bf.write(c.toCsvSimple());
		}
		bf.close();
	}
	
	public static void contattoToCsv (Contatto contatto, String csvPath) throws IOException {
		List<Contatto> temp = new ArrayList<Contatto>();
		temp.add(contatto);
		contattoToCsv(temp, csvPath);
	}
}
