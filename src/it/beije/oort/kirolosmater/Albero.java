package it.beije.oort.kirolosmater;

public class Albero {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length == 0) {
			System.out.println("Non hai inserito nulla");
		} else {
			int x = Integer.parseInt(args[0]);
			String simbolo = "#";
			String output = "";
			for (int i = 0; i < x; i++) {
				output += simbolo;
				System.out.println(output);
			}
		}
	}

}
