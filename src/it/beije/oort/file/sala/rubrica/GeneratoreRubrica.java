package it.beije.oort.file.sala.rubrica;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class GeneratoreRubrica {
	private static List<String> listaNumeriTelefono;
	private static List<String> listaEmail;

	public GeneratoreRubrica() {
		listaNumeriTelefono = new ArrayList<String>();
		listaEmail = new ArrayList<String>();
	}
	
	private String generaTelefono() {
		Random rr = new Random();
		StringBuilder tel = new StringBuilder("");
		int randomizer = rr.nextInt(8);	
		if(randomizer == 1) {
			if(!listaNumeriTelefono.isEmpty()) {
				tel.append(listaNumeriTelefono.get(rr.nextInt(listaNumeriTelefono.size())));
			}
		}
		else if(randomizer == 2 || randomizer ==3) {
			tel.append("+39");
			tel.append(Valori.getPrefisso(rr.nextInt(5)));
			for(int i = 0; i < 7; i++) {
				tel.append(rr.nextInt(10));
			}
			listaNumeriTelefono.add(tel.toString());
		}
		else if(randomizer>3 && randomizer<8){
			tel.append(Valori.getPrefisso(rr.nextInt(5)));
			for(int i = 0; i < 7; i++) {
				tel.append(rr.nextInt(10));
			}
			listaNumeriTelefono.add(tel.toString());
		}
		return tel.toString();
	}
	
	private String generaEmail(String nome, String cognome) {
		StringBuilder email = new StringBuilder("");
		Random rrr = new Random();
		int randomizer = rrr.nextInt(10);
		if(randomizer >= 2 && randomizer <= 4) {
			if(!listaEmail.isEmpty()) {
				email.append(listaEmail.get(rrr.nextInt(listaEmail.size())));
			}
		} else if(randomizer > 4 && randomizer <10) {
			email.append(GeneraMail.generaMail(nome, cognome, Valori.getDominio(rrr.nextInt(8))));
			listaEmail.add(email.toString());
		}
		return email.toString();
	}
	
	public List<Contatto> generaRubrica(ArrayList<String> nomi,
			ArrayList<String> cognomi, int dimensione) {
		
		List<Contatto> list = new ArrayList<Contatto>();
		Random r = new Random();
		
		for(int i=0;i<dimensione;i++) {
			String nome = nomi.get(r.nextInt(nomi.size()));
			String cognome = cognomi.get(r.nextInt(cognomi.size()));
			list.add(new Contatto(
						nome, 
						cognome,
						generaTelefono(),
						generaEmail(nome, cognome)));
		}
		return list;
	}
	
	public List<Contatto> generaRubrica(ArrayList<String> nomi,
			ArrayList<String> cognomi) {
		return generaRubrica(nomi, cognomi, 100);
	}
}