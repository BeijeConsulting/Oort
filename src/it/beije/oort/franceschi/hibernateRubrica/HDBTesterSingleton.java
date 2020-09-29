package it.beije.oort.franceschi.hibernateRubrica;

import it.beije.oort.franceschi.rubrica.Contatto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HDBTesterSingleton {
    static Session session;
    static Transaction ts;
    public static void main(String[] args) {
        // INSERT
        session = SingletonSessionFactory.openSession();
        ts = session.beginTransaction();
        Contatto c = new Contatto(true);
        System.out.println("Nuovo contatto: " + c);
        session.save(c);
        int latestID = c.getId();
        System.out.println("ID dell'ultimo Contatto inserito: " + latestID);
        ts.commit();
        session.close();

        // UPDATE
        session = SingletonSessionFactory.openSession();
        ts = session.beginTransaction();
        Contatto updateThis = session.get(Contatto.class, latestID);
        updateThis.setNome("Aggiornato");
        session.save(updateThis);
        ts.commit();
        session.close();

        printAllContatti();
    }
    // provo a scaricare e ricaricare tutto il database. Nota: Non cancello nulla, i contatti si duplicano
    // Meglio non usarlo ora, con 24k contatti ci mette oltre 5 minuti.
    private static void duplicaDatabase(){
        long start = System.nanoTime();

        session = SingletonSessionFactory.openSession();
        String selectAll = "SELECT c FROM Contatto as c";
        Query<Contatto> contattoQuery = session.createQuery(selectAll);
        List<Contatto> contatti = contattoQuery.list();
        System.out.println("Contatti pre-duplicazione: " + contatti.size());
        Contatto newContatto;
        for (Contatto contatto : contatti){
            ts = session.beginTransaction();
            newContatto = new Contatto();
            newContatto.setNome(contatto.getNome());
            newContatto.setCognome(contatto.getCognome());
            newContatto.setCell(contatto.getCell());
            newContatto.setEmail(contatto.getEmail());
            session.save(newContatto);
            ts.commit();
        }
        contattoQuery = session.createQuery(selectAll);
        long end = System.nanoTime();

        System.out.println("Post-duplicazione: " + contattoQuery.list().size());
        session.close();
        System.out.println("Tempo di esecuzione per duplicare " + contatti.size() + " contatti: " + (end-start)/1_000_000_000 + "secondi.");
    }

    private static void printAllContatti(){
        session = SingletonSessionFactory.openSession();
        String getAll = "SELECT c FROM Contatto AS c";
        Query<Contatto> allContatti = session.createQuery(getAll);
        for (Contatto con : allContatti.list()){
            System.out.println(con.getId() + " - " + con);
        }
    }
}
