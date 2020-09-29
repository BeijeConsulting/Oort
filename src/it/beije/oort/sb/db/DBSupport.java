package it.beije.oort.sb.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import it.beije.oort.rubrica.Contatto;

public class DBSupport {
	static Scanner sc = new Scanner(System.in);
	static final String PATH ="./src/it/beije/oort/sb/db/";
	
	public static List<String> visualizza() {
		List<String> list = new ArrayList<String>(0);
		System.out.println("Digita per quale campo vuoi ricercare tra Nome Cognome Email o Telefono");
		String campo1 = sc.next();
		System.out.println("Vuoi ricercare anche secondo un altro campo? Digitare si o no");
		
		if(sc.next().equalsIgnoreCase("si")) {
			System.out.println("Digita il nome del secondo campo");
			String campo2=sc.next();
			System.out.println("Vuoi ricercare anche per un terzo campo? Digitare si o no");
			
			if(sc.next().equalsIgnoreCase("si")) {
				System.out.println("Digita il nome del terzo campo");
				String campo3=sc.next();
				System.out.println("Ora inserisci quale "+campo3+" vuoi ricercare");
				String att3 = sc.next();
				list.add(campo3);
				list.add(att3);
				}
			
				System.out.println("Ora inserisci quale "+campo2+" vuoi ricercare");
				String att2 = sc.next();
				list.add(campo2);
				list.add(att2);
				}
		
		System.out.println("Ora inserisci quale "+campo1+" vuoi ricercare");
		String att1 = sc.next();
		list.add(campo1);
		list.add(att1);
		return list;
	}
	
	public static void listPrinterWithIndex(List<Contatto> list) {
		int numPag=1;
	CICLO :	for(int i = 0; i < list.size(); i+=30) {
			for(int count=i;count<i+30&&count<list.size();count++) {
				System.out.print("indice : " + indexList().get(count) + " ");
				System.out.println(list.get(count));
				if(count==list.size()-1) {
					System.out.println("numero della pagina: " + numPag);
					System.out.println("\nterminato il numero dei contatti");

					return;
				}		
			}
		System.out.println("numero della pagina: " + numPag);
		numPag++;
		System.out.println("\nse vuoi vedere i prossimi trenta contatti digita next. In caso contrario digita stop");
		String temp = "";
		while(temp.toLowerCase()!="next" || temp.toLowerCase()!= "stop") {
			temp = sc.next();
			if(temp.equalsIgnoreCase("next")) continue CICLO;
			else if(temp.equalsIgnoreCase("stop")) break CICLO;
			else System.out.println("Non ho compreso il comando inserito, digita stop o next");
			}
		}
	}
	
	//stampa i contatti coi vari indici
	public static void listViewPrinter(List<Contatto> list, List<Integer> indici) {
		int numPag=1;
	CICLO:	for(int i = 0; i < list.size(); i+=30) {
			for(int count=i;count<i+30&&count<list.size();count++) {
				System.out.print("indice: " + indici.get(count)+" ");
				System.out.println(list.get(count));
				if(count==list.size()-1) {
					System.out.println("numero della pagina: " + numPag);
					System.out.println("\nterminato il numero dei contatti");

					return;
				}		
			}
		System.out.println("numero della pagina: " + numPag);
		numPag++;
		System.out.println("\nse vuoi vedere i prossimi trenta contatti digita next. In caso contrario digita stop");
		String temp = "";
		while(!temp.toLowerCase().equalsIgnoreCase("next") || !temp.toLowerCase().equalsIgnoreCase("stop")) {
			temp = sc.next();
			if(temp.equalsIgnoreCase("next")) continue CICLO;
			else if(temp.equalsIgnoreCase("stop")) break CICLO;
			else System.out.println("non ho compreso il comando inserito, digita stop o next");
			}
		}
	}
	// mi ridà una lista con gli indici dei contatti del db
	public static List<Integer> indexList() {
		List<Integer> list = new ArrayList<Integer>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;		
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);					
			ps = connection.prepareStatement("SELECT id FROM rubrica");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				list.add(rs.getInt("id"));
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	//mi ridà la stringa col nome su cui salvare il file
	public static StringBuilder export() {
		StringBuilder temp = new StringBuilder(PATH);
		System.out.println("Inserisci il nome del file su cui salvare la rubrica");
		temp.append(sc.next());
		System.out.println("Ora digita l'estensione, se xml o csv");
		String s = "";
		while(!s.equals("xml") || !s.equals("csv")) {
		s = sc.next();
		if(!s.equals("xml") && !s.equals("csv")) {
			System.out.println("Non riconosco l'estensione, lavoro solo con csv o xml");
			continue;
			} else break;
		}
		temp.append(".").append(s);
		return temp;
	}
	
	public static StringBuilder importer() {
		StringBuilder temp = new StringBuilder(PATH);
		System.out.println("Inserisci il nome del file da cui vuoi importare la rubrica");
		temp.append(sc.next());
		System.out.println("Ora digita l'estensione, se xml o csv");
		String s = "";
		while(!s.equals("xml") || !s.equals("csv")) {
		s = sc.next();
		if(!s.equals("xml") && !s.equals("csv")) {
			System.out.println("Non riconosco l'estensione, lavoro solo con csv o xml");
			continue;
			} else break;
		}
		temp.append(".").append(s);
		return temp;
	}
}
