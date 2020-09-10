package it.beije.oort.gregori.fibonacci;

public class Fibonacci {

	public static void main(String[] args) {
		
		long n1 = 0, n2 = 1, n3;
		int count = 50;
		
		System.out.println("Fibonacci di " + count + ":");
		System.out.println("0: " + n1);
		System.out.println("1: " + n2); 
		    
		for(int i = 2; i <= count; ++i) {    
			n3 = n1 + n2;    
			System.out.println(i + ": " + n3);    
			n1 = n2;    
			n2 = n3;    
		}    
	}

}
