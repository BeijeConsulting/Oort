package kirolosmater;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class OurRubrica {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File namesFile = new File("/temp/nomi_italiani.txt");
		File surnamesFile = new File("/temp/cognomi.txt");
		
		String[] nomi = {"kirolos", "simone", "daniel"};
		String[] cognomi = {"mater", "maisto", "bassanelli"};
		String[] prefissi = {"345", "346", "347", "348", "349"};
		String[] domini = {"gmail.com", "hotmail.com", "hotmail.it", "libero.it", "yahoo.com", "tim.it",  "alice.it"};
 		
		List<String> listaNomi = Arrays.asList(nomi);
		List<String> listaCognomi = Arrays.asList(cognomi);
		List<String> listaPrefissi = Arrays.asList(prefissi);
		List<String> listaDomini = Arrays.asList(domini);
		List<String> listaRecord = new ArrayList<String>();
		Random r = new Random();
		String record;
		String numero;
//		for (int i = 0; i <= 1000; i++) {
//			r = new Random();
//			numero = listaPrefissi.get(r.nextInt(5)) + r.nextInt(9999999);
//			record = listaNomi.get(r.nextInt(3)) + listaCognomi.get(r.nextInt(3));
//			System.out.println(record);
//			listaRecord.add(record);
//		}
		System.out.println(generazioneNumero(listaPrefissi));
	}
	public static String generazioneNumero(List<String> args) {
		Random posizionePrefisso = new Random();
		Random suffisso = new Random();
		String prefisso = args.get(posizionePrefisso.nextInt(5));
		String risultato = "" + prefisso + suffisso.nextInt(9999999);
		return risultato;
	}
}

