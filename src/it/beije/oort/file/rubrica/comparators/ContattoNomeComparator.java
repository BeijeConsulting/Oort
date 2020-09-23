package it.beije.oort.file.rubrica.comparators;

import it.beije.oort.file.rubrica.Contatto;

import java.util.Comparator;

public class ContattoNomeComparator implements Comparator<Contatto> {
    public int compare(Contatto c, Contatto c2) {
        return c.getNome().compareTo(c2.getNome());
    }
}