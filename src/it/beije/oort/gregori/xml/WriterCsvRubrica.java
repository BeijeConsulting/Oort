package it.beije.oort.gregori.xml;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import it.beije.oort.rubrica.Contatto;

public class WriterCsvRubrica {
	public static ArrayList<String> nomi = new ArrayList<>();
	public static ArrayList<String> cognomi = new ArrayList<>();
	
	public WriterCsvRubrica() throws IOException {
		listaNomi();
		listaCognomi();
	}

	public static List<Contatto> readContatti(File xmlFile) throws ParserConfigurationException, SAXException, IOException {		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
		
        // Load the input XML document, parse it and return an instance of the Document class.
        Document document = builder.parse(xmlFile);
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
	
	public static void scritturaCsvFromXml(List<Contatto> records) throws IOException {
		File file = new File("/temp/rubrica-from-xml-to-csv.csv");
		FileWriter fileWriter = new FileWriter(file);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		
		bufferedWriter.append("COGNOME;NOME;EMAIL;TELEFONO\n");
		for(int i = 0; i < records.size(); i++) {
			String name = records.get(i).getNome();
			String surname = records.get(i).getCognome();
			String phone = records.get(i).getTelefono();
			String email = records.get(i).getEmail();
			bufferedWriter.append(surname).append(";").append(name).append(";").append(email).append(";").append(phone).append("\n");
		}
		
		bufferedWriter.flush();
		bufferedWriter.close();
	}
	
	public static void scritturaCsv(List<Contatto> records) throws IOException {
		Random random = new Random();
		File file = new File("/temp/rubrica-csv.csv");
		FileWriter fileWriter = new FileWriter(file);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		
		int randomValue1 = random.nextInt(5) + 1;
		int randomValue2 = random.nextInt(3) + 1;
		
		String name = "";
		String surname = "";
		String phone = "";
		String email = "";
		
		
		for(int i = 0; i < records.size(); i++) {
			name = records.get(i).getNome();
			surname = records.get(i).getCognome();
			phone = records.get(i).getTelefono();
			email = records.get(i).getEmail();
			
			StringBuilder sb = new StringBuilder();
			StringBuilder builder = new StringBuilder();
			sb.append(";").append(phone).append(";").append(email).append("\n");
		
			if(randomValue1 == 1) {
				builder.append(surname);
				//bufferedWriter.append(surname).append(";").append(phone).append(";").append(email).append("\n");
			}
			else {
				builder.append(name).append(surname);
				//bufferedWriter.append(name).append(";").append(surname).append(";").append(phone).append(";").append(email).append("\n");
			}
			
			if(randomValue2 == 1) {
				builder.append(name);
				//bufferedWriter.append(name).append(";").append(phone).append(";").append(email).append("\n");
			}
			else if(randomValue1 == 1){
				builder.append(name).append(surname);
				//bufferedWriter.append(name).append(";").append(surname).append(";").append(phone).append(";").append(email).append("\n");
			}	
			
			builder.append(sb);
			bufferedWriter.append(builder);
		}
		
		bufferedWriter.flush();
		bufferedWriter.close();
	}
	
	public static void listaNomi() throws IOException {
		File file = new File("/temp/lista_nomi.txt");
		if(file.exists()) {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
		
			while (bufferedReader.ready()) {
				nomi.add(bufferedReader.readLine());
			}
			
			bufferedReader.close();					
		}
		else System.out.println("Il file non esiste!");
	}
	
	public static void listaCognomi() throws IOException {
		File file = new File("/temp/lista_cognomi.txt");
		if(file.exists()) {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
		
			while (bufferedReader.ready()) {
				cognomi.add(bufferedReader.readLine());
			}
			
			bufferedReader.close();					
		}
		else System.out.println("Il file non esiste!");
	}
	
	public List<Contatto> recordsToContatti(ArrayList<String> nomi, ArrayList<String> cognomi) {
		List<Contatto> contatti = new ArrayList<>();
		final int num = 1000;
		
		for(int i = 0; i < num; i++) {
			Record record = new Record();
			Contatto contatto = new Contatto();
			record.generateNome(nomi);
			record.generateCognome(cognomi);
			record.generateTelefono();
			record.generateMail();
			
			contatto.setNome(record.getNome());
			contatto.setCognome(record.getCognome());
			contatto.setEmail(record.getMail());
			contatto.setTelefono(record.getTelefono());
			
			contatti.add(contatto);		
			System.out.println(contatto.toString());
		}
		return contatti;
	}
	
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		//File fileXml = new File("/temp/rubrica.xml");
		//WriterCsvRubrica.scritturaCsvFromXml(WriterCsvRubrica.readContatti(fileXml));
		WriterCsvRubrica wr = new WriterCsvRubrica();
		WriterCsvRubrica.scritturaCsv(wr.recordsToContatti(nomi, cognomi));
	}
	
	
	
}
