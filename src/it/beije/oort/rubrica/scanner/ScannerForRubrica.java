package it.beije.oort.rubrica.scanner;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import it.beije.oort.rubrica.Contatto;

public class ScannerForRubrica {
	List<Contatto> contatti = new ArrayList<>();
	static StringBuilder contlist = new StringBuilder();
	Scanner scan = new Scanner(System.in);
	boolean flag = true;
	String c = "";
	String s = "";
	
	public void scannMethod(StringBuilder contlist) {
		do {
			System.out.println("Generatore contatti per la rubrica!");
			System.out.println("Inserire i seguenti campi: ");
			System.out.print("- Nome: ");
			String nome = scan.nextLine(); 
			System.out.print("- Cognome: ");
			String cognome = scan.nextLine(); 
			System.out.print("- Telefono: "); 
			String telefono = scan.nextLine(); 
			System.out.print("- Email: ");
			String email = scan.nextLine();	
			System.out.println();
			if(nome.equalsIgnoreCase("") && cognome.equalsIgnoreCase("") && telefono.equalsIgnoreCase("")&& email.equalsIgnoreCase("")) {
				System.out.println("Non è stato inserito nessuno campo. Ripetere l'inserimento con almeno un valore!");	
			}else {
				System.out.println("Sceglieri quale opzione si vuole eseguire, digitando uno dei seguenti numeri: ");
				System.out.println("1) Cancellare l'ultimo contatto inserito!");
				System.out.println("2) Salvare l'ultimo contatto inserito!");
				System.out.println("3) Per uscire dal sistema di inserimento rubrica");
				System.out.print("Inserire la scelta: ");
				c = scan.nextLine();
				if(c.equals("1"))
					continue;
				else {
					if(c.equals("2")) {
						contlist.append(nome).append(cognome).append(telefono).append(email).append("\n");
					 	System.out.println("Salvato!");
					 	System.out.print("Continuare? (s/n): ");
					 	s = scan.nextLine();
					 	if(s.equals("s"))
					 		flag = true;
					 	else 
					 		flag = false;
					}else
						if(c.equals("3"))
							flag = false;
				}
			}
		}while(!flag);
		
	}
		
	
	public static void main(String[] args) {
		ScannerForRubrica pr = new ScannerForRubrica();
		pr.scannMethod(contlist);
		System.out.println(contlist);

}}
