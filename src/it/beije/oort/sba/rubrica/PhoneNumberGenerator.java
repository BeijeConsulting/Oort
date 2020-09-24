package it.beije.oort.sba.rubrica;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PhoneNumberGenerator {
	private List<String> listaNumeriDiTelefono = new ArrayList<>();
	private ListRandomSelector selettore;
	
	public PhoneNumberGenerator(List<String> prefissi) {
		selettore = new ListRandomSelector(prefissi);
	}
	
	//il metodo nextNumber restituisce una stringa contenente un numero di telefono.
	public String nextNumber() {
		Random r = new Random();
		StringBuilder b = new StringBuilder();
		//genero un numero casuale da 1 a 8
		int rand = r.nextInt(8)+1;
		//se il numero è 1 NON generare alcun numero e lasciare il campo vuoto.
		if(rand < 2) {
			return "";
		//se il numero è 2 NON generare nuovo numero ma pescare in modo randomico un telefono dalla List telefoni.
		}else if(rand < 3) {
			return selectRandomFromGenerated();
		//se il numero è nell'intervallo [3,4] generare un nuovo numero anteponendo +39.
		}else if(rand < 5){
			b.append("+39");
		}
		/* se non è una delle precedenti condizioni, generare un nuovo numero. 
		 * appendo a b un prefisso a caso dalla lista prefissi, passata al costruttore.*/
		b.append(selettore.getNext());
		for(int i = 0; i<7; i++) {
			// genero il numero di telefono appendendo a b una serie di numeri random presi da 0 a 9.
			b.append("" + r.nextInt(10));
		}
		String ret = b.toString();
		//aggiungo alla Lista dei numeri di telefono il numero appena generato.
		listaNumeriDiTelefono.add(ret);
		return ret;
	}
	
	//Questo metodo pesca un numero di telefono random dalla lista NumeriDiTelefono.
	private String selectRandomFromGenerated() {
		if(listaNumeriDiTelefono.isEmpty()) return "";
		else {
			ListRandomSelector lrs = new ListRandomSelector(listaNumeriDiTelefono);
			return lrs.getNext();
		}
	}
}