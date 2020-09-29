package it.beije.oort.bru.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.beije.oort.files.Contatto;

public class Utilities {
	
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
}
