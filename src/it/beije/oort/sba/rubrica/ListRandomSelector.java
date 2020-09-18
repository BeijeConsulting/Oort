package it.beije.oort.sba.rubrica;

import java.util.List;
import java.util.Random;

public class ListRandomSelector {
	private List<String> list;
	
	public ListRandomSelector(List<String> list) {
		this.list = list;
	}
	
	/* Il metodo getNext restituisce un elemento random pescato dalla variabile private list. 
	 * Se la lista è vuota restituisce null.*/
	public String getNext() {
		if(list == null) return null;
		// siz contiene la size della variabile list.
		int siz = list.size();
		Random r = new Random();
		//prendo un indice a caso dalla lista.
		int index = Math.abs(r.nextInt())%siz;
		//restituisco l'elemento a quell'indice.
		return list.get(index);
	}
}
