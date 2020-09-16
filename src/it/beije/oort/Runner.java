package it.beije.oort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import it.beije.oort.rubrica.Contatto;
import it.beije.oort.rubrica.ParserRubricaXml;

public class Runner {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
//		Contatto contatto = new Contatto();
//		contatto.setNome("Mario");
//		contatto.setCognome("Rossi");
//		contatto.setTelefono("3337658390");
//		contatto.setEmail("mario.rossi@tim.it");
		
		Contatto contatto1 = new Contatto("Carlo", "Bianchi", "3337658231");
		Contatto contatto2 = new Contatto("Mario", "Rossi", "3337658390", "mario.rossi@tim.it");
		Contatto contatto3 = new Contatto("Federica", "Verdi", "3457238390", "f.verdi@vodafone.it");
		
		List<Contatto> contatti = new ArrayList<Contatto>();
		contatti.add(contatto1);
		contatti.add(contatto2);
		contatti.add(contatto3);
		
//		System.out.println(contatto1);
//		System.out.println(contatto2);

//		File file = new File("./rubrica.xml");
//		
//		List<Contatto> rubrica = ParserRubricaXml.readContatti(file);
//		
//        System.out.println("rubrica size : " + rubrica.size());
	}

}
