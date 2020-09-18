package it.beije.oort.file.rubrica.comparators;

import java.util.Comparator;

import it.beije.oort.file.rubrica.Contatto;

public class ContattoNomeComparator implements Comparator<Contatto> {
    public int compare(Contatto chair1, Contatto chair2) {
        return chair1.getNome().compareTo(chair2.getNome());
    }
}