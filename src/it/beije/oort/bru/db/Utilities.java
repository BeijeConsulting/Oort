package it.beije.oort.bru.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.beije.oort.biblioteca.Autore;
import it.beije.oort.biblioteca.Editore;
import it.beije.oort.files.Contatto;

public class Utilities {
	
	//Metodo per impaginare una lista di contatti, 50 record per pagina.
	public static Map<Integer, List<Contatto>> layout(List<Contatto> contacts) {
		Map<Integer, List<Contatto>> pages = new HashMap<Integer, List<Contatto>>();
		int pageStart = 0;
		int pageEnd = 50;
		int end = (contacts.size()/50) + 1;
		for (int i = 1; i <= end; i++) {
			if (pageEnd > contacts.size()) {
				pageEnd = contacts.size();
			}
			pages.put(i, contacts.subList(pageStart, pageEnd));
			pageStart = pageEnd;
			pageEnd += 50;
		}
		return pages;
	}
	
	//Stampa una lista di autori e restituisce l'id dell'ultimo autore.
	public static int printAuthors(List<Autore> authors) {
		int lastID = 0;
		for (int i = 0; i < authors.size(); i++) {
			System.out.println(authors.get(i));
			if (i == authors.size()-1) {
				lastID = authors.get(i).getId();
			}
		}
		return lastID;
	}
	
	public static int printPublishers(List<Editore> publishers) {
		int lastID = 0;
		for (int i = 0; i < publishers.size(); i++) {
			System.out.println(publishers.get(i));
			if (i == publishers.size()-1) {
				lastID = publishers.get(i).getId();
			}
		}
		return lastID;
	}
}
