package it.beije.oort.bm.exercises.rubrica;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class XmlToCsvMain {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		if(args.length == 0) {
			System.out.println("Correct usage: java RubricaMain <xmlFile 1>...<xmlFile n>");
			System.exit(0);
		}
		List<List<Contatto>> lists = new ArrayList<List<Contatto>>();
		for(int i = 0; i<args.length;i++) {
			List<Contatto> l = XmlParser.readContatti(new File(args[i]));
			lists.add(l);
		}
		CsvContactsWriter.writeFile(new File("Rubrica_gen.csv"), lists);

	}

}
