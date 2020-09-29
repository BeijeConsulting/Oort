package it.beije.oort.lauria;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapClass {
	private static final String PATH_FILES = "C:\\Users\\Padawan06\\Documenti\\temp\\";
	
	public static void main(String[] args) throws IOException {
	
		File fileRubrica = new File(PATH_FILES + "rubricaDuplicatiIvo.csv");
		
		List<Contatto> recordContatti = new ArrayList<>();
		
		Map<String, Contatto> rubricaMap = new HashMap<>();
	 
		recordContatti = CsvParser.readContattiCsv(fileRubrica);
		
		System.out.println("Numero totale di contatti in rubrica: "+recordContatti.size());  
		
		Contatto duplicato;
		
		for(Contatto contattoTemp : recordContatti) {
			duplicato = rubricaMap.put(contattoTemp.getEmail(), contattoTemp);
			if(duplicato != null) {
				MapClass.joinContacts(duplicato, contattoTemp);
				rubricaMap.put(duplicato.getEmail(), duplicato);
			}
		}
		
		System.out.println("Numero totale di contatti in mappa: "+rubricaMap.size()); 
		System.out.println(rubricaMap);
		
	}

	public static Contatto joinContacts(Contatto duplicato, Contatto temp) {
		
		StringBuilder duplicatiList = new StringBuilder();
		
		
			if(duplicato.getNome().equalsIgnoreCase("")) {
				if(!temp.getNome().equalsIgnoreCase("")) {
					duplicato.setNome(temp.getNome());
				}
			}else {
				duplicatiList.append(temp.getNome());
			}
			if(duplicato.getCognome().equalsIgnoreCase("")) {
				if(!temp.getCognome().equalsIgnoreCase("")) {
					duplicato.setCognome(temp.getCognome());
				}
			}else {
				duplicatiList.append(" ").append(temp.getCognome()).append(",");
			}
			if(duplicato.getTelefono().equalsIgnoreCase("")) {
				if(!temp.getTelefono().equalsIgnoreCase("")) {
					duplicato.setTelefono(temp.getTelefono());
				}
			}
		
		//duplicato.setDuplicatiEmail(duplicatiList.toString());
		return duplicato;
	}

}

