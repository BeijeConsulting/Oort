package it.beije.oort.lauria;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

/**
 * Libreria di metodi per aggiungere il contenuto di un file .csv/.xml,
 * in coda ad un altro file .csv/.xml .	
 * Librerie usate : RubricaBuilder, FileReaderWriter, CsvParser, XmlParser
 * @author Padawan06
 *
 */
public class AddCsvXml {
	
	// ATTENZIONE: modificare l'INTESTAZIONE comporta modificare l'ordine di scrittura dei campi 
	//			   dei contatti nel metodo rowBuilderCsv(Contatto contatto) di RubricaBuilder.
	private static final String INTESTAZIONE = "COGNOME;NOME;E-MAIL;TELEFONO";
		
	/**
	 * Metodo per aggiungere il contenuto di fileIn.csv, in coda al contenuto di fileOut.csv.
	 * @param fileIn File in formato .csv
	 * @param fileOut File in formato .csv
	 * @throws IOException 
	 */
	public static void csvAddCsv(File fileIn, File fileOut) throws IOException {
		if(!fileOut.exists()){
			//fileOut.createNewFile();
			FileReader fileReader = new FileReader(fileIn);			
			BufferedReader bufferedReader = new BufferedReader(fileReader);	
			String firstRow = bufferedReader.readLine();
			FileReaderWriter.writeRow(fileOut, firstRow);
			bufferedReader.close();
		}
		
		//LEGGO IL FILE CSV
		StringBuilder content = new StringBuilder();
		content.append(FileReaderWriter.getContent(fileOut));
		
		// ELIMINO PRIMA RIGA DEL FILE INPUT
		StringBuilder contentIn = new StringBuilder();
		contentIn.append(FileReaderWriter.getContent(fileIn));
		contentIn.delete(0, FileReaderWriter.getContent(fileIn).indexOf('\n')+1);
	
		//AGGIUNGO IL CONTENUTO DEL FILE DI INPUT IN QUELLO DI OUTPUT
		content.append(contentIn.toString());
		FileReaderWriter.writeRow(fileOut, content.toString());

	}
	
	/**
	 * Metodo per aggiungere il contenuto di fileIn.csv, in coda al contenuto di fileOut.xml.
	 * @param fileIn File in formato .csv
	 * @param fileOut File in formato .xml
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws TransformerException
	 */
	public static void csvAddXml(File fileIn, File fileOut) throws IOException, ParserConfigurationException, SAXException, TransformerException {
		
		List<Contatto> listaContattiCsv = CsvParser.readContattiCsv(fileIn);		
		
		if(!fileOut.exists()){
			fileOut.createNewFile();
			XmlParser.writeContattiXml(fileOut, listaContattiCsv);	
		}else{
			//PRELEVO LISTA CONTATTI DA CSV E DA XML
			List<Contatto> listaContattiXml = XmlParser.readContattiXml(fileOut);
			listaContattiXml.addAll(listaContattiCsv);
			
			//AGGIUNGO IL CONTENUTO DEL FILE DI INPUT IN QUELLO DI OUTPUT
			XmlParser.writeContattiXml(fileOut, listaContattiCsv);			
		}
	}
	
	/**
	 * Metodo per aggiungere il contenuto di fileIn.xml, in coda al contenuto di fileOut.csv.
	 * Sul file di output si aggiunge la riga di intestazione.
	 * @param fileIn File in formato .xml
	 * @param fileOut File in formato .csv
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws TransformerException
	 */
	public static void xmlAddCsv(File fileIn, File fileOut) throws IOException, ParserConfigurationException, SAXException, TransformerException {
		//PRELIEVO LISTA CONTATTI DA XML E DA CSV
		List<Contatto> listaContattiXml = XmlParser.readContattiXml(fileIn);
		
		if(!fileOut.exists()){
			fileOut.createNewFile();
			CsvParser.writeContattiCsv(fileOut, INTESTAZIONE, listaContattiXml);
		}else{
			List<Contatto> listaContattiCsv = CsvParser.readContattiCsv(fileOut);
		
			listaContattiCsv.addAll(listaContattiXml);
			//System.out.println(listaContattiXml);
			
			//AGGIUNGO IL CONTENUTO DEL FILE DI INPUT IN QUELLO DI OUTPUT
			CsvParser.writeContattiCsv(fileOut, INTESTAZIONE, listaContattiCsv);	
		}
	}
	
	/**
	 * Metodo per aggiungere il contenuto di fileIn.xml, in coda al contenuto di fileOut.xml.
	 * @param fileIn File in formato .xml
	 * @param fileOut File in formato .xml
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws TransformerException
	 */
	public static void xmlAddXml(File fileIn, File fileOut) throws IOException, ParserConfigurationException, SAXException, TransformerException {
		List<Contatto> listaContattiXmlIn = XmlParser.readContattiXml(fileIn);
		
		if(!fileOut.exists()){
			fileOut.createNewFile();
			XmlParser.writeContattiXml(fileOut, listaContattiXmlIn);	
		}else {
			List<Contatto> listaContattiXmlOut = XmlParser.readContattiXml(fileOut);
			listaContattiXmlOut.addAll(listaContattiXmlIn);
			
			XmlParser.writeContattiXml(fileOut, listaContattiXmlOut);
		}
	}
}
