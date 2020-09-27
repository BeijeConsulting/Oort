package it.beije.oort.sb.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import it.beije.oort.rubrica.Contatto;
public class ConsoleWriter {
	static Scanner sc = new Scanner(System.in);
		
		
		public static Contatto contattoWriter() {
			String temp = "";
			Contatto nullo = new Contatto("","","","");
				Contatto c = new Contatto();				
				System.out.println("type the name the Contact");
				temp = sc.nextLine();
				c.setNome(temp);
				System.out.println("type the surname the Contact");
				temp = sc.nextLine();
				c.setCognome(temp);
				System.out.println("type the email the Contact");
				temp = sc.nextLine();
				c.setEmail(temp);
				System.out.println("type the number the Contact");
				temp = sc.nextLine();
				c.setTelefono(temp);
				if(c.myEquals(c, nullo)) System.out.println("non hai messo neanche un campo");
				return c;
	}
		
		
		private static void listPrinter(List<Contatto> list) {
			for(int i = 0; i < list.size(); i++) {
			System.out.print("indice : " + i + " ");
			System.out.println(list.get(i));
			}
		}
		
		
		
		public static void modificatore(List<Contatto> list, int ind) {
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
			}sc.nextLine();
		}
		
		
		
		public static List<Contatto> ConsoleHandler() {
//			System.out.println("iniziamo a creare il primo contatto");
			String concl = "";
			List<Contatto> list = new ArrayList<Contatto>();
			Contatto c = new Contatto();
//			c = contattoWriter();
			while(!concl.equalsIgnoreCase("quit")) {
				System.out.println("Cosa vuoi fare ora? Scegli tra Modifica Nuovo Annulla o Salva, se vuoi concludere digita quit");
				switch(sc.nextLine().toLowerCase()) {
				case "annulla" :
					list.remove(list.size()-1);
					break;
				case "nuovo" :
					c = contattoWriter();
					list.add(c);
					break;
				case "modifica" :
					System.out.println("Scegli quale contatto modificare tramite l'indice");
					listPrinter(list);
					System.out.println("Digita l'indice del contatto da modificare");
					int indice = sc.nextInt();
					modificatore(list, indice);
					break;
				case "salva" :
					//da fare
					break;
				case "quit" :
					concl = "quit";
					break;
				}

			}
			sc.close();
			listPrinter(list);
			return list;

		}
		
		
			public static void main(String[] args) {
				List<Contatto> c = ConsoleHandler();
			}
}
