package it.beije.oort.sb.db;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.DOMException;

import it.beije.oort.rubrica.Contatto;
import it.beije.oort.rubrica.RubricaCsvXml;

public class ClientRubricaDB {
	static Scanner sc = new Scanner(System.in);


	
	public static Contatto cWriter() {
		String temp = "";
		Contatto nullo = new Contatto("","","","");
			Contatto c = new Contatto();				
			System.out.println("inserisci il nome del Contatto");
			temp = sc.nextLine();
			c.setNome(temp);
			System.out.println("inserisci il cognome del Contatto");
			temp = sc.nextLine();
			c.setCognome(temp);
			System.out.println("inserisci l'email del Contatto");
			temp = sc.nextLine();
			c.setEmail(temp);
			System.out.println("inserisci il numero di telefono del Contatto");
			temp = sc.nextLine();
			c.setTelefono(temp);
			if(c.myEquals(c, nullo)) { 
				System.out.println("mi serve almeno un campo non vuoto \n");
				c = cWriter();
			}  return c;
			
	}
	

	
	
	public static void modifier(List<Contatto> list, int ind) {
		System.out.println("Dimmi quale campo vuoi modificare");
		String temp = sc.next();
		System.out.println("ora dammi l'attributo da inserire");
		String att = sc.next();
		switch(temp.toLowerCase()) {
		case ("nome") :
			list.get(ind).setNome(att);
			break;
		case ("cognome") :
			list.get(ind).setCognome(att);
			break;
		case ("email") :
			list.get(ind).setEmail(att);
			break;
		case ("telefono") :
			list.get(ind).setTelefono(att);
			break;
		default :
			System.out.println("non ho trovato il campo da modificare");
		}
	}
	
	
	public static void deleteContact(int ind) {
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);
			ps = connection.prepareStatement("delete from rubrica where id ="+ind);
			ps.execute();

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} sc.nextLine();
	}
	

	
	
	public static void consoleModifier(List<Contatto> list) {
		System.out.println("Scegli quale contatto modificare tramite l'indice");
		DBSupport.listPrinterWithIndex(list);
		System.out.println("Digita l'indice del contatto da modificare");
		int indice = sc.nextInt();
		int indM = DBSupport.indexList().indexOf(indice);
		modifier(list, indM);
		deleteContact(indice);
		DBimporter.individualInsert(list.get(indM), indice);
		System.out.println("Contatto modificato");
	}
	
	public static void consoleDeleter(List<Contatto> list) {
		System.out.println("Scegli quale contatto cancellare tramite l'indice");
		DBSupport.listPrinterWithIndex(list);
		System.out.println("Digita l'indice del contatto da cancellare");
		int ind = sc.nextInt();
		deleteContact(ind);
		System.out.println("contatto cancellato \n");
	}
	
	public static void client() throws DOMException, IOException, ParserConfigurationException, TransformerException {
		System.out.println("Buongiorno, questo è un comodo tool per importare e/o esportare contatti da un DB \n");		
		String concl = "";
		List<Contatto> list = new ArrayList<Contatto>();
		List<String> campi = new ArrayList<String>();
		List<Integer> indici = new ArrayList<Integer>();
		while(!concl.equalsIgnoreCase("quit")) {
			System.out.println("Cosa vuoi fare? Scegli tra: \n-Visualizza : per visualizzare tutti i contatti con determinate caratteristiche, \n-Modifica : per modificare un contatto già presente nel database,");
			System.out.println("-Cancella : per cancellare un contatto presente nel database, \n-Inserisci : per inserire un nuovo contatto in fondo alla rubrica del database,");
			System.out.println("-Export : per salvare la rubrica su file (xml o csv), \n-Quit : se vuoi concludere la sessione in corso.");
			switch(sc.nextLine().toLowerCase()) {
			case "visualizza" :
				campi = DBSupport.visualizza();
				if(campi.size()==6) list = DBexporter.visualizzaList(campi.get(0), campi.get(1), campi.get(2), campi.get(3), campi.get(4), campi.get(5), indici);
				else if(campi.size()==4) list = DBexporter.visualizzaList(campi.get(0), campi.get(1), campi.get(2), campi.get(3), indici);
				else if(campi.size()==2) list = DBexporter.visualizzaList(campi.get(0), campi.get(1), indici);
				DBSupport.listViewPrinter(list, indici);
				if(list.size()==0) System.out.println("non ho trovato nessun contatto con queste specifiche");
				list.clear();
				indici.clear();
				break;
			case "inserisci" :
				DBimporter.individualInsert(cWriter());
				System.out.println("Contatto inserito in fondo alla rubrica");
				break;
			case "modifica" :
				consoleModifier(DBexporter.preparedSelect());
				break;
			case "export" :
				File file = new File(DBSupport.export().toString());
				RubricaCsvXml.rubricaWriter(file, DBexporter.preparedSelect());
				System.out.println("rubrica salvata");
				break;
			case "cancella" :
				consoleDeleter(DBexporter.preparedSelect());
				break;
			case "quit" :
				concl = "quit";
				System.out.println("grazie per averci usato!");
				break;
			default :
				System.out.println("non ho riconosciuto il comando");
			}

		}
		sc.close();
}
		public static void main(String[] args) throws DOMException, IOException, ParserConfigurationException, TransformerException {
		client();
		}
}
