package it.beije.oort.madonia.rubrica;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.NoResultException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import it.beije.oort.madonia.rubrica.db.DBManager;
import it.beije.oort.madonia.rubrica.db.HybSessionFactory;

public class ClientRubricaDB {
	
	private final static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Benvenuto alla gestione del DB della rubrica");
		ClientRubricaDB.menuPrincipale();
	}

	public static void menuPrincipale() {
		boolean chiudiProgramma = false;
		while (!chiudiProgramma) {
			System.out.println("MENU PRINCIPALE");
			System.out.println("Scegliere l'operazione che si vuole intraprendere");
			System.out.println("1 - VISUALIZZAZIONE CONTATTI");
			System.out.println("2 - MODIFICA/CANCELLAZIONE CONTATTI");
			System.out.println("3 - INSERIMENTO CONTATTO");
			System.out.println("4 - EXPORT DATI");
			System.out.println("0 - CHIUDI PROGRAMMA");
			String inputUtente = sc.nextLine();
			switch (inputUtente) {
			case "1":
				ClientRubricaDB.operazioneVisualizzazioneContatti();
				break;
			case "2":
				ClientRubricaDB.operazioneModificaCancellazione();
				break;
			case "3":
				ClientRubricaDB.operazioneInserimento();
				break;
			case "4":
				ClientRubricaDB.operazioneExport();
				break;
			case "0":
				System.out.println("Grazie per aver usato il client, uscita programma");
				chiudiProgramma = true;
				break;
			default:
				System.out.println("Comando non riconosciuto");
			}
		}
	}
	
	private static void operazioneVisualizzazioneContatti() {
		boolean tornaIndietro = false;
		while (!tornaIndietro) {
			System.out.println("VISUALIZZAZIONE CONTATTI");
			System.out.println("Scegliere l'operazione da intraprendere");
			System.out.println("1 - RICERCA");
			System.out.println("2 - MOSTRA TUTTI");
			System.out.println("0 - TORNA INDIETRO");
			String inputUtente = sc.nextLine();
			switch (inputUtente) {
			case "1":
				ClientRubricaDB.operazioneVisualizzazioneContattiRicerca();
				break;
			case "2":
				ClientRubricaDB.operazioneVisualizzazioneContattiCompleta();
				break;
			case "0":
				tornaIndietro = true;
				break;
			default:
				System.out.println("Comando non riconosciuto");
			}
		}
	}
	
	private static void operazioneVisualizzazioneContattiRicerca() {
		System.out.println("VISUALIZZAZIONE CONTATTI - RICERCA");
		
		String nome = "";
		String cognome = "";
		String telefono = "";
		String email = "";
		
		boolean tornaIndietro = false;
		while (!tornaIndietro) {
			System.out.println("Impostare i filtri di ricerca");
			System.out.println("1 - IMPOSTA NOME");
			System.out.println("2 - IMPOSTA COGNOME");
			System.out.println("3 - IMPOSTA TELEFONO");
			System.out.println("4 - IMPOSTA EMAIL");
			System.out.println("5 - CERCA");
			System.out.println("9 - RESETTA CAMPI");
			System.out.println("0 - TORNA INDIETRO");
			String inputUtente = sc.nextLine();
			boolean filtroModificato = false;
			boolean cerca = false;
			switch (inputUtente) {
			case "1":
				System.out.println("Nome: ");
				nome = sc.nextLine();
				filtroModificato = true;
				break;
			case "2":
				System.out.println("Cognome: ");
				cognome = sc.nextLine();
				filtroModificato = true;
				break;
			case "3":
				System.out.println("Telefono: ");
				telefono = sc.nextLine();
				filtroModificato = true;
				break;
			case "4":
				System.out.println("Email: ");
				email = sc.nextLine();
				filtroModificato = true;
				break;
			case "5":
				if (nome.equals("") && cognome.equals("") && telefono.equals("") && email.equals("")) {
					System.out.println("Non hai impostato alcun filtro, la ricerca è annullata");
				} else {
					cerca = true;
				}
				break;
			case "9":
				nome = "";
				cognome = "";
				telefono = "";
				email = "";
				System.out.println("Filtri resettati! Imposta altri filtri");
				break;
			case "0":
				tornaIndietro = true;
				break;
			default:
				System.out.println("Comando non riconosciuto");
				break;
			}
			
			if (filtroModificato) {
				StringBuilder sb = new StringBuilder("Filtri: ")
						.append("Nome: \"").append(nome)
						.append("\"; Cognome: \"").append(cognome)
						.append("\"; Telefono: \"").append(telefono)
						.append("\" Email: \"").append(email)
						.append("\"");
				System.out.println(sb.toString());
			} else if (cerca) {
				try {
					List<Contatto> contatti = ottieniListaContattiHDB(nome, cognome, telefono, email);
					if (contatti.size() > 0) {
						stampaContatti(contatti);
					} else {
						System.out.println("Nessun contatto trovato");
					}
				} catch (Exception e) {
					System.err.println("Errore non riconosciuto");
					e.printStackTrace();
				}
			}	
		}
	}
	
	private static void operazioneVisualizzazioneContattiCompleta() {
		System.out.println("VISUALIZZAZIONE CONTATTI - COMPLETA");
		System.out.println();
		
		boolean tornaIndietro = false;
		boolean errore = false;
		List<Contatto> listaContatti = null;
		try {
			listaContatti = ottieniListaContattiHDB();
		} catch (Exception e) {
			System.err.println("Errore non riconosciuto");
			e.printStackTrace();
			errore = true;
		}
		
		int pagina = 0;
		int maxPagina = (listaContatti.size() - 1)/20;
		while (!errore && !tornaIndietro && listaContatti.size() > 0) {
			int start = 20*pagina;
			int end = 20 + 20*pagina;
			end = end > listaContatti.size() ? listaContatti.size() : end; // Controllo se la lista ha raggiunto la fine
			stampaContatti(listaContatti, start, end);
			StringBuilder numeroPagina = 
					new StringBuilder()
					.append("[PAG. ")
					.append(pagina + 1)
					.append("/")
					.append(maxPagina + 1)
					.append("] ");
			System.out.print(numeroPagina);
			if (pagina != 0) {
				System.out.print("1 - PREC; ");
			}
			if (pagina < (listaContatti.size() - 1)/20) {
				System.out.print("2 - SUCC; ");
			}
			System.out.println("0 - TORNA INDIETRO");
			
			boolean comandoCorretto = false;
			while(!comandoCorretto) {
				String inputUtente = sc.nextLine();
				switch (inputUtente) {
				case "1":
					if (pagina != 0) {
						pagina--;
						comandoCorretto = true;
					} else {
						System.out.println("Non puoi andare alla pagina precedente perché non esiste!");
					}
					break;
				case "2":
					if (pagina < (listaContatti.size() - 1)/20) {
						pagina++;
						comandoCorretto = true;
					} else {
						System.out.println("Non puoi andare alla pagina successiva perché non esiste!");
					}
					break;
				case "0":
					comandoCorretto = true;
					tornaIndietro = true;
					break;
				default:
					System.out.println("Input non riconosciuto");
				}
			}
		}
	}
	
	private static void operazioneModificaCancellazione() {
		System.out.println("MODIFICA/CANCELLAZIONE CONTATTI");
		boolean tornaIndietroGenerale = false;
		while(!tornaIndietroGenerale) {
			int id = -1;
			Contatto contatto = null;
			System.out.println("Inserire l'id del contatto da modificare/cancellare (0 - TORNA INDIETRO)");
			
			boolean inputIdCorretto = false;
			while(!inputIdCorretto) {
				String inputId = sc.nextLine();
				try {
					id = Integer.parseInt(inputId);
					if (id < 0) {
						System.out.println("Gli ID non possono essere negativi");
					} else {
						inputIdCorretto = true;
					}
				} catch (NumberFormatException e) {
					System.out.println("La ID inserita non è un numero nel formato corretto, riprova");
				}
			}
			
			try {
				if (id > 0) {
				contatto = ottieniContattoHDB(id);
				} else if (id == 0) {
					tornaIndietroGenerale = true;
				}
			} catch (NoResultException e) {
				System.out.println("Il contatto non è stato trovato nel database");
			} catch (Exception e) {
				System.err.println("Errore non riconosciuto");
				e.printStackTrace();
			}
			
			if (!tornaIndietroGenerale && contatto != null) {
				boolean tornaIndietroModifica = false;
				while(!tornaIndietroModifica) {
					System.out.println("Cosa vuoi fare con questo contatto?");
					System.out.println(contatto);
					System.out.println("1 - MODIFICA");
					System.out.println("2 - CANCELLAZIONE");
					System.out.println("0 - TORNA INDIETRO");
					
					String inputUtente = sc.nextLine();
					
					switch (inputUtente) {
					case "1":
						operazioneModifica(contatto);
						tornaIndietroModifica = true;
						break;
					case "2":
						tornaIndietroModifica = operazioneCancellazione(contatto);
						break;
					case "0":
						tornaIndietroModifica = true;
						break;
					default:
						break;
					}
				}
			}
		}
	}
	
	private static void operazioneModifica(Contatto contatto) {
		Contatto contattoClonato = contatto.clone();
		System.out.println("MODIFICA CONTATTO");
		
		boolean tornaIndietro = false;
		
		while(!tornaIndietro) {
			System.out.println("Scegli cosa modificare");
			System.out.println("1 - MODIFICA NOME");
			System.out.println("2 - MODIFICA COGNOME");
			System.out.println("3 - MODIFICA TELEFONO");
			System.out.println("4 - MODIFICA EMAIL");
			System.out.println("5 - CONFERMA LA MODIFICA EFFETTUATA");
			System.out.println("9 - ANNULLA MODIFICHE");
			System.out.println("0 - TORNA INDIETRO");

			String valore = null;

			String inputUtente = sc.nextLine();
			if (inputUtente.equals("1")) {
				System.out.println("Nome: ");
				valore = sc.nextLine();
				contattoClonato.setNome(valore);
			} else if (inputUtente.equals("2")) {
				System.out.println("Cognome: ");
				valore = sc.nextLine();
				contattoClonato.setCognome(valore);
			} else if (inputUtente.equals("3")) {
				System.out.println("Telefono: ");
				valore = sc.nextLine();
				contattoClonato.setTelefono(valore);
			} else if (inputUtente.equals("4")) {
				System.out.println("Email: ");
				valore = sc.nextLine();
				contattoClonato.setEmail(valore);
			} else if (inputUtente.equals("5")) {
				System.out.println("Il contatto che stai per modificare è:");
				System.out.println(contatto);
				System.out.println("Verrà trasformato in questo contatto:");
				System.out.println(contattoClonato);
				do {
					System.out.println("Confermare la modifica? 1 - Sì; 2 - No");
					valore = sc.nextLine();
					if (valore.equals("1")) {
						try {
						System.out.println("Contatto in modifica nel database...");
						modificaContatto(contattoClonato);
						contatto = contattoClonato.clone();
						System.out.println("Modifica effettuata");
						tornaIndietro = true;
						} catch (IllegalArgumentException e) {
							System.out.println("Modifica non eseguita");
							System.out.println(e.getMessage());
						}
					} else if (valore.equals("2")) {
						System.out.println("Modifica nel database annullata");
					} else {
						System.out.println("Comando non riconosciuto");
					}
				} while (valore != null && !valore.equals("1") && !valore.equals("2"));
			} else if (inputUtente.equals("9")) {
				contattoClonato = contatto.clone();
				System.out.println("Contatto precedente ripristinato!");
			} else if (inputUtente.equals("0")) {
				tornaIndietro = true;
			} else {
				System.out.println("Comando non riconosciuto");
			}
			
			if (!tornaIndietro) {
				System.out.println("Le tue modifiche sono queste:");
				System.out.print("Vecchio: ");
				System.out.println(contatto);
				System.out.print("Nuovo: ");
				System.out.println(contattoClonato);
				System.out.println("Puoi continuare con i seguenti comandi");
			}
		}
	}

	private static boolean operazioneCancellazione(Contatto contatto) {
		boolean tornaIndietro = false;
		System.out.println("CANCELLAZIONE CONTATTO");
		System.out.println("Sei sicuro di voler cancellare questo contatto?");
		System.out.println(contatto);
		System.out.println("1 - Sì; 2 - No");
		
		boolean comandoValido = false;
		
		while(!comandoValido) {
			String inputUtente = sc.nextLine();
			if (inputUtente.equals("1")) {
				System.out.println("Cancellazione in corso...");
				cancellaContattoHDB(contatto);
				System.out.println("Cancellazione avvenuta!");
				comandoValido = true;
				tornaIndietro = true;
			} else if (inputUtente.equals("2")) {
				System.out.println("Cancellazione contatto annullata");
				comandoValido = true;
			} else {
				System.out.println("Comando non riconosciuto");
			}
		}
		
		return tornaIndietro;
	}
	
	private static void operazioneInserimento() {
		System.out.println("INSERIMENTO CONTATTO");
		System.out.println("Inserisci i dati del contatto (almeno un campo deve essere riempito)");
		Contatto contatto = new Contatto("","","","");

		boolean tornaIndietro = false;
		while(!tornaIndietro) {
			System.out.println("1 - INSERISCI NOME");
			System.out.println("2 - INSERISCI COGNOME");
			System.out.println("3 - INSERISCI TELEFONO");
			System.out.println("4 - INSERISCI EMAIL");
			System.out.println("5 - INSERISCI CONTATTO SUL DATABASE");
			System.out.println("9 - RESETTA CONTATTO");
			System.out.println("0 - TORNA INDIETRO");
			
			String valore = null;
			String inputUtente = sc.nextLine();
			if (inputUtente.equals("1")) {
				System.out.println("Nome: ");
				valore = sc.nextLine();
				contatto.setNome(valore);
			} else if (inputUtente.equals("2")) {
				System.out.println("Cognome: ");
				valore = sc.nextLine();
				contatto.setCognome(valore);
			} else if (inputUtente.equals("3")) {
				System.out.println("Telefono: ");
				valore = sc.nextLine();
				contatto.setTelefono(valore);
			} else if (inputUtente.equals("4")) {
				System.out.println("Email: ");
				valore = sc.nextLine();
				contatto.setEmail(valore);
			} else if (inputUtente.equals("5")) {
				if (contatto.isEmpty()) {
					System.out.println("Il contatto è vuoto, non puoi inserirlo");
				} else {
					System.out.println("Il contatto che vuoi inserire è:");
					System.out.println(contatto);
					do {
						System.out.println("Confermare l'inserimento? 1 - Sì; 2 - No");
						valore = sc.nextLine();
						if (valore.equals("1")) {
							try {
								System.out.println("Contatto in inserimento nel database...");
								inserisciContatto(contatto);
								System.out.println("Inserimento effettuato");
								tornaIndietro = true;
							} catch (IllegalArgumentException e) {
								System.out.println("Inserimento non eseguito");
								System.out.println(e.getMessage());
							}
						} else if (valore.equals("2")) {
							System.out.println("Inserimento nel database non eseguito");
						} else {
							System.out.println("Comando non riconosciuto");
						}
					} while (valore != null && !valore.equals("1") && !valore.equals("2"));
				}
			} else if (inputUtente.equals("9")) {
				contatto = new Contatto("","","","");
				System.out.println("Contatto resettato!");
			} else if (inputUtente.equals("0")) {
				tornaIndietro = true;
			} else {
				System.out.println("Comando non riconosciuto");
			}
			
			if (!tornaIndietro) {
				System.out.println("Il contatto in creazione è:");
				System.out.println(contatto);
				System.out.println("Puoi continuare con i seguenti comandi");
			}
		}
	}
	
	private static void operazioneExport() {
		System.out.println("EXPORT");
		System.out.println("Esportazione dati database su file");
		boolean tornaIndietro = false;
		String estensione = null;
		
		while(!tornaIndietro) {
			StringBuilder s = new StringBuilder();
			boolean comandoValido = false;
			while(!comandoValido && !tornaIndietro) {
				System.out.println("Scegliere il formato verso il quale esportare i dati del database");
				System.out.println("1 - CSV");
				System.out.println("2 - XML");
				System.out.println("0 - TORNA INDIETRO");
				String inputUtente = sc.nextLine();

				if (inputUtente.equals("1")) {
					estensione = "csv";
					comandoValido = true;
				} else if (inputUtente.equals("2")) {
					estensione = "xml";
					comandoValido = true;
				} else if (inputUtente.equals("0")) {
					tornaIndietro = true;
				} else {
					System.out.println("Comando non riconosciuto");
				}
			}
			
			if (!tornaIndietro) {
				System.out.println("Inserisci il nome del file (senza indicare l'estensione)");
				String inputUtente = sc.nextLine();
				s.append("/temp/rubrica/")
					.append(inputUtente)
					.append(".")
					.append(estensione);
				comandoValido = false;
				while (!comandoValido) {
					System.out.print("Confermare il salvataggio in \"");
					System.out.print(s);
					System.out.println("\"?");
					System.out.println("1 - Sì; 2 - No");
					
					inputUtente = sc.nextLine();
					
					if (inputUtente.equals("1")) {
						exportDati(s.toString(), estensione);
						System.out.println("Esportazione completata");
						comandoValido = true;
					} else if (inputUtente.equals("2")) {
						System.out.println("Esportazione annullata");
						comandoValido = true;
					} else {
						System.out.println("Comando non riconosciuto");
					}
				}
			}
		}
	}
	
	private static void exportDati(String pathfile, String estensione) {
		String[] intestazione = {"NOME", "COGNOME", "TELEFONO", "EMAIL"};
		List<Contatto> contatti = ClientRubricaDB.ottieniListaContattiHDB();
		switch (estensione) {
		case "csv":
			try {
				WriterCsvRubrica.writeCsvFile(intestazione, contatti, pathfile, false);
			} catch (IOException e) {
				System.out.println("Errore nel salvataggio del file");
				e.printStackTrace();
			}
			break;
		case "xml":
			try {
				WriterXmlRubrica.writeXmlFile(contatti, pathfile, false);
			} catch (ParserConfigurationException e) {
				System.out.println("Errore nel salvataggio del file");
				e.printStackTrace();
			} catch (TransformerException e) {
				System.out.println("Errore nel salvataggio del file");
				e.printStackTrace();
			}
		}
	}
	
 	private static Contatto ottieniContattoHDB(int id) {
		Session session = null;
		Query<Contatto> query = null;
		Contatto contatto = null;
		try {
			session = HybSessionFactory.getSession();
			String hql = "SELECT c FROM Contatto as c WHERE c.id = :id";
			query = session.createQuery(hql);
			query.setParameter("id", id);
			contatto = query.getSingleResult();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return contatto;
	}
	
	private static List<Contatto> ottieniListaContattiHDB() {
		return ottieniListaContattiHDB("","","","");
	}
	
	private static List<Contatto> ottieniListaContattiHDB(String nome, String cognome, String telefono, String email) {
		boolean addAnd = false;
		boolean addNome = nome != null && nome.length() > 0;
		boolean addCognome = cognome != null && cognome.length() > 0;
		boolean addTelefono = telefono != null && telefono.length() > 0;
		boolean addEmail = email != null && email.length() > 0;
		StringBuilder hql = new StringBuilder("SELECT c FROM Contatto as c ");
		
		if (addNome || addCognome || addTelefono || addEmail) {
			hql.append("WHERE ");
		}
		
		if (addNome) {
			hql.append("c.nome like :nome ");
			addAnd = true;
		}
		if (addCognome) {
			if (addAnd) { hql.append("AND "); }
			hql.append("c.cognome like :cognome ");
			addAnd = true;
		}
		if (addTelefono) {
			if (addAnd) { hql.append("AND "); }
			hql.append("c.telefono like :telefono ");
			addAnd = true;
		}
		if (addEmail) {
			if (addAnd) { hql.append("AND "); }
			hql.append("c.email like :email ");
		}
		
		hql.append("ORDER BY c.id");
		
		Session session = null;
		Query<Contatto> query = null;
		List<Contatto> contatti = null;
		
		try {
			session = HybSessionFactory.getSession();
			query = session.createQuery(hql.toString());
			if(addNome) { query.setParameter("nome", nome); }
			if(addCognome) { query.setParameter("cognome", cognome); }
			if(addTelefono) { query.setParameter("telefono", telefono); }
			if(addEmail) { query.setParameter("email", email); }
			contatti = query.list();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return contatti;
	}
	
	private static void modificaContatto(Contatto contatto) {
		if (contatto == null) throw new NullPointerException();
		if (contatto.isEmpty()) throw new IllegalArgumentException("Il contatto deve avere almeno un campo riempito");
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HybSessionFactory.getSession();
			transaction = session.beginTransaction();
			Contatto nuovoContatto = session.get(Contatto.class, contatto.getId());
			nuovoContatto.setNome(contatto.getNome());
			nuovoContatto.setCognome(contatto.getCognome());
			nuovoContatto.setTelefono(contatto.getTelefono());
			nuovoContatto.setEmail(contatto.getEmail());
			session.save(nuovoContatto);
			transaction.commit();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	private static void cancellaContattoHDB(Contatto contatto) {
		if (contatto == null) throw new NullPointerException();
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HybSessionFactory.getSession();
			transaction = session.beginTransaction();
			Contatto nuovoContatto = session.get(Contatto.class, contatto.getId());
			session.delete(nuovoContatto);
			transaction.commit();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	private static void inserisciContatto(Contatto contatto) {
		if (contatto == null) throw new NullPointerException();
		if (contatto.isEmpty()) throw new IllegalArgumentException("Il contatto non ha almeno un campo pieno");
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HybSessionFactory.getSession();
			transaction = session.beginTransaction();
			session.save(contatto);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}finally {
			if (session != null) {
				session.close();
			}
		}
	}

	private static void stampaContatti(List<Contatto> listaContatti) {
		if (listaContatti.size() == 0) return;
		stampaContatti(listaContatti, 0, listaContatti.size());
	}
	
	private static void stampaContatti(List<Contatto> listaContatti, int start, int end) {
		if (start < 0 || end > listaContatti.size()) throw new IllegalArgumentException();
		if (listaContatti.size() == 0) return;
		
		for (int i = start; i < end; i++) {
			Contatto contatto = listaContatti.get(i);
			StringBuilder sb = new StringBuilder()
					.append(contatto.getId())
					.append(" - ")
					.append(contatto);
			System.out.println(sb.toString());
		}
	}

	/* VECCHIO CONNETTORE */
	
	private static Contatto ottieniContattoJDBC(int id) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Contatto contatto = null;
		
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);

			ps = connection.prepareStatement("SELECT * FROM rubrica WHERE id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				contatto = new Contatto();
				contatto.setNome(rs.getString("nome"));
				contatto.setCognome(rs.getString("cognome"));
				contatto.setTelefono(rs.getString("telefono"));
				contatto.setEmail(rs.getString("email"));
			}
		} finally {
			rs.close();
			ps.close();
			connection.close();
		}
		
		return contatto;
	}
	
	private static List<Contatto> ottieniListaContattiJDBC() throws ClassNotFoundException, SQLException {
		return ottieniListaContattiJDBC("","","","");
	}
	
	private static List<Contatto> ottieniListaContattiJDBC(String nome, String cognome, String telefono, String email) throws ClassNotFoundException, SQLException {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Contatto> listaContatti = new ArrayList<Contatto>();
		try {
			connection = DBManager.getMySqlConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASSWORD);

			boolean addAnd = false;
			boolean addNome = nome != null && nome.length() > 0;
			boolean addCognome = cognome != null && cognome.length() > 0;
			boolean addTelefono = telefono != null && telefono.length() > 0;
			boolean addEmail = email != null && email.length() > 0;
			StringBuilder sql = new StringBuilder("SELECT * FROM rubrica ");
			
			if (addNome || addCognome || addTelefono || addEmail) {
				sql.append("WHERE ");
			}
			
			if (addNome) {
				sql.append("nome like ? ");
				addAnd = true;
			}
			if (addCognome) {
				if (addAnd) { sql.append("AND "); }
				sql.append("cognome like ? ");
				addAnd = true;
			}
			if (addTelefono) {
				if (addAnd) { sql.append("AND "); }
				sql.append("telefono like ? ");
				addAnd = true;
			}
			if (addEmail) {
				if (addAnd) { sql.append("AND "); }
				sql.append("email like ? ");
			}
			
			sql.append("ORDER BY id");

			int puntoDomanda = 0;
			ps = connection.prepareStatement(sql.toString().trim());
			if(addNome) { ps.setString(++puntoDomanda, nome); }
			if(addCognome) { ps.setString(++puntoDomanda, cognome); }
			if(addTelefono) { ps.setString(++puntoDomanda, telefono); }
			if(addEmail) { ps.setString(++puntoDomanda, email); }
			rs = ps.executeQuery();

			while(rs.next()) {
				Contatto contatto = new Contatto();
				contatto.setId(rs.getInt("id"));
				contatto.setNome(rs.getString("nome"));
				contatto.setCognome(rs.getString("cognome"));
				contatto.setTelefono(rs.getString("telefono"));
				contatto.setEmail(rs.getString("email"));

				listaContatti.add(contatto);
			}
		} finally {
			rs.close();
			ps.close();
			connection.close();
		}
		
		return listaContatti;
	}

}
