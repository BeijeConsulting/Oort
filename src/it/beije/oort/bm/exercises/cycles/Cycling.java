package it.beije.oort.bm.exercises.cycles;

import java.util.ArrayList;
import java.util.Iterator;

public class Cycling {

	public static void main(String[] args) {
		if(args.length == 0) {
			System.out.println("Correct usage: java Cycling <params>");
			System.exit(0);
		}
		ArrayList<String> params = new ArrayList<>();
		for(String s : args) {
			params.add(s);
		}
		System.out.println("Printing with while cycle...");
		System.out.println();
		Iterator<String> it = params.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		System.out.println();
		System.out.println("Printing with do-while cycle...");
		System.out.println();
		it = params.iterator();
		do {
			System.out.println(it.next());
		} while(it.hasNext());
		System.out.println();
		System.out.println("Printing with for cycle...");
		System.out.println();
		for(int i = 0; i<args.length; i++) {
			System.out.println("for iteration #"+ i + ": " + args[i]);
		}
		System.out.println();
		System.out.println("Printing with foreach cycle...");
		System.out.println();
		for(String s : args) {
			System.out.println(s);
		}
	}

}
