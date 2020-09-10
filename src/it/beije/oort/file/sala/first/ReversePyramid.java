package it.beije.oort.file.sala.first;

public class ReversePyramid {

	public static void main(String[] args) {
		if(args.length!=0) {
			int x = Integer.parseInt(args[0]);
			for(;x>0;x--) {
				for(int i=x;i>0;i--) {
					System.out.print("*");
				}
				System.out.println("");
			}
		}
	}
}
