package it.beije.oort.file.sala.first;

public class Pyramid {

	public static void main(String[] args) {
		
		if(args.length!=0) {
			String pyramid = "";
			int x = Integer.parseInt(args[0]);
			for(int i=0;i<x;i++) {
				System.out.println(pyramid+="#");
			}
		}
		
	}
}
