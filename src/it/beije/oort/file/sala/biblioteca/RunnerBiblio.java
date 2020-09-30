package it.beije.oort.file.sala.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.time.Year;
import java.time.LocalDate;

public class RunnerBiblio {

	public static void main(String[] args) {
//		List<Databasable> insertList = new ArrayList<Databasable>();
//		insertList.add(new Libro(null, null, null, "Libro1", "descrizione libro 1", new Short((short)2010)));
//		insertList.add(new Libro(null, null, null, "Libro2", "descrizione libro 2", new Short((short)2015)));
//		insertList.add(new Libro(null, null, null, "Libro3", "descrizione libro 3", new Short((short)1987)));
//		JPAToolset.insertJPA(insertList);
		
		JPAToolset.deleteJPA("Libro", 2);
		List<Databasable> list = JPAToolset.selectJPA("Libro", "descrizione", "");
//		if(list.get(0) instanceof Libro) System.out.println("Instance of Libro");
		for(Databasable d : list) {
			System.out.println(d.toString());
		}
	}

}
