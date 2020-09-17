package it.beije.oort.sba.rubrica;

import java.util.List;
import java.util.Random;

public class ListRandomSelector {
	private List<String> list;
	
	public ListRandomSelector(List<String> list) {
		this.list = list;
	}
	
	public String getNext() {
		if(list == null) return null;
		int siz = list.size();
		Random r = new Random();
		int index = Math.abs(r.nextInt())%siz;
		return list.get(index);
	}
}
