package it.beije.oort.franceschi.csvToXml;

public class Main {

	public static void main(String[] args) {
		System.out.println("Programma avviato.");
		InputManager im = new InputManager();
		for (int i = 0; i < im.getInputAmount(); i++) {
			CSVReader r = new CSVReader(im.getNextInputPath());
			XMLWriter.writeList(r.creaListaContatti(), im.getNextOutputPath());
		}
		System.out.println("Programma concluso.");

		/*
		 * TODO aggiungere input dall'utente per definire il divisore e l'eventuale
		 * presenza delle virgolette Virgolette: chiede all'utente se ci sono o no, in
		 * caso di sì attiva boolean che elimina il primo e l'ultimo carattere da ogni
		 * parola trimmata nel ciclo Divisore: semplicemente salvare in una variabile
		 * char o String il divisore immesso dall'utente e usarlo nel trim
		 */
	}
}