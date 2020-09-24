package it.beije.oort.rubrica.scanner;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import it.beije.oort.rubrica.Contatto;

public class ScannerForRubrica {
		
	
	public static void main(String[] args) {
		List<String> lista = new ArrayList<>();
		Scanner scan = new Scanner(System.in);
		boolean flag = true;
	do {
		System.out.println("Generatore contatti per la rubrica!" + "\n");
		System.out.println("Inserire i seguenti campi: ");
		System.out.print("- Nome: ");
		String nome = scan.nextLine(); 
		System.out.print("- Cognome: ");
		String cognome = scan.nextLine(); 
		System.out.print("- Telefono: "); 
		String telefono = scan.nextLine(); 
		System.out.print("- Email: ");
		String email = scan.nextLine();	
		((PrintStream) lista).append(nome).append(";").append(cognome).append(";").append(telefono).append(";").append(email).append("\n");
		if(nome == "" && cognome == "" && telefono == "" && email == "") {
			
			
			
		}
	}while(!flag);

}}
