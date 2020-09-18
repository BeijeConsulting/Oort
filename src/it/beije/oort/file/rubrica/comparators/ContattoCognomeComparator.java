package it.beije.oort.file.rubrica.comparators;

import java.util.Comparator;

import it.beije.oort.file.rubrica.Contatto;

public class ContattoCognomeComparator implements Comparator<Contatto> {
    public int compare(Contatto chair1, Contatto chair2) {
        return chair1.getCognome().compareTo(chair2.getCognome());
    }
}