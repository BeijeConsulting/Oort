package it.beije.oort.file.rubrica;

import it.beije.oort.file.rubrica.utils.Valori;

public class Main {
    public static void main(String[] args) {
        GeneratoreRubrica.creaRubricaRandom();
        GeneratoreRubrica.writeRubrica(Valori.contatti, Valori.getOutputPath());
    }
}
