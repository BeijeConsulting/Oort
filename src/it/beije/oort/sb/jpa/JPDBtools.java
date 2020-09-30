package it.beije.oort.sb.jpa;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import it.beije.oort.rubrica.Contatto;
import it.beije.oort.sb.biblioteca.*;

public class JPDBtools {
	
	static Scanner sc = new Scanner(System.in);

	public static Autori createAutore(){
		Autori autore = new Autori();
		System.out.println("Inserisci il nome dell'Autore");
		autore.setNome(sc.nextLine());
		System.out.println("Inserisci il cognome dell'Autore");
		autore.setCognome(sc.nextLine());
		System.out.println("Inserisci la biografia dell'autore");
		autore.setBiografia(sc.nextLine());	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String okay="";
		while(!okay.equals("okay")) {
			try {
				System.out.println("Inserisci la data di nascita in formato dd/MM/yyyy");
				LocalDate nascita = LocalDate.parse(sc.nextLine(), formatter);
				autore.setData_nascita(nascita);
				okay="okay";
			} catch(DateTimeException e) {
				System.out.println("Data non riconosciuta, se non vuoi mettere nessuna data di nascita premi INVIO");
				if(sc.nextLine().equals("")) okay="okay";
			}
		}
		String okay2="";
		while(!okay2.equals("okay")) {
			System.out.println("Inserisci la data di morte in formato dd/MM/yyyy. Se è ancora vivo premi INVIO");
			String dMorte = sc.nextLine();
			try {
				LocalDate morte = LocalDate.parse(dMorte, formatter);
				okay2="okay";
			} catch(DateTimeException e) {
				if(!dMorte.equals("")) System.out.println("Data non riconosciuta");
				else okay2="okay";
			}
		}
		return autore;
	}

	
	public static Editori createEditore() {
		Editori editore = new Editori();
		System.out.println("Inserisci la descrizione dell'editore");
		editore.setDescrizione(sc.nextLine());
		String temp ="";
		while(!temp.equals("okay")) {
			System.out.println("Inserisci la denominazione dell'editore");
			String den = sc.nextLine();
			if(!den.equals("")){
				editore.setDenominazione(den);
				temp="okay";
			} else System.out.println("La denominazione non può essere vuota\n");
		}
		return editore;
	}
	
	public static Utenti createUtente() {
		Utenti utente =  new Utenti();
		System.out.println("Inserisci il nome dell'utente");
		utente.setNome(sc.nextLine());
		String temp="";
		while(!temp.equals("okay")) {
			System.out.println("Inserisci il cognome dell'utente");
			String cogn = sc.nextLine();
			if(cogn.equals("")) System.out.println("Almeno il cognome non può essere vuoto");
			else {
				utente.setCognome(cogn);
				temp="okay";
			}
		}
		System.out.println("Inserisci l'indirizzo dell'utente");
		utente.setIndirizzo(sc.nextLine());
		System.out.println("Inserisci l'email dell'utente");
		utente.setEmail(sc.nextLine());
		System.out.println("Inserisci il numero di telefono dell'utente");
		utente.setTelefono(sc.nextLine());
		System.out.println("Inserisci il codice fiscale dell'utente");
		String cod = sc.nextLine();
		if(cod.length()>16) System.out.println("Codice fiscale non accettato");
		else utente.setCodice_fiscale(cod);
		return utente;
	}
	
	public static Libri createLibro() {
		Libri libro = new Libri();
		String temp="";
		while(!temp.equals("okay")) {
			System.out.println("Inserisci il titolo del libro");
			String titolo = sc.nextLine();
			if(titolo.equals("")) System.out.println("Il campo Titolo non può essere vuoto");
			else {
				libro.setTitolo(titolo);
				temp="okay";
			}
		}
		System.out.println("Inserisci la descrizione del libro");
		libro.setDescrizione(sc.nextLine());
		System.out.println("Inserisci l'anno di pubblicazione del libro");
		libro.setAnno(sc.nextInt());		
		System.out.println("Inserisci l'Editore scegliendo tra quelli proposti");
		int Edit = sceltaEditore();
		if(Edit!=0) libro.setEditore(Edit);
		System.out.println("Inserisci l'autore scegliendo tra quelli proposti");
		int Aut = sceltaAutore();
		if(Aut!=0) libro.setEditore(Aut);
		return libro;
	}
	
	public static Prestiti createPrestito() {
		Prestiti prestito = new Prestiti();
		System.out.println("Inserisci il libro scegliendo tra quelli presenti nel DB");
		int libro = sceltaLibro();
		if(libro!=0) prestito.setLibro(libro);
		else {
			System.out.println("Il libro chiesto in prestito non è presente nel DataBase. Prima di prestarlo va inserito");
			return null;
		}
		System.out.println("Inserisci l'utente che sta richiedendo il prestito tra quelli presenti nel DB");
		int utente = sceltaUtente();
		if(utente!=0) prestito.setUtente(utente);
		else {
			System.out.println("L'utente richiedente il prestito non è presente nel DataBase. Deve prima registrarsi");
			return null;
		}
		String okay="";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		while(!okay.equals("okay")) {
			try {
				System.out.println("Inserisci la data di inizio prestito in formato dd/MM/yyyy");
				LocalDate inizio = LocalDate.parse(sc.nextLine(), formatter);
				prestito.setData_inizio(inizio);
				okay="okay";
			} catch(DateTimeException e) {
				System.out.println("Data non riconosciuta, inserisci una data corretta");
			}
		}
		String okay1="";
		while(!okay1.equals("okay")) {
			try {
				System.out.println("Inserisci la data di fine prestito in formato dd/MM/yyyy, se non vuoi inserire nessuna data premi INVIO");
				String appoggio = sc.nextLine();
				if(!appoggio.equals("")) { 
					LocalDate fine = LocalDate.parse(appoggio, formatter);
					prestito.setData_fine(fine);
					okay1="okay";
					}
				okay1="okay";
			} catch(DateTimeException e) {
				System.out.println("Data non riconosciuta, inserisci una data corretta");
			}
		}
		return prestito;
	}
	
	public static int sceltaEditore() {
		EntityManager entityManager = JpaEntityManagerFactory.entityManager("OortBiblioteca");
		String jpql = "SELECT e FROM Editori as e";
		Query query = entityManager.createQuery(jpql);
		List<Editori> editori = query.getResultList();
		int count = 1;
		Map<Integer, Editori> mapEdit = new HashMap<Integer, Editori>();
		for(Editori e : editori) {	
			System.out.println(count+") "+ e.getDenominazione() );
			mapEdit.put(count, e);
			count++;
		}
		System.out.println("Inserisci il numero corrispondente all'editore scelto. Se non è presente l'editore voluto digita 0");
		entityManager.close();
		int temp = Integer.parseInt(sc.nextLine());
		if(temp==0) return 0;
		return mapEdit.get(temp).getId();
	}
	
	
	
	public static int sceltaAutore() {
		EntityManager entityManager = JpaEntityManagerFactory.entityManager("OortBiblioteca");
		String jpql = "SELECT e FROM Autori as e";
		Query query = entityManager.createQuery(jpql);
		List<Autori> autori = query.getResultList();
		int count = 1;
		Map<Integer, Autori> mapEdit = new HashMap<Integer, Autori>();
		for(Autori e : autori) {
			if(e.getNome()!=null) System.out.println(count+") "+ e.getNome()+" "+e.getCognome());
			else System.out.println(count+") "+e.getCognome());
			mapEdit.put(count, e);
			count++;
		}
		System.out.println("Inserisci il numero corrispondente all'autore scelto. Se non è presente l'autore voluto digita 0");
		entityManager.close();
		int temp = Integer.parseInt(sc.nextLine());
		if(temp==0) return 0;
		return mapEdit.get(temp).getId();
	}
	
	public static int sceltaLibro() {
		EntityManager entityManager = JpaEntityManagerFactory.entityManager("OortBiblioteca");
		String jpql = "SELECT e FROM Libri as e";
		Query query = entityManager.createQuery(jpql);
		List<Libri> libri = query.getResultList();
		int count = 1;
		Map<Integer, Libri> mapEdit = new HashMap<Integer, Libri>();
		for(Libri e : libri) {	
			System.out.println(count+") "+ e.getTitolo());
			mapEdit.put(count, e);
			count++;
		}
		System.out.println("Inserisci il numero corrispondente al libro scelto. Se non è presente il libro voluto digita 0");
		entityManager.close();
		int temp = Integer.parseInt(sc.nextLine());
		if(temp==0) return 0;
		return mapEdit.get(temp).getId();
	}
	
	public static int sceltaUtente() {
		EntityManager entityManager = JpaEntityManagerFactory.entityManager("OortBiblioteca");
		String jpql = "SELECT e FROM Utenti as e";
		Query query = entityManager.createQuery(jpql);
		List<Utenti> utenti = query.getResultList();
		int count = 1;
		Map<Integer, Utenti> mapEdit = new HashMap<Integer, Utenti>();
		for(Utenti e : utenti) {
			if(e.getNome()!=null) System.out.println(count+") "+ e.getNome()+" "+e.getCognome());
			else System.out.println(count+") "+e.getCognome());
			mapEdit.put(count, e);
			count++;
		}
		System.out.println("Inserisci il numero corrispondente all'utente scelto. Se non è presente l'utente voluto digita 0");
		entityManager.close();
		int temp = Integer.parseInt(sc.nextLine());
		if(temp==0) return 0;
		return mapEdit.get(temp).getId();
	}
//	public static Editori ricercaEd(int indice) {
//		EntityManager entityManager = JpaEntityManagerFactory.entityManager("OortBiblioteca");
//		String jpql = "SELECT e FROM Editori as e where denominazione = '"+denominazione+"'";
//		Query query = entityManager.createQuery(jpql);
//		List<Editori> editori = query.getResultList();
//		if(editori.size()!=1) {
//			System.out.println("Quale di questi");
//		}
//		return editori.get(0);
//	}
	
	
	public static void main(String[] args) {

//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("OortRubrica");
//		EntityManager entityManager = factory.createEntityManager();
	
		//esempio SELECT
//		Contatto contatto = entityManager.find(Contatto.class, 1);
//		
//		System.out.println(contatto);
		
		//esempio query JPQL
//		String jpql = "SELECT c FROM Contatto as c WHERE cognome = 'rossi'";
//		Query query = entityManager.createQuery(jpql);
//		List<Contatto> contatti = query.getResultList();
//		System.out.println(contatti.size());
//		for (Contatto contatto : contatti) {
//			System.out.println("id : " + contatto.getId());
//			System.out.println("nome : " + contatto.getNome());
//			System.out.println("cognome : " + contatto.getCognome());
//			System.out.println("telefono : " + contatto.getTelefono());
//			System.out.println("email : " + contatto.getEmail());
//		}
		
		//esempio INSERT
//		Contatto contatto = new Contatto();
//		contatto.setNome("Gianna");
//		contatto.setCognome("Nanni");
//		contatto.setEmail("gianna@nannino.it");
//		contatto.setTelefono("3455661634");
		EntityManager entityManager = JpaEntityManagerFactory.entityManager("OortBiblioteca");
		//entityManager.getTransaction().begin();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(createPrestito());
//		System.out.println("contatto id : " + contatto.getId());
//		entityManager.persist(contatto);
//		System.out.println("contatto id : " + contatto.getId());
		entityManager.getTransaction().commit();
		//entityManager.getTransaction().rollback();
		
		entityManager.close();
	}

}
