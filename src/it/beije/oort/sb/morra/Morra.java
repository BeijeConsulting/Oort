package it.beije.oort.sb.morra;

public class Morra {
	
	
	public static void main(String[] args) {
		
		String a=args[0];
		String b=args[1];
		if(a.equals(b)) {
			System.out.println("Parità!");
		}
		if(a.equals("forbice")){
			if(b.equals("carta")) {
				System.out.println("forbice vince contro carta");
			} else if(b.equals("sasso")) {
				System.out.println("forbice perde contro sasso");
			}
				else System.out.println(b +" non è un argomento valido");			}
		
		if(a.equals("carta")){
			if(b.equals("forbice")) {
				System.out.println("carta perde contro forbice");
			} else if(b.equals("sasso")) {
				System.out.println("carta vince contro sasso");
			}
				else System.out.println(b +" non è un argomento valido");			}
		
		if(a.equals("sasso")){
			if(b.equals("carta")) {
				System.out.println("sasso perde contro carta");
			} else if(b.equals("forbice")) {
				System.out.println("sasso vince contro forbice");
			}
				else System.out.println(b +" non è un argomento valido");			}
	}
}