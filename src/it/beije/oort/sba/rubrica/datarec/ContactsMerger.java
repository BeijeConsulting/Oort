package it.beije.oort.sba.rubrica.datarec;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import it.beije.oort.sba.rubrica.Contatto;

public class ContactsMerger {
	public static List<Contatto> mergeContact(ContactsMap map) {
		List<Contatto> contatti = new ArrayList<Contatto>(2000);
		Set<Contatto> setContatti;
		for (String key : map.getKeySet()) {
			setContatti = map.getValue(key);
			if (setContatti.size() == 1) {
				for (Contatto contatto : setContatti) {
					contatti.add(contatto);
				}
			} else {
				
			}
		}
		
		return contatti;
		
	}
	
	public boolean compatibilita(Contatto c1,Contatto c2){
		String[] content1 = {c1.getNome(), c1.getCognome(), c1.getEmail()};
		String[] content2 = {c2.getNome(), c2.getCognome(), c2.getEmail()};
		boolean a, b, c;
		a = content1[0].equalsIgnoreCase(content2[0])||content1[0].equals("")||content2[0].equals("");
		b = content1[1].equalsIgnoreCase(content2[1])||content1[1].equals("")||content2[1].equals("");
		c = content1[2].equalsIgnoreCase(content2[2])||content1[2].equals("")||content2[2].equals("");
		return (a && b && c);
	}
}
