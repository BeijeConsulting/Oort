package it.beije.oort.file.sala;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScannerContatto {

	private List<Contatto> listContatti;
	
	private String sanitizer(String s) {
		return s;
	}
	
	private void overWrite(Contatto c) {
		listContatti.set(listContatti.size(), c);
	}
	
	public void routine() {
		boolean flag = true;
		List<Contatto> temp = new ArrayList<>();
		Scanner s = new Scanner(System.in);
		String exitToken = ":Q";
		String bufferNome, bufferCognome, bufferTelefono, bufferEmail;
		System.out.println("Utility per inserimento contatti manuale.");
		while(flag) {
			
			System.out.println("Inserire il nome per il prossimo contatto:");
			bufferNome = sanitizer(s.nextLine());
			System.out.println("Inserire il cognome per il prossimo contatto:");
			bufferCognome = sanitizer(s.nextLine());
			System.out.println("Inserire il telefono per il prossimo contatto:");
			bufferTelefono = sanitizer(s.nextLine());
			System.out.println("Inserire l'email per il prossimo contatto:");
			bufferEmail = sanitizer(s.nextLine());
			
			if(bufferCognome.equalsIgnoreCase("") && bufferEmail.equalsIgnoreCase("") &&
					bufferNome.equalsIgnoreCase("") && bufferTelefono.equalsIgnoreCase("")) {
				System.out.println("Impossibile creare contatto con 4 campi vuoti, fornire almeno un campo non vuoto:");
				continue;
			}
			System.out.println("Cosa desideri fare con quest'ultimo contatto appena inserito?\n"+
			"Digita 1 per scartarlo e procedere con un nuovo inserimento.\n" +
			"Digita 2 per sovrascriverlo all'ultimo contatto inserito e procedere con un nuovo inserimento.\n" +
			"Digita 3 per salvarlo e procedere con un nuovo inserimento.\n" +
			"Digita 4 procedere al salvataggio della lista contatti.\n" +
			"Digita :q per scartare tutti i dati ed uscire dall'utility.");
			String buffer = s.nextLine();
			if(buffer.equalsIgnoreCase(exitToken)) {
				flag=false;
				System.out.println("Terminating.");
				continue;
				
			}else if(buffer.equals("1")) continue;
			else if(buffer.equals("2")) overWrite(new Contatto(bufferNome, bufferCognome, bufferTelefono, bufferEmail));
			
			if(bufferCognome.equalsIgnoreCase(exitToken) || bufferEmail.equalsIgnoreCase(exitToken) ||
					bufferNome.equalsIgnoreCase(exitToken) || bufferTelefono.equalsIgnoreCase(exitToken)) {
				flag=false;
				System.out.println("Terminating.");
				continue;
			}
			
			
			System.out.println("###->"+bufferCognome + bufferEmail + bufferNome + bufferTelefono);
		}
		s.close();
	}
	
	public static void main(String[] args) {
		new ScannerContatto().routine();
	}

}
