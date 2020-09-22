package it.beije.oort.sba.rubrica.datarec;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import it.beije.oort.sba.rubrica.Contatto;

public class ContactsMap {
	private Map<String, Set<Contatto>> map = new HashMap<>();
	
	public ContactsMap() {}
	
	public ContactsMap(List<Contatto> list) {
		for(Contatto c : list) {
			this.add(c);
		}
	}
	
	public void add(Contatto c) {
		if(c.getTelefono() == null || c.getTelefono().equals("")) return;
		//if(c.getTelefono() == null) c.setTelefono("");
		if(!map.keySet().contains(c.getTelefono())) {
			map.put(c.getTelefono(), new HashSet<Contatto>(1));
		}
		map.get(c.getTelefono()).add(c);
	}
	
	public Set<Contatto> getValue(String key){
		return map.get(key);
	}
	
	public Set<String> getKeySet() {
		return map.keySet();
	}
	
//	public static ContactsMap getMap(List<Contatto> list) {
//		ContactsMap ret = new ContactsMap();
//		for(Contatto c : list) {
//			ret.add(c);
//		}
//		return ret;
//	}
}
