package it.beije.oort.lauria.db;

import java.util.ArrayList;
import java.util.List;

import it.beije.oort.lauria.Contatto;

public class DBexport {

	public static void main(String[] args) {
		
		String tableName = "contatti_busseni";
		
		List<Contatto> recordContatti = new ArrayList<Contatto>();
		
		DBtools.preparedSelect(tableName, recordContatti);
		
		System.out.println(recordContatti.size());
		
		
	}

}
