package it.beije.oort.franceschi.rubrica;

import it.beije.oort.franceschi.rubrica.lettore.Lettore;

public class Main {
	
	private static String fileNomi = "\\Code\\Oort\\cose\\nomi.txt";
	private static String fileCognomi = "\\Code\\Oort\\cose\\nomi.txt";
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		String[] nomi = Lettore.getArrayValori(fileNomi);
		String[] cognomi = Lettore.getArrayValori(fileCognomi);


	}
	
}
