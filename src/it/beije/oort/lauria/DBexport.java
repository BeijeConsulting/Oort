package it.beije.oort.lauria;

import java.util.ArrayList;
import java.util.List;

public class DBexport {

	public static void main(String[] args) {
		
		String tableName = "contatti_busseni";
		
		List<Contatto> recordContatti = new ArrayList<Contatto>();
		
		DBtools.preparedSelect(tableName, recordContatti);
		
		System.out.println(recordContatti.size());
		
		
	}

}
