package it.beije.oort.franceschi.basics;

public class Fibonacci {

	public static void main(String[] args) {
		long x = 50L;
		long primo = 1L, sec = 1L;
		
		for (int n = 1; n <= x; n++) {
			if (n == 1 || n == 2) {
				System.out.println(n + ": \t1");
			} else {
				long tmp = primo + sec;
				System.out.println(n + ": \t" + tmp);
				primo = sec;
				sec = tmp;
			}
		}
	}
}