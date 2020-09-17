package it.beije.oort.file.rubrica;

import java.util.Random;

public class GeneraNumero {
	public static String generaNumero() {
		Random r = new Random();
		StringBuilder suffisso = new StringBuilder();
		for(int i = 0; i < 7; i++) {
			suffisso.append(r.nextInt(10));
		}
		
		int n = r.nextInt(8) + 1;
		
		if (n == 1) {
			return "";
		} else if (n == 2 && Valori.cellCompleti.size() > 0) {
			return Valori.cellCompleti.get(r.nextInt(Valori.cellCompleti.size() - 1));
		} else if (n <= 4) {
			return "+39" + Valori.getPrefisso(r.nextInt(5)) + suffisso;
		} else {
			return Valori.getPrefisso(r.nextInt(5)) + suffisso;
		}
	}
}
