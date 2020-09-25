package it.beije.oort.file.rubrica.univoco;

import it.beije.oort.file.rubrica.Contatto;
import it.beije.oort.file.rubrica.comparators.ContattoEmailComparator;
import it.beije.oort.franceschi.csvToXml.CSVParser;
import it.beije.oort.franceschi.csvToXml.CSVWriter;
import it.beije.oort.franceschi.csvToXml.InputManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RubricaUnivoca2 {
    private static final List<Contatto> duplicatiList = new ArrayList<>();
    private static final Map<String, Contatto> univociMap = new HashMap<>();

    public static void main(String[] args) {
        List<Contatto> tutti = new CSVParser(InputManager.getDuplicatiPath() + "rubrica.csv").creaListaContatti();


		//tutti = rimuoviMailVuote(tutti);
		// tolte le mail vuote

		List<Contatto> accorpati = accorpa(tutti);

//		CSVWriter.writeCSV(accorpati, InputManager.getDuplicatiPath() + "rubrica_test.csv",
//				new ContattoEmailComparator());
//
//		System.out.println("end");


        separa(accorpati);
        CSVWriter.writeCSV(duplicatiList, InputManager.getDuplicatiPath() + "rubrica_duplicati.csv",
                new ContattoEmailComparator());
        CSVWriter.writeCSV(new ArrayList<>(univociMap.values()), InputManager.getDuplicatiPath() + "rubrica_univoci.csv",
                new ContattoEmailComparator());
    }

    private static List<Contatto> rimuoviMailVuote(List<Contatto> list) {
        List<Contatto> senzaMail = new ArrayList<>();
        List<Contatto> soloMail = new ArrayList<>();
        for (Contatto contatto : list) {
            if (contatto.getEmail() != null && !contatto.getEmail().equalsIgnoreCase("")) {
                senzaMail.add(contatto);
            } else {
                soloMail.add(contatto);
            }
        }
        return senzaMail;
    }

    private static List<Contatto> accorpa(List<Contatto> tutti) {
        List<Contatto> nuovaList = new ArrayList<>();
        List<Contatto> stessaMailTemp = new ArrayList<>();

        for (int i = 0; i < tutti.size() - 1; i++) {
            boolean dupe = false;
            for (int k = i + 1; k < tutti.size(); k++) {
                if (tutti.get(i).getEmail().equalsIgnoreCase(tutti.get(k).getEmail())) {
                    dupe = true;
                    stessaMailTemp.add(tutti.get(k));
                }
            }
            if (dupe) {
                // se era duplicato, togli anche l'index
                stessaMailTemp.add(tutti.get(i));
                // rimuovi la lista di dupes da tutti
                tutti.removeAll(stessaMailTemp);

                // lavora sulla lista di dupes
                stessaMailTemp = new ArrayList<>(accorpaDupes(stessaMailTemp));

                nuovaList.addAll(stessaMailTemp);
                stessaMailTemp.clear();

                if (i > 0) i--;
            }
        }
        nuovaList.addAll(tutti);
        return nuovaList;
    }

    // non funziona come dovrebbe
    private static List<Contatto> accorpaDupes(List<Contatto> stessaMail) {
        List<Contatto> nuovaList = new ArrayList<>();

        System.out.println(stessaMail.toString());
        //boolean modified ;
        for (int i = 0; i < stessaMail.size(); i++) {
            for (int k = 0; k < stessaMail.size(); k++) {
                //modified = false;
                Contatto c = stessaMail.get(i);
                Contatto b = stessaMail.get(k);
                Contatto nuovo = new Contatto();
                nuovo.setEmail(c.getEmail());
                nuovo.setNome(c.getNome());
                nuovo.setCognome(c.getCognome());
                nuovo.setCell(c.getCell());

                // check if they are identical, if so delete one
                // check with a methods
                if (c.getNome().equals(b.getNome()) &
                        c.getCognome().equals(b.getCognome()) &
                        c.getCell().equals(b.getCell())) {
                    stessaMail.remove(b);
                    i = 0;
                    k = 0;
                }
//                if (!c.getNome().equals(b.getNome()) && !c.getAlias().contains(b.getNome())) {
//                    String alias = c.getAlias();
//                    alias += ", " + b.getNome();
//                    c.setAlias(alias);
//                    stessaMail.remove(b);
//                    i = 0;
//                    k = 0;
//                }


//				if (c.getNome().equals("") & !b.getNome().equals("")) {
//					nuovo.setNome(b.getNome());
//					modified = true;
//				} 
//				if (c.getCognome().equals("") & !b.getCognome().equals("")) {
//					c.setCognome(b.getCognome());
//					modified = true;
//				}
//				
//				if (c.getCell().equals("") & !b.getCell().equals("")) {
//					c.setCell(b.getCell());
//					modified = true;
//				} 
//				if (modified) {
//					stessaMail.add(nuovo);
//					stessaMail.remove(c);
//					stessaMail.remove(b);
//					i = 0;
//					k = 0;
//				
//				}
            }
        }
        nuovaList.addAll(stessaMail);
        return nuovaList;
    }

    private static boolean checkSeCompatibili(Contatto a, Contatto b) {
        if (!a.getNome().equals("") && !a.getNome().equalsIgnoreCase(b.getNome())) {
            return false;
        }
        if (!a.getCognome().equals("") && !a.getCognome().equalsIgnoreCase(b.getCognome())) {
            return false;
        }
        return a.getCell().equals("") || a.getCell().equalsIgnoreCase(b.getCell());
    }

    private static void separa(List<Contatto> list) {
        for (Contatto c : list) {
            Contatto temp = univociMap.put(c.getEmail(), c);
            if (temp != null) {
                duplicatiList.add(temp);
            }
        }
    }
}
