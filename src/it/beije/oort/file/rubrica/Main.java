package it.beije.oort.file.rubrica;

public class Main {
	public static void main(String[] args) {
		GeneratoreRubrica.creaRubricaRandom();
		GeneratoreRubrica.writeRubrica(Valori.contatti, Valori.getOutputPath());
	}
}
