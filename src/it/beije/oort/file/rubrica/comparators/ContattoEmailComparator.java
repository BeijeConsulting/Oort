package it.beije.oort.file.rubrica.comparators;

import it.beije.oort.file.rubrica.Contatto;

import java.util.Comparator;

public class ContattoEmailComparator implements Comparator<Contatto> {
    public int compare(Contatto c1, Contatto c2) {
        return c1.getEmail().compareTo(c2.getEmail());
    }
}
