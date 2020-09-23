package it.beije.oort.franceschi.basics;

public class DisegnoFibonacci {

    public static void main(String[] args) {
        int x = 20;
        long primo = 1L, sec = 1L;
        String stringone = "";

        for (int n = 1; n <= x; n++) {
            if (n == 1) {
                stringone += "1";
            } else if (n == 2) {
                stringone += ", 1";
            } else {
                long tmp = primo + sec;
                stringone += ", " + tmp;
                primo = sec;
                sec = tmp;
            }
            System.out.println(stringone);
        }
    }
}
