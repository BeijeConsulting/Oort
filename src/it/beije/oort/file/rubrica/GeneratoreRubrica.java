package it.beije.oort.file.rubrica;

import it.beije.oort.file.rubrica.comparators.ContattoNomeComparator;
import it.beije.oort.file.rubrica.utils.Valori;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GeneratoreRubrica {

    private final static int VALORI_DA_SCRIVERE = 1_000;

    private GeneratoreRubrica() {
    }

    public static void creaRubrica(ArrayList<String> nomi, ArrayList<String> cognomi) {
        for (int i = 0; i < VALORI_DA_SCRIVERE; i++) {
            Contatto c = new Contatto(nomi, cognomi);
            Valori.contatti.add(c);
        }
        System.out.println("Rubrica generata.");
    }

    public static void creaRubricaRandom() {
        for (int i = 0; i < VALORI_DA_SCRIVERE; i++) {
            Contatto c = new Contatto(true);
            Valori.contatti.add(c);
        }
        System.out.println("Rubrica random generata.");
    }

    public static void writeRubrica(ArrayList<Contatto> rubrica, String filename) {
        Random r = new Random();
        Collections.sort(rubrica, new ContattoNomeComparator());
        //Collections.sort(rubrica, new ContattoCognomeComparator())
        try (BufferedWriter bf = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(filename, true), StandardCharsets.UTF_8))) {

            bf.write("COGNOME;NOME;TELEFONO;EMAIL;\n");

            for (int i = 0; i < rubrica.size(); i++) {
                if (i != rubrica.size() - 1) {
                    bf.write(Contatto.getContattoFormattatoToString(rubrica, i, r) + '\n');
                } else {
                    bf.write(Contatto.getContattoFormattatoToString(rubrica, i, r));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Rubrica scritta.");
    }
}