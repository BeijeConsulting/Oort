package it.beije.oort.csvToXml;

import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		String filePath = "C:\\Users\\Padawan09\\Desktop\\rubrica_brugaletta.csv";
		String outputPath = "C:\\Users\\Padawan09\\Desktop\\rubrica_brugaletta.xml";
		CSVReader reader = new CSVReader(filePath);
		
		List<ContattoBean> contatti = reader.creaListaContatti();
		XMLWriter.writeList(contatti, outputPath);
	}
}