package it.beije.oort.file.rubrica;

public class Main {
	public static void main(String[] args) {
		GeneratoreRubrica.creaRubrica(Valori.getNomi(), Valori.getCognomi());
		GeneratoreRubrica.writeRubrica(Valori.contatti, Valori.getOutputPath());
	}
}
