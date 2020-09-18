package csvandxml;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import phonebookgenerator.Contact;

import static csvandxml.CsvToXml.getContent;
import static csvandxml.CsvToXml.listSplitter;
import static csvandxml.CsvToXml.stringToArrayList;
import static csvandxml.XmlToCsv.*;
public class CsvXmlOverwriter {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		File file1 = new File("./contacts.xml");
		String file1Extension = recognizeExtension(file1);
		File file2 = new File("/temp/records.csv");
		String file2Extension = recognizeExtension(file2);
		System.out.println(file2Extension);
		if(file1Extension == ".xml") {
			List<ContactXml> contactsBook1 = readContacts(file1);
		}
		
	}
	
	public static String recognizeExtension (File file) {
		String extension = "";
		String fileName = file.getName();
		if(fileName.contains(".csv")) {
			extension = ".csv";
		} 
		else if (fileName.contains(".xml")) {
			extension = ".xml";
		}
		else {
			System.out.println("extension not recognized");
		}
		return extension;
	}
	
	public static List<ContactXml> xmlToContact (File file) throws Exception {
		List<ContactXml> contactsBook = readContacts(file);
		return contactsBook;
	}
	
	public static List<Contact> organizeCsv (File file) throws Exception {
		String fileToString = getContent(file);
//		System.out.println(fileToString);
		String header = fileToString.substring(0,fileToString.indexOf("\n"));
		String body = fileToString.substring(fileToString.indexOf("\n") + 1);
//		System.out.println(header);
		List<String> firstArray = stringToArrayList(header);
		List<String> dataArray = stringToArrayList(body);
//		System.out.println(dataArray);
		int namePosition = firstArray.indexOf("NOME");
		int surnamePosition = firstArray.indexOf("COGNOME");
		int mobilePosition = firstArray.indexOf("TELEFONO");
		int emailPosition = firstArray.indexOf("EMAIL");
//		System.out.println(namePosition);
//		System.out.println(dataArray.get(surnamePosition));
		List<String> namesList = listSplitter(dataArray, namePosition);
		List<String> surnamesList = listSplitter(dataArray, surnamePosition);
		List<String> mobileList = listSplitter(dataArray, mobilePosition);
		List<String> emailList = listSplitter(dataArray, emailPosition);
		
		List<Contact> contacts = new ArrayList<Contact>();
		for (int i = 0; i < namesList.size(); i++) {
			Contact contact = new Contact();
			contact.setName(namesList.get(i));
			contact.setSurname(surnamesList.get(i));
			contact.setMobile(mobileList.get(i));
			contact.setEmail(emailList.get(i));
//			System.out.println(contact);
			contacts.add(contact);
		}
		
		return contacts;
	}
	
	public static void getContacts(File file) throws Exception {
		String fileExtension = recognizeExtension(file);
		if(fileExtension == ".xml") {
			List<ContactXml> contactsXml = readContacts(file);
		}
		if(fileExtension == ".csv" ) {
			List<Contact> contactCsv = organizeCsv(file);
		}
	}
}
