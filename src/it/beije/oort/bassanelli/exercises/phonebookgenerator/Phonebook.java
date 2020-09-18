package it.beije.oort.bassanelli.exercises.phonebookgenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Phonebook {

	public static List<Contact> readCsvFile(String filepath) throws IOException {

		File file = new File(filepath);

		return readCsvFile(file);

	}

	public static List<Contact> readCsvFile(File file) throws IOException {

		List<Contact> list = new ArrayList<Contact>();

		if (file.exists()) {
			FileReader fileReader = new FileReader(file);

			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String[] fields = bufferedReader.readLine().split(";");

			while (bufferedReader.ready()) {

				Contact contact = new Contact();

				String[] values = bufferedReader.readLine().split(";");

				for (int i = 0; i < fields.length; i++) {
					switch (fields[i]) {
					case "NOME":
						contact.setName(values[i]);
						break;
					case "COGNOME":
						contact.setSurname(values[i]);
						break;
					case "TELEFONO":
						contact.setMobile(values[i]);
						break;
					case "EMAIL":
						contact.setEmail(values[i]);
						break;
					}
				}

				list.add(contact);

			}

			bufferedReader.close();

			System.out.println("File CSV loaded!");

		} else {

			System.out.println("File CSV not exists!");

		}

		return list;
	}

	public static void writeCsvFile(List<Contact> list, String pattern, String filepath, boolean useRandom) throws IOException {

		File file = new File(filepath);

		writeCsvFile(list, pattern, file, useRandom);

	}

	public static void writeCsvFile(List<Contact> list, String pattern, File file, boolean useRandom) throws IOException {

		if (file.exists()) {

			FileWriter fileWriter = new FileWriter(file);
			Random r = new Random();

			String[] fields = pattern.split(";");

			fileWriter.write(pattern + "\n");

			for (Contact contact : list) {

				StringBuilder builder = new StringBuilder();

				for (int i = 0; i < fields.length; i++) {

					
					if(useRandom) {
						switch (fields[i]) {
						case "NOME":
							
							if(r.nextInt(5) + 1 == 1) {
								builder.append(contact.getName());
							}
							
							break;
						case "COGNOME":
							
							if(r.nextInt(3) + 1 == 1) {
								builder.append(contact.getSurname());
							}
							
							break;
						case "TELEFONO":
							builder.append(contact.getMobile());
							break;
						case "EMAIL":
							builder.append(contact.getEmail());
							break;
						}
					} else {
						switch (fields[i]) {
						case "NOME":
							builder.append(contact.getName());
							break;
						case "COGNOME":
							builder.append(contact.getSurname());
							break;
						case "TELEFONO":
							builder.append(contact.getMobile());
							break;
						case "EMAIL":
							builder.append(contact.getEmail());
							break;
						}
					}
					
					builder.append(";");

				}

				builder.deleteCharAt(builder.length() - 1);
				builder.append("\n");

				fileWriter.write(builder.toString());
			}

			fileWriter.flush();
			fileWriter.close();

			System.out.println("File CSV saved!");

		} else {
			System.out.println("File CSV not exists!");
		}

	}

	public static List<Contact> readXmlFile(String filepath)
			throws ParserConfigurationException, SAXException, IOException {

		File file = new File(filepath);

		return readXmlFile(file);

	}

	public static List<Contact> readXmlFile(File file) throws ParserConfigurationException, SAXException, IOException {

		List<Contact> list = new ArrayList<Contact>();

		if (file.exists()) {

			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();

			Document document = documentBuilder.parse(file);
			Element element = document.getDocumentElement();

			NodeList nodes = element.getChildNodes();

			for (int i = 0; i < nodes.getLength(); i++) {

				Node node = nodes.item(i);

				if (node instanceof Element) {
					Element elementContact = (Element) node;
					Contact contact = new Contact();

					NodeList values = elementContact.getChildNodes();

					for (int x = 0; x < values.getLength(); x++) {
						Node n = values.item(x);

						if (n instanceof Element) {

							Element value = (Element) n;

							switch (value.getTagName()) {
							case "nome":
								contact.setName(value.getTextContent());
								break;
							case "cognome":
								contact.setSurname(value.getTextContent());
								break;
							case "telefono":
								contact.setMobile(value.getTextContent());
								break;
							case "email":
								contact.setEmail(value.getTextContent());
								break;
							default:
								System.out.println("elemento in contatto non riconosciuto");
								break;
							}
						}
					}

					list.add(contact);

				}
			}

			System.out.println("File XML loaded!");

		} else {

			System.out.println("File XML not exists!");

		}

		return list;

	}

	public static void writeXmlFile(List<Contact> list, String filepath)
			throws ParserConfigurationException, TransformerException {

		File file = new File(filepath);

		writeXmlFile(list, file);

	}

	public static void writeXmlFile(List<Contact> list, File file)
			throws ParserConfigurationException, TransformerException {

		if (file.exists()) {

			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();

			Document document = documentBuilder.newDocument();
			Element rootElement = document.createElement("rubrica");
			document.appendChild(rootElement);

			int count = 0;

			for (Contact contact : list) {

				Element contactElement = document.createElement("contatto");

//	        	contatto.setAttribute("nome", c.getNome());
//	        	contatto.setAttribute("cognome", c.getCognome());
//	        	contatto.setAttribute("telefono", c.getTelefono());
//	        	contatto.setAttribute("email", c.getEmail());

				contactElement.setAttribute("id", Integer.toString(count++));

				Element nameElement = document.createElement("nome");
				Element surnameElement = document.createElement("cognome");
				Element mobileElement = document.createElement("telefono");
				Element emailElement = document.createElement("email");

				nameElement.setTextContent(contact.getName());
				surnameElement.setTextContent(contact.getSurname());
				mobileElement.setTextContent(contact.getMobile());
				emailElement.setTextContent(contact.getEmail());

				contactElement.appendChild(nameElement);
				contactElement.appendChild(surnameElement);
				contactElement.appendChild(mobileElement);
				contactElement.appendChild(emailElement);

				rootElement.appendChild(contactElement);

			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);

			StreamResult result = new StreamResult(file);

			transformer.transform(source, result);

			System.out.println("File XML saved!");

		} else {

			System.out.println("File XML not exists!");

		}
	}

	public static List<String> extractField(List<Contact> list, String field) {
		
		List<String> newList = new ArrayList<String>();
		
		for(Contact contact: list) {
			
			switch(field) {
			case "NOME":
				newList.add(contact.getName());
				break;
			case "COGNOME":
				newList.add(contact.getSurname());
				break;
			case "TELEFONO":
				newList.add(contact.getMobile());
				break;
			case "EMAIL":
				newList.add(contact.getEmail());
				break;
			}
			
		}
		
		return newList;
		
	}
	
	public static void updateFile(String source, String dest) throws IOException, ParserConfigurationException, SAXException, TransformerException {
		
		File fileSource = new File(source);
		File fileDest = new File(dest);
		
		updateFile(fileSource, fileDest);
		
	}
	
	public static void updateFile(File source, File dest) throws IOException, ParserConfigurationException, SAXException, TransformerException {
		
		if(source.exists() && dest.exists()) {
			
			List<Contact> sourceList;
			List<Contact> destList;
			
			if(source.getPath().endsWith("csv")) {
				
				sourceList = readCsvFile(source);
				destList = readCsvFile(dest);
				
				List<Contact> finalList = new ArrayList<Contact>(sourceList);
				finalList.addAll(destList);
				
				
				writeCsvFile(finalList, "NOME;COGNOME;TELEFONO;EMAIL", dest, false);
				
				System.out.println("Files source or dest not exists!");
				
				
			} else /* if(source.getPath().endsWith("xml")) */ {
				
				sourceList = readXmlFile(source);
				destList = readXmlFile(dest);
				
				List<Contact> finalList = new ArrayList<Contact>(sourceList);
				finalList.addAll(destList);
				
				
				writeXmlFile(finalList, dest);
				
				System.out.println("File dest update!");
				
			}
			
			
		} else {
			
			System.out.println("Files source or dest not exists!");
			
		}
		
	}
}
