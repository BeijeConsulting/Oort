package it.beije.oort.gregori.fibonacci;

public class Fibonacci2 {

	public static void main(String[] args) {

		long n1 = 0, n2 = 1, n3;
		int count = 50;
		String result = "";
		
		System.out.println("Fibonacci di " + count + ":");
		System.out.println(n1);
		result = result + Long.toString(n1) + ", " + Long.toString(n2);
		System.out.println(result); 
		    
		for(int i = 2; i <= count; ++i) {    
			n3 = n1 + n2;
			result = result + ", " + Long.toString(n3);
			System.out.println(result);    
			n1 = n2;    
			n2 = n3;    
		}    

	}

}
