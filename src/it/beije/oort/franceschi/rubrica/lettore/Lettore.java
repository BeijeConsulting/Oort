package it.beije.oort.franceschi.rubrica.lettore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Lettore {
    private Lettore() {
    }

    public static String[] getArrayValori(String filePath) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        ArrayList<String> valori = new ArrayList<>();
        while (br.ready()) {
            valori.add(br.readLine());
        }
        br.close();
        return valori.toArray(new String[0]);
    }
}