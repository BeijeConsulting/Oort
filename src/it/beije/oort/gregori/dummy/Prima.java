package it.beije.oort.gregori.dummy;
/**
 * Passare una serie di argomenti al main e stamparli a schermo
 * Provare tutti i cicli
 * 
 * @author Luca Gregori
 *
 */
public class Prima {
	public static void main(String[] args) {
		
		if(args.length == 0) {
			System.out.println("Non vi sono membri nel gruppo.");
		}
		else {
			System.out.println("Gruppo di lavoro:");
			System.out.println();
			
			// Testing ciclo for
			for(int i = 0; i < args.length; i++) {
				System.out.println(i + ": " + args[i] + " \t(from for loop)");
			}
			
			// Testing ciclo while
			System.out.println();
			int i = 0;
			while(i < 3) {
				if(i == 0) System.out.println("It might not enter here.");
				System.out.println(i + ": " + args[i] + " \t(from while loop)");
				i++;
			}
			
			// Testing ciclo do-while
			System.out.println();
			i = 0;
			do {
				if(i == 0) System.out.println("It will enter here for sure.");
				System.out.println(i + ": " + args[i] + " \t(from do-while loop)");
				i++;
			} while(i < 3);
			
			// Testing for-each
			System.out.println();
			for(String nome : args) {
				System.out.println(nome + " \t(from for-each loop)");
			}
			
			// Testing operatore ternario
			System.out.println();
			System.out.println((args[0].equals(args[1])) ? (args[0] + " e " + args[1] + " sono omonimi.") : (args[0] + " e " + args[1] + " non sono omonimi."));
			System.out.println((args[1].equals(args[2])) ? (args[1] + " e " + args[2] + " sono omonimi.") : (args[1] + " e " + args[2] + " non sono omonimi."));
			System.out.println((args[0].equals(args[2])) ? (args[0] + " e " + args[2] + " sono omonimi.") : (args[0] + " e " + args[2] + " non sono omonimi."));	
		
			// Testing break
			System.out.println();
			for(String nome : args) {
				System.out.println(nome + " \t(from for-each loop with break)");
				if(nome.equals("Luca")) {
					System.out.println("Break tested here.");
					break;
				}
			}
			
			// Testing break with label
			System.out.println();
			USELESS_LABEL: for(String nome : args) {
				System.out.println(nome + " \t(from for-each loop with break)");
				if(nome.equals("Luca")) {
					System.out.println("Break with an useless label tested here.");
					break USELESS_LABEL;
				}
			}
		}
	}
}
