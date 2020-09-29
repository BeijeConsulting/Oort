package it.beije.oort.sb.db;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import it.beije.oort.sb.hibernate.ClientDBHyb;

public class menu {

	public static void main(String[] args) throws DOMException, IOException, ParserConfigurationException, TransformerException, SAXException{
		ClientDBHyb.menu();
	}
}
