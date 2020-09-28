package it.beije.oort.franceschi.rubrica.comparators;

import it.beije.oort.franceschi.rubrica.Contatto;

import java.util.Comparator;

public class ContattoCognomeComparator implements Comparator<Contatto> {
    public int compare(Contatto chair1, Contatto chair2) {
        return chair1.getCognome().compareTo(chair2.getCognome());
    }
}