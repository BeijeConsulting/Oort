package it.beije.oort.lauria;

import java.util.Comparator;

public class MatchEmail implements Comparator<Contatto>{
	public int compare(Contatto left, Contatto right) {
        return left.getEmail().compareTo(right.getEmail());
    }
}

