package it.beije.oort.franceschi.scannertofile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MyReader {
    public static String readFile(File file) throws Exception {
        if (!file.exists()) return ""; //If the file doesn't exist, return empty string

        StringBuilder s = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(file));

        while (br.ready()) {
            s.append(br.readLine() + '\n');
        }
        br.close();

        System.out.println("File was already created. Here's the content:");
        System.out.println(s);

        return s.toString();
    }
}
