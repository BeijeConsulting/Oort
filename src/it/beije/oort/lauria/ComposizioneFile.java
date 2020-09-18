package it.beije.oort.lauria;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;


public class ComposizioneFile {
	private static final String PATH_FILES = "C:\\Users\\Padawan06\\Documenti\\temp\\";
	
	public static void main(String[] args) throws Exception {
	
		String fileNameInput = "rubrica4.csv"; 
		String fileNameOutput = "rubricaNew.csv"; 		
		
		File fileIn = new File(PATH_FILES + fileNameInput);
		File fileOut = new File(PATH_FILES + fileNameOutput);
			

			if(fileNameInput.endsWith(".csv") && fileNameOutput.endsWith(".csv")) {				
				ComposizioneFile.csvAddCsv(fileIn, fileOut);	
				
			}else if(fileNameInput.endsWith(".csv") && fileNameOutput.endsWith(".xml")) {		
				ComposizioneFile.csvAddXml(fileIn, fileOut);
				
			}else if(fileNameInput.endsWith(".xml") && fileNameOutput.endsWith(".csv")) {					
				ComposizioneFile.xmlAddCsv(fileIn, fileOut);

			}else if(fileNameInput.endsWith(".xml") && fileNameOutput.endsWith(".xml")){
				ComposizioneFile.xmlAddXml(fileIn, fileOut);
				
			}else {
				System.out.println("Il formato del file di output, non è supportato.");
			}
			

		System.out.println("Fine.");
			
	}
	/**
	 * Metodo per aggiungere il contenuto di fileIn, in coda al contenuto di fileOut.
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
			ReadAndWrite.writeRow(fileOut, firstRow);
			bufferedReader.close();
		}
		
		//LEGGO IL FILE CSV
		StringBuilder content = new StringBuilder();
		content.append(ReadAndWrite.getContent(fileOut));
		
		// ELIMINO PRIMA RIGA DEL FILE INPUT
		StringBuilder contentIn = new StringBuilder();
		contentIn.append(ReadAndWrite.getContent(fileIn));
		contentIn.delete(0, ReadAndWrite.getContent(fileIn).indexOf('\n')+1);
	
		//AGGIUNGO IL CONTENUTO DEL FILE DI INPUT IN QUELLO DI OUTPUT
		content.append(contentIn.toString());
		ReadAndWrite.writeRow(fileOut, content.toString());

	}
	
	/**
	 * Metodo per aggiungere il contenuto di fileIn, in coda al contenuto di fileOut.
	 * @param fileIn File in formato .csv
	 * @param fileOut File in formato .xml
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws TransformerException
	 */
	public static void csvAddXml(File fileIn, File fileOut) throws IOException, ParserConfigurationException, SAXException, TransformerException {
		
		List<Contatto> listaContattiCsv = ReaderCsv.csvReaderIntestazione(fileIn);		
		
		if(!fileOut.exists()){
			fileOut.createNewFile();
			ReaderCsv.writeContattiXml(listaContattiCsv, fileOut);	
		}else{
			//PRELEVO LISTA CONTATTI DA CSV E DA XML
			List<Contatto> listaContattiXml = ReaderCsv.readContatti(fileOut);
			listaContattiXml.addAll(listaContattiCsv);
			
			//AGGIUNGO IL CONTENUTO DEL FILE DI INPUT IN QUELLO DI OUTPUT
			ReaderCsv.writeContattiXml(listaContattiXml, fileOut);			
		}
	}
	
	/**
	 * Metodo per aggiungere il contenuto di fileIn, in coda al contenuto di fileOut.
	 * Sul file di output si aggiunge la riga di intestazione "COGNOME;NOME;E-MAIL;TELEFONO".
	 * @param fileIn File in formato .xml
	 * @param fileOut File in formato .csv
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws TransformerException
	 */
	public static void xmlAddCsv(File fileIn, File fileOut) throws IOException, ParserConfigurationException, SAXException, TransformerException {
		//PRELIEVO LISTA CONTATTI DA XML E DA CSV
		List<Contatto> listaContattiXml = ReaderCsv.readContatti(fileIn);
		
		if(!fileOut.exists()){
			fileOut.createNewFile();
			FileWriter writer = new FileWriter(fileOut);
			writer.write("COGNOME;NOME;E-MAIL;TELEFONO");
			for (Contatto contattoTemp : listaContattiXml) {
				writer.write("\n");
				writer.write(RecordFile.costruisciRiga(contattoTemp));
			}
			writer.flush();
			writer.close();
		}else{
			List<Contatto> listaContattiCsv = ReaderCsv.csvReaderIntestazione(fileOut);
		
			listaContattiCsv.addAll(listaContattiXml);
			//System.out.println(listaContattiXml);
			
			//AGGIUNGO IL CONTENUTO DEL FILE DI INPUT IN QUELLO DI OUTPUT
			FileWriter writer = new FileWriter(fileOut);
			writer.write("COGNOME;NOME;E-MAIL;TELEFONO");
			for (Contatto contattoTemp : listaContattiCsv) {
				writer.write("\n");
				writer.write(RecordFile.costruisciRiga(contattoTemp));
			}
			
			writer.flush();
			writer.close();		
		}
	}
	
	/**
	 * Metodo per aggiungere il contenuto di fileIn, in coda al contenuto di fileOut.
	 * @param fileIn File in formato .xml
	 * @param fileOut File in formato .xml
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws TransformerException
	 */
	public static void xmlAddXml(File fileIn, File fileOut) throws IOException, ParserConfigurationException, SAXException, TransformerException {
		List<Contatto> listaContattiXmlIn = ReaderCsv.readContatti(fileIn);
		
		if(!fileOut.exists()){
			fileOut.createNewFile();
			ReaderCsv.writeContattiXml(listaContattiXmlIn, fileOut);	
		}else {
			List<Contatto> listaContattiXmlOut = ReaderCsv.readContatti(fileOut);
			listaContattiXmlOut.addAll(listaContattiXmlIn);
			
			ReaderCsv.writeContattiXml(listaContattiXmlOut, fileOut);
		}
	}
}
