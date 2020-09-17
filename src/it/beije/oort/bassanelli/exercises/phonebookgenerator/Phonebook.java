package it.beije.oort.bassanelli.exercises.phonebookgenerator;

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

}
