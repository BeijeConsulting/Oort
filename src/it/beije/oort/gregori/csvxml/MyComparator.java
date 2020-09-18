package it.beije.oort.gregori.csvxml;

import java.util.Comparator;

import it.beije.oort.rubrica.Contatto;

public class MyComparator implements Comparator<Contatto> {

	@Override
	public int compare(Contatto o1, Contatto o2) {
		int res = o1.getCognome().compareTo(o2.getCognome());

		if(res != 0) {
			return res;
		}
		else {
			return o1.getNome().compareTo(o2.getNome());
		}
	}

}
