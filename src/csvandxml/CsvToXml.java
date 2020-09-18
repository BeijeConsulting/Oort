package csvandxml;
import phonebookgenerator.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class CsvToXml {

	public static void main(String[] args) throws Exception {
		System.out.println("Start: " + LocalTime.now());
		// TODO Auto-generated method stub
		File fileCsv = new File("/temp/records.csv");
		String fileToString = getContent(fileCsv);
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
//		System.out.println(namesList);
//		System.out.println(surnamesList);
//		System.out.println(mobileList);
//		System.out.println(emailList);
		
		List<Contact> contacts = new ArrayList<Contact>();
//		System.out.println(namesList.size());
		
		for (int i = 0; i < namesList.size(); i++) {
			Contact contact = new Contact();
			contact.setName(namesList.get(i));
			contact.setSurname(surnamesList.get(i));
			contact.setMobile(mobileList.get(i));
			contact.setEmail(emailList.get(i));
//			System.out.println(contact);
			contacts.add(contact);
		}
		
		System.out.println(contacts);
		
		writeContatti(contacts, "./contacts.xml");
//		System.out.println(firstArray);
		
		
		
		
		System.out.println("Done records: " + LocalTime.now());
	}
	
	private static void writeContatti(List<Contact> contacts, String pathfile) throws ParserConfigurationException, TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.newDocument();
        Element docElement = document.createElement("contacts");
        document.appendChild(docElement);
        
        for (Contact c : contacts) {
        	Element contact = document.createElement("contact");
       	
        	Element name = document.createElement("nome");
        	Element surname = document.createElement("cognome");
        	Element mobile = document.createElement("telefono");
        	Element email = document.createElement("email");
        	
        	name.setTextContent(c.getName());
        	surname.setTextContent(c.getSurname());
        	mobile.setTextContent(c.getMobile());
        	email.setTextContent(c.getEmail());
        	
        	contact.appendChild(name);
        	contact.appendChild(surname);
        	contact.appendChild(mobile);
        	contact.appendChild(email);

        	docElement.appendChild(contact);
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

	public static String getContent(File file) throws IOException {
		FileReader fileReader = new FileReader(file);
		StringBuilder builder = new StringBuilder();
		
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		while (bufferedReader.ready()) {
			builder.append(bufferedReader.readLine()).append('\n');
		}
		
		return builder.toString();
	}
	
	public static List<String> listSplitter(List<String> list, int index) throws Exception {
		List<String> campi = new ArrayList<String>();
		for (int i = index; i < list.size(); i+=4) {
			campi.add(list.get(i));
		}
		return campi;
	}
	
	public static List<String> stringToArrayList (String str) {
		List<String> arraylist = new ArrayList<String>();
		int firstIndex = 0;
		int lastIndex = 0;
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == ';') {
				lastIndex = i;
				arraylist.add(str.substring(firstIndex, lastIndex));
				firstIndex = lastIndex + 1;
			}
			if(str.charAt(i) == '\n') {
				lastIndex = i;
				arraylist.add(str.substring(firstIndex, lastIndex));
				firstIndex = lastIndex + 1;
			}
			if(i == (str.length() - 1)) {
				arraylist.add(str.substring(firstIndex, i + 1));
			}
			
		}
		return arraylist;
	}
	

}
