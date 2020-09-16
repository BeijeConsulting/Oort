package it.beije.oort.bm.exercises.rubrica;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class RubricaMain {

	public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException {
		if(args.length == 0) {
			System.out.println("Correct usage: java RubricaMain <csvFile 1>...<csvFile n>");
			System.exit(0);
		}
		List<List<Contatto>> lists = new ArrayList<List<Contatto>>();
		for(int i = 0; i<args.length;i++) {
			List<Contatto> l = CsvContactsReader.readFile(new File(args[i]));
			lists.add(l);
		}
		XmlParser.writeFile(new File("Rubrica.xml"), lists);

	}

}
