package it.beije.oort.franceschi.csvToXml;

public class Main {

	public static void main(String[] args) {
		System.out.println("Programma avviato.");

		for (int i = 0; i < 1; i++) {
			Convertitore.csvToXml(i);
		}
		
		Appenditore.unisciFileEOrdina("C:\\Code\\Oort\\csv\\rubriche\\rubrica_brugaletta.csv",
				"C:\\Code\\Oort\\xml\\rubriche\\rubrica_brugaletta.xml", 
				"C:\\Code\\Oort\\xml\\rubriche\\rubrica_brugaletta_unito.xml");

//		for (int i = 0; i < InputManager.getInputAmount(); i++) {
//			Convertitore.xmlToCsv(i);;
//		}

//		Appenditore.append("C:\\Code\\Oort\\csv\\rubriche\\rubrica_brugaletta.csv",
//				"C:\\Code\\Oort\\xml\\rubriche\\rubrica_brugaletta.xml");

		System.out.println("Programma terminato.");

		/*
		 * TODO aggiungere input dall'utente per definire il divisore e l'eventuale
		 * presenza delle virgolette Virgolette: chiede all'utente se ci sono o no, in
		 * caso di sì attiva boolean che elimina il primo e l'ultimo carattere da ogni
		 * parola trimmata nel ciclo Divisore: semplicemente salvare in una variabile
		 * char o String il divisore immesso dall'utente e usarlo nel trim
		 */
	}

}