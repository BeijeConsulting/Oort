package it.beije.oort.madonia.rubrica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import it.beije.oort.madonia.rubrica.db.DBManager;
import it.beije.oort.madonia.rubrica.db.HDBtools;

public class ClientRubricaDB {
	
	private static Scanner sc = new Scanner(System.in);
	private static HDBtools connettoreDB = new HDBtools();

	public static void main(String[] args) {
		System.out.println("Benvenuto alla gestione del DB della rubrica, scegliere l'operazione che si vuole intraprendere");
		System.out.println("1 - VISUALIZZAZIONE CONTATTI");
		System.out.println("2 - MODIFICA/CANCELLAZIONE CONTATTI");
		System.out.println("3 - INSERIMENTO CONTATTO");
		System.out.println("4 - EXPORT DATI");
		
		operazioneModificaCancellazione();
		
		connettoreDB.close();
	}
	
	private static void operazioneVisualizzazioneContatti() {
		System.out.println("VISUALIZZAZIONE CONTATTI");
		System.out.println("Scegliere l'operazione da intraprendere");
		System.out.println("1 - RICERCA");
		System.out.println("2 - MOSTRA TUTTI");
		System.out.println("0 - TORNA INDIETRO");
	}
	
	private static void operazioneVisualizzazioneContattiRicerca() {
		String nome = "";
		String cognome = "";
		String telefono = "";
		String email = "";
		
		System.out.println("VISUALIZZAZIONE CONTATTI - RICERCA");
		System.out.println("Impostare i filtri di ricerca");
		System.out.println("1 - IMPOSTA NOME");
		System.out.println("2 - IMPOSTA COGNOME");
		System.out.println("3 - IMPOSTA TELEFONO");
		System.out.println("4 - IMPOSTA EMAIL");
		System.out.println("5 - CERCA");
		System.out.println("9 - RESETTA CAMPI");
		System.out.println("0 - TORNA INDIETRO");
		
		boolean tornaIndietro = false;
		do {
			String inputUtente = sc.nextLine();
			boolean filtroModificato = false;
			boolean cerca = false;
			switch (inputUtente) {
			case "1":
				System.out.println("Nome: ");
				nome = sc.nextLine();
				filtroModificato = true;
				System.out.println("Impostato! Impostare altro?");
				break;
			case "2":
				System.out.println("Cognome: ");
				cognome = sc.nextLine();
				filtroModificato = true;
				System.out.println("Impostato! Impostare altro?");
				break;
			case "3":
				System.out.println("Telefono: ");
				telefono = sc.nextLine();
				filtroModificato = true;
				System.out.println("Impostato! Impostare altro?");
				break;
			case "4":
				System.out.println("Email: ");
				email = sc.nextLine();
				filtroModificato = true;
				System.out.println("Impostato! Impostare altro?");
				break;
			case "5":
				if (nome.equals("") && cognome.equals("") && telefono.equals("") && email.equals("")) {
					System.out.println("Non hai impostato alcun filtro, la ricerca è annullata");
					System.out.println("Imposta per favore i filtri");
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
					stampaContatti(ottieniListaContattiHDB(nome, cognome, telefono, email));
				} catch (Exception e) {
					System.err.println("Errore non riconosciuto");
					e.printStackTrace();
				}
			}
			
		} while (!tornaIndietro);
	}
	
	private static void operazioneVisualizzazioneContattiCompleta() {
		System.out.println("VISUALIZZAZIONE CONTATTI - COMPLETA");
		System.out.println();
		
		int pagina = 0;
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
		
		while (!errore && !tornaIndietro && listaContatti.size() > 0) {
			int start = 20*pagina;
			int end = 20 + 20*pagina;
			end = end > listaContatti.size() ? listaContatti.size() : end;
			stampaContatti(listaContatti, start, end);
			boolean comandoCorretto = false;
			if (pagina != 0) {
				System.out.print("1 - PREC; ");
			}
			if (pagina < (listaContatti.size() - 1)/20) {
				System.out.print("2 - SUCC; ");
			}
			System.out.println("0 - TORNA INDIETRO");
			
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
		System.out.println();
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
					inputIdCorretto = true;
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
			} catch (Exception e) {
				System.err.println("Errore non riconosciuto");
				e.printStackTrace();
			}
			
			if (!tornaIndietroGenerale) {

				if (contatto == null) {
					System.out.println("Il contatto non è stato trovato nel database");
				} else {
					System.out.println("Il contatto selezionato è");
					System.out.println(contatto);
				}
			}
			
			if (!tornaIndietroGenerale && contatto != null) {
				boolean tornaIndietroModifica = false;
				while(!tornaIndietroModifica) {
					System.out.println("Cosa vuoi fare con questo contatto?");
					System.out.println("1 - MODIFICA");
					System.out.println("2 - CANCELLAZIONE");
					System.out.println("0 - TORNA INDIETRO");
					
					String inputUtente = sc.nextLine();
					
					switch (inputUtente) {
					case "1":
						operazioneModifica(contatto);
						break;
					case "2":
						System.out.println("CANCELLAZIONE");
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
		System.out.println("Scegli cosa modificare");
		
		boolean tornaIndietro = false;
		
		while(!tornaIndietro) {
			
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

	private static Contatto ottieniContattoHDB(int id) {
		String hql = "SELECT c FROM Contatto as c WHERE c.id = :id";
		Query<Contatto> query = connettoreDB.getSession().createQuery(hql);
		query.setParameter("id", id);
		
		return query.getSingleResult();
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
		
		Query<Contatto> query = connettoreDB.getSession().createQuery(hql.toString());
		if(addNome) { query.setParameter("nome", nome); }
		if(addCognome) { query.setParameter("cognome", cognome); }
		if(addTelefono) { query.setParameter("telefono", telefono); }
		if(addEmail) { query.setParameter("email", email); }
		
		return query.list();
	}
	
	private static void modificaContatto(Contatto contatto) {
		if (contatto == null) throw new NullPointerException();
		if (contatto.getNome().length() == 0
				&& contatto.getCognome().length() == 0
				&& contatto.getTelefono().length() == 0
				&& contatto.getEmail().length() == 0) throw new IllegalArgumentException("Il contatto deve avere almeno un campo riempito");
		
		Session session = connettoreDB.getSession();
		Transaction transaction = session.beginTransaction();
		Contatto nuovoContatto = session.get(Contatto.class, contatto.getId());
		nuovoContatto.setNome(contatto.getNome());
		nuovoContatto.setCognome(contatto.getCognome());
		nuovoContatto.setTelefono(contatto.getTelefono());
		nuovoContatto.setEmail(contatto.getEmail());
		session.save(nuovoContatto);
		transaction.commit();
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
