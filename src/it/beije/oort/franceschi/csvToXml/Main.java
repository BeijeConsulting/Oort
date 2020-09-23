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
    }

}