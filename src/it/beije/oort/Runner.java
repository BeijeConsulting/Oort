package it.beije.oort;

import it.beije.oort.rubrica.Contatto;

public class Runner {

	public static void main(String[] args) {
//		Contatto contatto = new Contatto();
//		contatto.setNome("Mario");
//		contatto.setCognome("Rossi");
//		contatto.setTelefono("3337658390");
//		contatto.setEmail("mario.rossi@tim.it");
		
		Contatto contatto1 = new Contatto("Carlo", "Bianchi", "3337658231");
		Contatto contatto2 = new Contatto("Mario", "Rossi", "3337658390", "mario.rossi@tim.it");
		
		System.out.println(contatto1);
		System.out.println(contatto2);
	}

}
