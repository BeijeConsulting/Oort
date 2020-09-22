package it.beije.oort.sba.rubrica.datarec;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
		}
		
		return contatti;
		
	}
	
	private static void mergeContact(Set<Contatto> set) {
		if (set.size() == 1) {
			return;
		}
		boolean compatible = false;
		List<Contatto> contacts = new ArrayList<Contatto>();
		for (Contatto c : set) {
			contacts.add(c);
		}
		set.clear();
		for (int i = 0; i < contacts.size(); i++) {
			boolean merged = false;
			for (int j = i + 1; j < contacts.size(); j++) {
				if (isCompatible(contacts.get(i), contacts.get(j))) {
					set.add(merge(contacts.get(i),contacts.get(j)));
					merged = true;
					compatible = true;
				}
			}
			if (!merged) {
				set.add(contacts.get(i));
				merged = false;
			}
		}
		if (compatible) {
			mergeContact(set);
		} else {
			return;
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
		
		return cResult;
	}
	
	public static void rubricaCsvWriter(File file, List<Contatto> contatti) throws IOException {
		FileWriter writer = new FileWriter(file);
		writer.write("COGNOME;NOME;EMAIL;TELEFONO\n");	
		StringBuilder builder = new StringBuilder();
		for(int i = 0;i<contatti.size();i++) {
			builder.append(contatti.get(i).getCognome()).append(";")
					.append(contatti.get(i).getNome()).append(";")
					.append(contatti.get(i).getEmail()).append(";")
					.append(contatti.get(i).getTelefono()).append("\n");
			}
		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}
	
	
}