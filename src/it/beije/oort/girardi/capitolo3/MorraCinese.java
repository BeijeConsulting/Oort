package it.beije.oort.girardi.capitolo3;

public class MorraCinese {

	public static String gioca(String giocatore1, String giocatore2) {
		// carta = c, forbice = f, sasso = s.
		char g1 = giocatore1.charAt(0);
		char g2 = giocatore2.charAt(0);
		String partita = new StringBuilder().append(g1).append(g2).toString();
		String risultato = giocatore1;
		switch (partita) {
		case "cf":
			risultato += " perde contro ";
			break;
		case "cs":
			risultato += " batte ";
			break;
		case "fc":
			risultato += " batte ";
			break;
		case "fs":
			risultato += " perde contro ";
			break;
		case "sc":
			risultato += " batte ";
			break;
		case "sf":
			risultato += " perde contro ";
			break;
		default:
			risultato += " pareggia contro ";
		
		}
		return risultato + giocatore2;
	}
	
	// main
	public static void main (String[] args) {
		String g1 = "forbice";
		String g2 = "carta";
		System.out.println(gioca(g1,g2));
	}
}
