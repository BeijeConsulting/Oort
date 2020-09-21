package it.beije.oort.file.sala;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class Runner {

	public static void main(String[] args)
			throws IOException, ParserConfigurationException, SAXException, TransformerException{
		GeneratoreRubrica g = new GeneratoreRubrica();
		List<Contatto> list = g.generaRubrica(Valori.getNomi(), Valori.getCognomi(), 5);
		List<Contatto> list2 = g.generaRubrica(Valori.getNomi(), Valori.getCognomi(), 5);
		RubricaToolset.contattoToCsv(list, "C:/Users/Padawan12/Desktop/rubrica_test_input_handler.csv");
		RubricaToolset.contattoToXml(list, "C:/Users/Padawan12/Desktop/rubrica_test_output1_handler.xml");
		RubricaToolset.contattoToXml(list2, "C:/Users/Padawan12/Desktop/rubrica_test_output2_handler.xml");
		RubricaHandler.tranferRubrica("C:/Users/Padawan12/Desktop/rubrica_test_input_handler.csv",
				"C:/Users/Padawan12/Desktop/rubrica_test_output1_handler.xml");
		RubricaHandler.tranferRubrica("C:/Users/Padawan12/Desktop/rubrica_test_input_handler.csv",
				"C:/Users/Padawan12/Desktop/rubrica_test_output2_handler.xml");
		new GeneratoreRubrica();
	}
}