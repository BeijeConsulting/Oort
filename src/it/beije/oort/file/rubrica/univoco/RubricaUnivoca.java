package it.beije.oort.file.rubrica.univoco;

import it.beije.oort.file.rubrica.Contatto;
import it.beije.oort.file.rubrica.comparators.ContattoEmailComparator;
import it.beije.oort.franceschi.csvToXml.CSVParser;
import it.beije.oort.franceschi.csvToXml.CSVWriter;
import it.beije.oort.franceschi.csvToXml.InputManager;

import java.util.*;

public class RubricaUnivoca {
    private static List<Contatto> duplicatiList = new ArrayList<>();
    private static Map<String, Contatto> univociMap = new HashMap<>();
    static List<Contatto> duplicatiSenzaMail = new ArrayList<>();
    int contattiTot = 0;
    static int contattiUnici = 0;
    static int contattiDupes = 0;


    public static void main(String[] args) {
        // Crea una lista con tutti i contatti nel file.
        List<Contatto> lista = new CSVParser(InputManager.getDuplicatiPath() + "rubrica.csv").creaListaContatti();

        separaDuplicati(lista);
        System.out.println("Totali: " + lista.size() +
                ". Unici: " + contattiUnici +
                ". Dupes: " + contattiDupes +
                ". Unici+Dupes: " + (contattiUnici + contattiDupes));

        // Li sorto
        Collections.sort(duplicatiList, new ContattoEmailComparator());

        // Rimuovo i contatti senza mail, per ora
        //duplicatiSenzaMail = rimuoviContattiNoEmail();

        accorpaDuplicati();

        CSVWriter.writeCSV(new ArrayList<Contatto>(univociMap.values()),
                InputManager.getDuplicatiPath() + "rubrica_univoci.csv",
                new ContattoEmailComparator());
        System.out.println("Scritto file Univoci");
        CSVWriter.writeCSV(duplicatiList,
                InputManager.getDuplicatiPath() + "rubrica_duplicati.csv",
                new ContattoEmailComparator());
        System.out.println("Scritto file Duplicati");
    }


    public static List<Contatto> rimuoviContattiNoEmail() {
        List<Contatto> noMail = new ArrayList<>();
        int rimossi = 0;
        for (int i = 0; i < duplicatiList.size(); i++) {
            if (duplicatiList.get(i).getEmail().equalsIgnoreCase("") || duplicatiList.get(i).getEmail() == null) {
                noMail.add(duplicatiList.get(i));
                duplicatiList.remove(duplicatiList.get(i));
                rimossi++;
            }
        }
        System.out.println("Rimossi contatti no email: " + rimossi);
        return noMail;
    }

    public static void accorpaDuplicati() {
        System.out.println("Tento accorpamento.");
        List<Contatto> nuovaListaDupes = new ArrayList<Contatto>();
        List<Contatto> stessaMailTemp = new ArrayList<Contatto>();
        List<Contatto> duplicatiList2 = new ArrayList<>();
        duplicatiList2.addAll(duplicatiList);
        String email = "";
        while (!duplicatiList.isEmpty()) {
            email = duplicatiList.get(0).getEmail();
            for (Contatto c : duplicatiList2) {
                if (c.getEmail().equals(email)) {
                    stessaMailTemp.add(c);
                    duplicatiList.remove(c);
                }
            }
            stessaMailTemp.add(univociMap.remove(email));
            //adesso in una lista ho tutti i contatti con la stessa mail
            stessaMailTemp = tentaAccorpamentoTraDupes(stessaMailTemp);
            //Adesso questi sono tutti accorpati al max delle possibilità
            for (int i = 0; i < stessaMailTemp.size(); i++) {
                if (i == 0) univociMap.put(email, stessaMailTemp.get(0));
                else {
                    nuovaListaDupes.add(stessaMailTemp.get(i));
                }
            }
        }
        duplicatiList.addAll(nuovaListaDupes);
    }


    private static void separaDuplicati(List<Contatto> rubrica) {
        for (Contatto c : rubrica) {
            Contatto temp = univociMap.put(c.getEmail(), c);
            if (temp != null) {
                System.out.println();
                compareContacts(c, temp);
                contattiDupes++;
            } else {
                contattiUnici++;
            }
        }
    }

    public static List<Contatto> tentaAccorpamentoTraDupes(List<Contatto> contatti) {
        List<Contatto> stessaMailMetodo = new ArrayList<>(contatti);
        for (Contatto c : contatti) {
            for (Contatto b : contatti) {
                Contatto nuovo = new Contatto();
                if (!c.getNome().equals(b.getNome()) ||
                        !c.getCognome().equals(b.getCognome()) ||
                        !c.getCell().equals(b.getCell())) {
                    continue;
                }
                if (b.getNome() != "") {
                    nuovo.setNome(b.getNome());
                }
                if (b.getCognome() != "") {
                    nuovo.setCognome(b.getCognome());
                }
                if (b.getCell() != "") {
                    nuovo.setCell(b.getCell());
                }
                stessaMailMetodo.remove(b);
                stessaMailMetodo.remove(c);
                stessaMailMetodo.add(nuovo);
            }
        }

        return stessaMailMetodo;

    }

    public static void compareContacts(Contatto c, Contatto espulso) {
        if (!c.getNome().equals(espulso.getNome()) ||
                !c.getCognome().equals(espulso.getCognome()) ||
                !c.getCell().equals(espulso.getCell())) {
            duplicatiList.add(espulso);
            return;
        }

        if (espulso.getNome() != "") {
            c.setNome(espulso.getNome());
        }
        if (espulso.getCognome() != "") {
            c.setCognome(espulso.getCognome());
        }
        if (espulso.getCell() != "") {
            c.setCell(espulso.getCell());
        }
        //duplicatiMap.add(c);
        univociMap.put(c.getEmail(), c);
    }


    public static List<Contatto> getDuplicatiList() {
        return duplicatiList;
    }


    public static void setDuplicatiList(List<Contatto> duplicatiList) {
        RubricaUnivoca.duplicatiList = duplicatiList;
    }


    public static Map<String, Contatto> getUnivociMap() {
        return univociMap;
    }


    public static void setUnivociMap(Map<String, Contatto> univociMap) {
        RubricaUnivoca.univociMap = univociMap;
    }
}
