package it.beije.oort.franceschi.rubrica.comparators;

import it.beije.oort.franceschi.rubrica.Contatto;

import java.util.Comparator;

public class ContattoNomeComparator implements Comparator<Contatto> {
    public int compare(Contatto c, Contatto c2) {
        return c.getNome().compareTo(c2.getNome());
    }
}