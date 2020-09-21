package it.beije.oort.file.rubrica.comparators;

import java.util.Comparator;

import it.beije.oort.file.rubrica.Contatto;

public class ContattoEmailComparator implements Comparator<Contatto> {
    public int compare(Contatto c1, Contatto c2) {
        return c1.getEmail().compareTo(c2.getEmail());
    }
}
