package it.beije.oort.file.sala.first;

public class Tabellina {

	public static void main(String[] args) {
		if(args.length!=0) {
			int x = Integer.parseInt(args[0]);
			for(int i = 0;i<11;i++) {
				System.out.println(x+" x "+i+" = "+x*i);
			}
		}
	}
}
