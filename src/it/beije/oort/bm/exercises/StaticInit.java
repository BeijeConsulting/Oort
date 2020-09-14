package it.beije.oort.bm.exercises;

public class StaticInit {
	static {
		new StaticInit();
	}
	
	static int NUM = 6;
	int num;
	static {
		System.out.println("0");
	}
	
	StaticInit(){
		
		num = NUM;
		System.out.println(num);
	}
	public static void main(String[] args) {
		System.out.println("1");

	}

}
