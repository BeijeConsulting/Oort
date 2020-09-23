package it.beije.oort.franceschi.basics;

public class DisegnoTre {

    public static void main(String[] args) {
        int val = Integer.parseInt(args[0]);

        for (int i = 0; i < val; i++) {
            for (int k = 0; k <= i; k++) {
                System.out.print(k + 1);
            }
            System.out.print("      ");
            for (int z = val - i; z > 0; z--) {
                System.out.print(z);
            }
            System.out.println();
        }
    }

}
