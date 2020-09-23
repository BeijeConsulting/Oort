package it.beije.oort;

import java.util.Scanner;

public class ScannerExample {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        String st = s.next();
        while (!st.equalsIgnoreCase("Q")) {
            st = s.next();
            System.out.println(st);

            //...
        }

        System.gc();
        System.out.println("BYE!!");
        s.close();

    }

}
