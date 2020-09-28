package it.beije.oort.sba.rubrica.datarec;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import it.beije.oort.sba.rubrica.Contatto;

public class ContactsMerger {
	
	public static List<Contatto> composeList(ContactsMap map) {
		List<Contatto> contatti = new ArrayList<Contatto>(2000);
		Set<Contatto> setContatti;
		for (String key : map.getKeySet()) {
			setContatti = map.getValue(key);
			mergeContact(setContatti);
			contatti.addAll(setContatti);
		}
		return contatti;
		
	}
	
	private static void mergeContact(Set<Contatto> set) {
		if (set.size() <= 1) {
			return;
		}
		boolean compatible = false;
		List<Contatto> contacts = new ArrayList<Contatto>();
		for (Contatto c : set) {
			contacts.add(c);
		}
		set.clear();
		boolean[] merged = new boolean[contacts.size()];
		for(int i = 0; i<merged.length; i++) {
			merged[i] = false;
		}
		for (int i = 0; i < contacts.size()-1; i++) {
			for (int j = i + 1; j < contacts.size(); j++) {
				if (isCompatible(contacts.get(i), contacts.get(j))) {
					set.add(merge(contacts.get(i),contacts.get(j)));
					merged[i] = true;
					merged[j] = true;
					compatible = true;
				}
			}
		}
		for(int i = 0; i<merged.length; i++) {
			if(!merged[i]) {
				set.add(contacts.get(i));
			}
		}
		if (compatible) {
			mergeContact(set);
		}
	}

	private static boolean isCompatible(Contatto c1,Contatto c2) {
		String[] content1 = {c1.getNome(), c1.getCognome(), c1.getEmail()};
		String[] content2 = {c2.getNome(), c2.getCognome(), c2.getEmail()};
		boolean a, b, c;
		a = content1[0].equalsIgnoreCase(content2[0])||content1[0].equals("")||content2[0].equals("");
		b = content1[1].equalsIgnoreCase(content2[1])||content1[1].equals("")||content2[1].equals("");
		c = content1[2].equalsIgnoreCase(content2[2])||content1[2].equals("")||content2[2].equals("");
		return (a && b && c);
	}
	
	
	private static Contatto merge(Contatto c1, Contatto c2) {
		Contatto cResult = new Contatto();
		
		if(c1.getNome().equalsIgnoreCase(c2.getNome())) cResult.setNome(c1.getNome());
		else cResult.setNome(c1.getNome()+c2.getNome());
		
		if(c1.getCognome().equalsIgnoreCase(c2.getCognome())) cResult.setCognome(c1.getCognome());
		else cResult.setCognome(c1.getCognome()+c2.getCognome());
		
		if(c1.getEmail().equalsIgnoreCase(c2.getEmail())) cResult.setEmail(c1.getEmail());
		else cResult.setEmail(c1.getEmail()+c2.getEmail());
		
		cResult.setTelefono(c1.getTelefono());
		
		return cResult;
	}
	
}
