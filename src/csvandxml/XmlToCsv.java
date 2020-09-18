package csvandxml;
import phonebookgenerator.*;
import static phonebookgenerator.PhonebookGenerator.listGenerator;
import static phonebookgenerator.PhonebookGenerator.mailGenerator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import it.beije.oort.rubrica.Contatto;

import static csvandxml.CsvToXml.getContent;

public class XmlToCsv {
	public static void main(String[] args) throws Exception {
		System.out.println("Start: " + LocalTime.now());
		File fileXml = new File("./contacts.xml");
		List<ContactXml> contactsBook = readContacts(fileXml);
		List<String> namesList = new ArrayList<String>();
		List<String> surnamesList = new ArrayList<String>();
		List<String> mobileList = new ArrayList<String>();
		List<String> emailList = new ArrayList<String>();
		List<String> domainsList = listGenerator("./domini.txt");
		
		String name = "";
		String surname = "";
		String mobile = "";
		String email = "";

//		System.out.println(contactsBook);

		File fileCsv = new File("/temp/csvfromxml.csv");
		FileWriter writer = new FileWriter(fileCsv);
		writer.write("COGNOME;NOME;EMAIL;TELEFONO\n");
		ContactXml contact = new ContactXml();
		for (int i = 0; i < 1000; i++) {
			contact = contactsBook.get(i);
			
			name = contact.getName();
			surname = contact.getSurname();
			mobile = contact.getMobile();
			email = contact.getEmail();
			
			
			mobile = mobileGenerator(mobileList);
//			System.out.println(mobile);
			
			email = mailGeneratorFromLists(emailList, domainsList, name, surname);
//			System.out.println(email);
			
			Random r = new Random();
			if(r.nextInt(5) == 1) {
				name = "";
			}
			if(r.nextInt(3) == 1) {
				surname = "";
			}
			
			contact.setName(name);
			contact.setSurname(surname);
			contact.setMobile(mobile);
			contact.setEmail(email);
			writer.write(contact.toRow());
//		System.out.println(contactsBook.get(i));
			// recordsList.add(record);
		}
		
		System.out.println("Done records: " + LocalTime.now());
		
		writer.flush();
		writer.close();

		System.out.println("Done file: " + LocalTime.now());
	}
	
	public static List<ContactXml> readContacts(String xmlFilepath) throws ParserConfigurationException, SAXException, IOException {
		File file = new File(xmlFilepath);
		
		return readContacts(file);
	}
	
	public static List<ContactXml> readContacts(File xmlFile) throws ParserConfigurationException, SAXException, IOException {		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
		
        // Load the input XML document, parse it and return an instance of the Document class.
        Document document = builder.parse(xmlFile);
        Element element = document.getDocumentElement();       
//        System.out.println(element.getTagName());
        
        List<ContactXml> telephoneBook = new ArrayList<ContactXml>();
        NodeList contacts = element.getChildNodes();
        //NodeList contatti = element.getElementsByTagName("contatto");
        //System.out.println(contatti.getLength());
        for (int i = 0; i < contacts.getLength(); i++) {
        	Node node = contacts.item(i);
        	if (node instanceof Element) {
            	Element contact = (Element) node;
            	ContactXml beanContact = new ContactXml();
            	NodeList values = contact.getChildNodes();
                //System.out.println(valori.getLength());
                for (int j = 0; j < values.getLength(); j++) {
                	Node n = values.item(j);
                	if (n instanceof Element) {
                		Element valore = (Element) n;
//                		System.out.println(valore.getTagName() + " : " + valore.getTextContent());
                		switch (valore.getTagName()) {
						case "nome":
							beanContact.setName(valore.getTextContent());
							break;
						case "cognome":
							beanContact.setSurname(valore.getTextContent());
							break;
						case "telefono":
							beanContact.setMobile(valore.getTextContent());
							break;
						case "email":
							beanContact.setEmail(valore.getTextContent());
							break;

						default:
							System.out.println("elemento in contatto non riconosciuto");
							break;
						}
                	}
                }
//                System.out.println(beanContact);
                telephoneBook.add(beanContact);
        	}
        }
        
        return telephoneBook;
        
	}
	
	public static String mobileGenerator (List<String> mobiles) {
		Random r = new Random();
		
		char[] numbers = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
	
		StringBuilder mobile = new StringBuilder("3");
		
		for(int i = 0; i < 9; i++) {
			mobile.append(numbers[r.nextInt(numbers.length)]);
		}
		if(r.nextInt(8) == 1) {
			mobile = new StringBuilder();
		}
		if((r.nextInt(8) == 2) && (mobiles.size() > 0) ) {
			mobile = new StringBuilder(mobiles.get(r.nextInt(mobiles.size())));
		}
		if((mobile.length() > 0) && (r.nextInt(8) == 3) || (r.nextInt(8) == 4)) {			
			mobile.insert(0, "+39");
		}
		mobiles.add(mobile.toString());
		return mobile.toString();
	}
	
	public static String mailGeneratorFromLists (List<String> emails, List<String> domainsList, String name, String surname) {
		
		Random r = new Random();
		StringBuilder mail = new StringBuilder();
		String domain = domainsList.get(r.nextInt(domainsList.size()));
		if((r.nextInt(10) == 1) || (r.nextInt(10) == 2) ) {
			mail = new StringBuilder();
		}
		if((emails.size() > 0) && (r.nextInt(10) >= 3) && (r.nextInt(10) <= 5) ) {
			mail = new StringBuilder(emails.get(r.nextInt(emails.size())));
			emails.add(mail.toString());
		}
		if((r.nextInt(10) >= 6) && (r.nextInt(10) <= 10) ) {
			mail = new StringBuilder(mailGenerator(name, surname, domain));
			emails.add(mail.toString());
		}
		return mail.toString();
	}
}
