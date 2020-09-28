package it.beije.oort.franceschi.rubrica.utils;

import java.util.Random;

public class GeneraNumero {
    public static String generaNumero() {
        Random r = new Random();
        StringBuilder suffisso = new StringBuilder();
        String num;
        for (int i = 0; i < 7; i++) {
            suffisso.append(r.nextInt(10));
        }

        int n = r.nextInt(8) + 1;

        if (n == 1) {
            num = "";
        } else if (n == 2 && Valori.cellCompleti.size() > 0) {
            num = Valori.cellCompleti.get(r.nextInt(Valori.cellCompleti.size()));
        } else if (n <= 4) {
            num = "+39" + Valori.getPrefisso(r.nextInt(5)) + suffisso;
        } else {
            num = Valori.getPrefisso(r.nextInt(5)) + suffisso;
        }
        Valori.cellCompleti.add(num);
        return num;
    }
}
