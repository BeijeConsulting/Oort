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
	
	public static List<String> visualizza() {
		List<String> list = new ArrayList<String>(0);
		System.out.println("digita per quale campo vuoi ricercare tra Nome Cognome Email o Telefono");
		String campo1 = sc.next();
		System.out.println("vuoi ricercare anche secondo un altro campo? Digitare si o no");
		
		if(sc.next().equalsIgnoreCase("si")) {
			System.out.println("digita il nome del secondo campo");
			String campo2=sc.next();
			System.out.println("vuoi ricercare anche per un terzo campo? Digitare si o no");
			
			if(sc.next().equalsIgnoreCase("si")) {
				System.out.println("digita il nome del terzo campo");
				String campo3=sc.next();
				System.out.println("ora inserisci l'attributo di "+campo3);
				String att3 = sc.next();
				list.add(campo3);
				list.add(att3);
				}
			
				System.out.println("ora inserisci l'attributo di "+campo2);
				String att2 = sc.next();
				list.add(campo2);
				list.add(att2);
				}
		
		System.out.println("ora inserisci l'attributo di "+campo1);
		String att1 = sc.next();
		list.add(campo1);
		list.add(att1);
		return list;
	}
	
	public static void listPrinterWithIndex(List<Contatto> list) {
		int numPag=1;
		for(int i = 0; i < list.size(); i+=30) {
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
		System.out.println("\nse vuoi vedere i prossimo trenta contatti digita next");
		if(sc.next().equalsIgnoreCase("next")) continue;
		else break;
		}
	}
	
	
	public static void listViewPrinter(List<Contatto> list, List<Integer> indici) {
		int numPag=1;
		for(int i = 0; i < list.size(); i+=30) {
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
		System.out.println("\nse vuoi vedere i prossimo trenta contatti digita next");
		if(sc.next().equalsIgnoreCase("next")) continue;
		else break;
		}
	}
	
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
}
