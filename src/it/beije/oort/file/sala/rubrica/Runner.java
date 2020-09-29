package it.beije.oort.file.sala.rubrica;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import it.beije.oort.file.sala.db.HibernateToolset;

public class Runner {

	public static void main(String[] args)
			throws IOException, ParserConfigurationException, SAXException, TransformerException{
//		GeneratoreRubrica g = new GeneratoreRubrica();
//		List<Contatto> list = g.generaRubrica(Valori.getNomi(), Valori.getCognomi(), 50);
//		List<Contatto> list2 = g.generaRubrica(Valori.getNomi(), Valori.getCognomi(), 5);
//		RubricaToolset.contattoToCsv(list, "C:/Users/Padawan12/Desktop/rubrica_test_input_handler.csv");
//		RubricaToolset.contattoToXml(list, "C:/Users/Padawan12/Desktop/rubrica_test_output1_handler.xml");
//		RubricaToolset.contattoToXml(list2, "C:/Users/Padawan12/Desktop/rubrica_test_output2_handler.xml");
//		RubricaHandler.tranferRubrica("C:/Users/Padawan12/Desktop/rubrica_test_input_handler.csv",
//				"C:/Users/Padawan12/Desktop/rubrica_test_output1_handler.xml");
//		RubricaHandler.tranferRubrica("C:/Users/Padawan12/Desktop/rubrica_test_input_handler.csv",
//				"C:/Users/Padawan12/Desktop/rubrica_test_output2_handler.xml");
//		RubricaToolset.contattoToSql(list);
//		HibernateToolset.insertHibernate(list);
//		List<Contatto> temp = HibernateToolset.selectHibernate(true);
//		for(Contatto c : temp) {
//			System.out.println(c.toString());
//		}
//		HibernateToolset.insertHibernate(list);
//		System.out.println(HibernateToolset.selectHibernate(true));
		//HibernateToolset.updateHibernate(53, "nome", "Pippo");
		System.out.println(HibernateToolset.selectHibernate("nome", "a"));
	}
}