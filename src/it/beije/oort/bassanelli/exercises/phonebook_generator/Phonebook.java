package it.beije.oort.bassanelli.exercises.phonebook_generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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

	public static void printAllRecords(List<Contact> list) {

		for (Contact contact : list) {
			System.out.println(contact.toString());
		}

	}

	public static void printAllRecords(List<Contact> list, String pattern) {

		for (Contact contact : list) {
			System.out.println(contact.toString(pattern));
		}

	}

	public static List<Contact> recordsGenerator(int total, List<String> namesList, List<String> surnamesList,
			List<String> prefixsList, List<String> mobilesList, List<String> domainsList, List<String> emailsList) {

		List<Contact> recordsList = new ArrayList<Contact>();

		Random r = new Random();
		String name = "";
		String surname = "";
		String mobile = "";
		String domain = "";
		String email = "";

		for (int i = 0; i < total; i++) {

			Contact contact = new Contact();

			name = nameGenerator(namesList.get(r.nextInt(namesList.size())));

			surname = surnameGenerator(surnamesList.get(r.nextInt(surnamesList.size())));

			mobile = randomDiceMobile(prefixsList, mobilesList);

			domain = domainsList.get(r.nextInt(domainsList.size()));

			email = randomDiceEmail(name, surname, domain, emailsList);

			contact.setName(name);
			contact.setSurname(surname);
			contact.setMobile(mobile);
			contact.setEmail(email);

			// System.out.println(contact.toString());

			recordsList.add(contact);

		}

		return recordsList;

	}

	public static String nameGenerator(String name) {
		name = toUpper(name.trim());
		return name;
	}

	public static String surnameGenerator(String surname) {
		surname = toUpper(surname.trim());
		return surname;
	}

	public static String toUpper(String str) {
		StringBuilder finalWord = new StringBuilder();
		finalWord.append(str.substring(0, 1).toUpperCase()).append(str.substring(1));
		return finalWord.toString();
	}

	public static String randomDiceMobile(List<String> prefixList, List<String> mobileList) {
		Random r = new Random();

		int randomNumber = r.nextInt(8) + 1;

		StringBuilder mobile = new StringBuilder();

		if (randomNumber == 1) {

			return mobile.toString();

		} else if (randomNumber == 2) {

			mobile.append(mobileList.get(r.nextInt(mobileList.size())));
			return mobile.toString();

		} else if (randomNumber == 3 || randomNumber == 4) {

			mobile.append("+39").append(mobileGenerator(prefixList));
			return mobile.toString();

		} else {

			mobile.append(mobileGenerator(prefixList));
			return mobile.toString();

		}
	}

	public static String mobileGenerator(List<String> prefixList) {
		Random r = new Random();

		char[] numbers = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };

		StringBuilder mobile = new StringBuilder(prefixList.get(r.nextInt(5)));

		for (int i = 0; i < 7; i++) {
			mobile.append(numbers[r.nextInt(numbers.length)]);
		}

		return mobile.toString();
	}

	public static String randomDiceEmail(String name, String surname, String domain, List<String> emailList) {
		Random r = new Random();

		int randomNumber = r.nextInt(10) + 1;

		StringBuilder email = new StringBuilder("");

		if (randomNumber == 1 || randomNumber == 2) {

			return email.toString();

		} else if (randomNumber >= 3 && randomNumber <= 5) {

			email.append(emailList.get(r.nextInt(emailList.size())));
			return email.toString();

		} else {

			email.append(emailGenerator(name, surname, domain));
			return email.toString();

		}
	}

	public static String emailGenerator(String name, String surname, String domain) {
		Random r = new Random();

		StringBuilder email = new StringBuilder();

		boolean nameEmpty = false;
		boolean surnameEmpty = false;

		String separetor = "";
		String prefixNumber = "";

		if (r.nextInt(5) + 1 == 1) {
			name = "";
			nameEmpty = true;
		}
		if (r.nextInt(3) + 1 == 1) {
			surname = "";
			surnameEmpty = true;
		}

		// mancano o il nome o il cognome
		if (nameEmpty ^ surnameEmpty) {

			if (r.nextInt(10) + 1 != 1) {

				int num = r.nextInt(100);
				prefixNumber = (num > 10 ? "" + num : "0" + num);

			}
		}

		// mancano il nome e il cognome
		if (nameEmpty && surnameEmpty) {
			int randomLength = r.nextInt((20 - 6) + 1) + 6;

			for (int i = 0; i < randomLength; i++) {
				char c = (char) (r.nextInt((122 - 97) + 1) + 97);
				name += c;
			}

		}

		// presente il nome e il cognome
		if (!nameEmpty && !surnameEmpty) {

			int num = r.nextInt(10) + 1;

			if (num == 1 || num == 2) {
				name = name.substring(0, 1);
			} else if (num >= 3 && num <= 5) {
				for (int i = 0; i < name.length(); i++) {
					switch (name.charAt(i)) {
					case 'a':
					case 'e':
					case 'i':
					case 'o':
					case 'u':
						name = name.substring(0, i + 1);
						i = name.length();
						break;
					default:
						break;
					}
				}
			}

			num = r.nextInt(4) + 1;

			if (num == 1) {
				String temp = name;
				name = surname;
				surname = temp;
			}

			num = r.nextInt(10) + 1;

			if (num >= 1 && num <= 3) {
				separetor = "";
			} else if (num >= 4 && num <= 7) {
				separetor = ".";
			} else {
				separetor = "-";
			}

			num = r.nextInt(5) + 1;

			if (num == 1) {
				for (int i = 0; i < name.length(); i++) {
					switch (name.charAt(i)) {
					case 'a':
						name = name.replace('a', '4');
						break;
					case 'e':
						name = name.replace('e', '3');
						break;
					case 'i':
						name = name.replace('i', '1');
						break;
					case 'o':
						name = name.replace('o', '0');
						break;
					default:
						break;
					}
				}

				for (int i = 0; i < surname.length(); i++) {
					switch (surname.charAt(i)) {
					case 'a':
						surname = surname.replace('a', '4');
						break;
					case 'e':
						surname = surname.replace('e', '3');
						break;
					case 'i':
						surname = surname.replace('i', '1');
						break;
					case 'o':
						surname = surname.replace('o', '0');
						break;
					default:
						break;
					}
				}
			}
		}

		email.append(name.replace(" ", "_")).append(separetor).append(surname.replace(" ", "_")).append(prefixNumber)
				.append("@").append(domain);

		return email.toString().toLowerCase();
	}

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

				String[] values = bufferedReader.readLine().split(";", -1);

				for (int i = 0; i < fields.length; i++) {

					switch (fields[i].toUpperCase()) {
					case "ID":
						contact.setId(values[i]);
						break;
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
					case "ALIAS":
						contact.setAlias(Arrays.asList(values[i].split(",", -1)));
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

	public static void writeCsvFile(List<Contact> list, String pattern, String filepath, boolean isRandom)
			throws IOException {

		File file = new File(filepath);

		writeCsvFile(list, pattern, file, isRandom);

	}

	public static void writeCsvFile(List<Contact> list, String pattern, File file, boolean isRandom)
			throws IOException {

		if (file.exists()) {

			FileWriter fileWriter = new FileWriter(file);

			fileWriter.write(pattern + "\n");

			for (Contact contact : list) {

				fileWriter.write(contact.toCsvRow(pattern, isRandom));
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
							case "alias":
								contact.setAlias(Arrays.asList(value.getTextContent().split(",", -1)));
								break;
							default:
								System.out.println("Contact element not recognized");
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

			for (Contact contact : list) {

				Element contactElement = document.createElement("contatto");

//	        	contatto.setAttribute("nome", c.getNome());
//	        	contatto.setAttribute("cognome", c.getCognome());
//	        	contatto.setAttribute("telefono", c.getTelefono());
//	        	contatto.setAttribute("email", c.getEmail());

				contactElement.setAttribute("id", String.valueOf(contact.getId()));

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

		for (Contact contact : list) {

			switch (field.toUpperCase()) {
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

	public static void updateFile(String source, String dest)
			throws IOException, ParserConfigurationException, SAXException, TransformerException {

		File fileSource = new File(source);
		File fileDest = new File(dest);

		updateFile(fileSource, fileDest);

	}

	public static void updateFile(File source, File dest)
			throws IOException, ParserConfigurationException, SAXException, TransformerException {

		if (source.exists() && dest.exists()) {

			List<Contact> sourceList;
			List<Contact> destList;

			if (source.getPath().endsWith("csv")) {

				sourceList = readCsvFile(source);
				destList = readCsvFile(dest);

				List<Contact> finalList = new ArrayList<Contact>(sourceList);
				finalList.addAll(destList);

				writeCsvFile(finalList, "NOME;COGNOME;TELEFONO;EMAIL", dest, false);

				System.out.println("File dest updated!");

			} else /* if(source.getPath().endsWith("xml")) */ {

				sourceList = readXmlFile(source);
				destList = readXmlFile(dest);

				List<Contact> finalList = new ArrayList<Contact>(sourceList);
				finalList.addAll(destList);

				writeXmlFile(finalList, dest);

				System.out.println("File dest updated!");

			}

		} else {

			System.out.println("Files source or dest not exists!");

		}
	}

	public static void sortByField(List<Contact> list, String field) {

		if (list.size() > 0) {
			Collections.sort(list, new Comparator<Contact>() {

				@Override
				public int compare(final Contact contactObject1, final Contact contactObject2) {

					switch (field.toUpperCase()) {
					case "ID":
						return Integer.valueOf(contactObject1.getId())
								.compareTo(Integer.valueOf(contactObject2.getId()));
					default:
					case "NOME":
					case "NAME":
						return contactObject1.getName().compareTo(contactObject2.getName());
					case "COGNOME":
					case "SURNAME":
						return contactObject1.getSurname().compareTo(contactObject2.getSurname());
					case "TELEFONO":
					case "MOBILE":
						return contactObject1.getMobile().compareTo(contactObject2.getMobile());
					case "EMAIL":
						return contactObject1.getEmail().compareTo(contactObject2.getEmail());
					}
				}
			});
		}
	}
}
