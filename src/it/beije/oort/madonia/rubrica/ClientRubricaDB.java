package it.beije.oort.madonia.rubrica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import it.beije.oort.madonia.rubrica.db.DBManager;

public class ClientRubricaDB {
	
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Benvenuto alla gestione del DB della rubrica, scegliere l'operazione che si vuole intraprendere");
		System.out.println("1 - VISUALIZZAZIONE CONTATTI");
		System.out.println("2 - MODIFICA/ELIMINAZIONE CONTATTI");
		System.out.println("3 - INSERIMENTO CONTATTO");
		System.out.println("4 - EXPORT DATI");
		
		operazioneModificaCancellazione();
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
					stampaContatti(ottieniRisultatoRicerca(nome, cognome, telefono, email));
				} catch (ClassNotFoundException e) {
					System.err.println("Driver collegamento al DB non trovato");
					e.printStackTrace();
				} catch (SQLException e) {
					System.err.println("Errore nella definizione SQL");
					e.printStackTrace();
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
		Map<Integer,Contatto> mappaContatti = null;
		try {
			mappaContatti = ottieniContattiCompleti();
			System.out.println(mappaContatti.size());
		} catch (ClassNotFoundException e) {
			System.err.println("Driver collegamento al DB non trovato");
			e.printStackTrace();
			errore = true;
		} catch (SQLException e) {
			System.err.println("Errore nella definizione SQL");
			e.printStackTrace();
			errore = true;
		} catch (Exception e) {
			System.err.println("Errore non riconosciuto");
			e.printStackTrace();
			errore = true;
		}
		while (!errore && !tornaIndietro && mappaContatti.size() > 0) {
			int start = 20*pagina;
			int end = 20 + 20*pagina;
			end = end > mappaContatti.size() ? mappaContatti.size() : end;
			stampaContatti(mappaContatti, start, end);
			boolean comandoCorretto = false;
			if (pagina != 0) {
				System.out.print("1 - PREC; ");
			}
			if (pagina < (mappaContatti.size() - 1)/20) {
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
					if (pagina < (mappaContatti.size() - 1)/20) {
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
		boolean contattoIsNull = false;
		while(!tornaIndietroGenerale) {
			int id = -1;
			Contatto contatto = null;
			System.out.println("Inserire l'id del contatto da modificare/cancellare");
			
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
				contatto = ottieniContatto(id);
				} else if (id == 0) {
					tornaIndietroGenerale = true;
				}
			} catch (ClassNotFoundException e) {
				System.err.println("Driver collegamento al DB non trovato");
				e.printStackTrace();
			} catch (SQLException e) {
				System.err.println("Errore nella definizione SQL");
				e.printStackTrace();
			} catch (Exception e) {
				System.err.println("Errore non riconosciuto");
				e.printStackTrace();
			}
			
			if (!tornaIndietroGenerale) {

				if (contatto == null) {
					contattoIsNull = true;
					System.out.println("Il contatto non è stato trovato nel database");
				} else {
					System.out.println("Il contatto selezionato è");
					System.out.println(contatto);
				}
			}
			
			if (!tornaIndietroGenerale && !contattoIsNull) {
				boolean tornaIndietroModifica = false;
				while(!tornaIndietroModifica) {
					System.out.println("Cosa vuoi fare con questo contatto?");
					System.out.println("1 - MODIFICA");
					System.out.println("2 - CANCELLAZIONE");
					System.out.println("0 - TORNA INDIETRO");
					
					String inputUtente = sc.nextLine();
					
					switch (inputUtente) {
					case "1":
						System.out.println("MODIFICA");
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
		System.out.println("1 - MODIFICA NOME");
		System.out.println("2 - MODIFICA COGNOME");
		System.out.println("3 - MODIFICA TELEFONO");
		System.out.println("4 - MODIFICA EMAIL");
		System.out.println("9 - ANNULLA MODIFICHE");
		System.out.println("0 - TORNA INDIETRO");
		
		String inputUtente = sc.nextLine();
		
		switch (inputUtente) {
		case "1":
			System.out.println("MODIFICA");
			break;
		case "2":
			System.out.println("CANCELLAZIONE");
			break;
		case "0":
			break;
		default:
			break;
		}
	}
	
	private static Map<Integer, Contatto> ottieniContattiCompleti() throws ClassNotFoundException, SQLException {
		return ottieniRisultatoRicerca("","","","");
	}
	
	private static Contatto ottieniContatto(int id) throws ClassNotFoundException, SQLException {
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
	
	private static Map<Integer, Contatto> ottieniRisultatoRicerca(String nome, String cognome, String telefono, String email) throws ClassNotFoundException, SQLException {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<Integer, Contatto> mappaContatti = new HashMap<Integer, Contatto>();
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
				contatto.setNome(rs.getString("nome"));
				contatto.setCognome(rs.getString("cognome"));
				contatto.setTelefono(rs.getString("telefono"));
				contatto.setEmail(rs.getString("email"));

				Integer id = rs.getInt("id");
				mappaContatti.put(id, contatto);
			}
		} finally {
			rs.close();
			ps.close();
			connection.close();
		}
		
		return mappaContatti;
	}
	
	private static void stampaContatti(Map<Integer, Contatto> mappaContatti) {
		if (mappaContatti.size() == 0) return;
		stampaContatti(mappaContatti, 0, mappaContatti.size());
	}
	
	private static void stampaContatti(Map<Integer, Contatto> mappaContatti, int start, int end) {
		if (start < 0 || end > mappaContatti.size()) throw new IllegalArgumentException();
		if (mappaContatti.size() == 0) return;
		
		List<Integer> keys = new ArrayList<Integer>(mappaContatti.keySet());
		keys.sort(null);
		for(int i = start; i < end; i++) {
			Integer key = keys.get(i);
			StringBuilder sb = new StringBuilder()
					.append(key)
					.append(" - ")
					.append(mappaContatti.get(key));
			System.out.println(sb.toString());
		}
	}

}
