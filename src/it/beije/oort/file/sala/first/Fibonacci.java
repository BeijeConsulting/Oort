package it.beije.oort.file.sala.first;

public class Fibonacci {
//Il primo esercizio di fibonacci è stato convertito in questo per praticità.
//Meglio non chiamare questo programma con una sequenza di fib maggiore di 30 elementi
	public static void main(String[] args) {
		long x = 0;
		long y = 0;
		long z;
		String fib = "";
		//Per modificare la lunghezza della sequenza da stampare ritoccare il parametro nel for
		for(int i=0;i<30;i++) {
			if(y==0 && x==0) {
				fib+=x;
				x++;
				System.out.println(fib);
			} else if(y==0 && x==1) {
				fib+=", "+x;
				y++;
				System.out.println(fib);
			} else {
				fib+=", "+x;
				z=x;
				x+=y;
				y=z;
				System.out.println(fib);
			}
		}
	}
}
