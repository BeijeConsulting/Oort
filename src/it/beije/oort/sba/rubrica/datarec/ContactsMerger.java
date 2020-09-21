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
}
