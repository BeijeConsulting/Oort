package it.beije.oort.franceschi.hibernateRubrica;

import it.beije.oort.franceschi.rubrica.Contatto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class HDBTester {
    public static void main(String[] args){
        // Inizializzo la configurazione base di Hibernate con file
        Configuration configuration = new Configuration().configure();
        // Creo la SessionFactory
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        // Apro una nuova sessione
        Session session = sessionFactory.openSession();

        // SELECT (ALL)
        // Scrivo la query in HQL
        String hql= "SELECT c FROM Contatto as c";
        // Creo ed eseguo la Query, riportando il risultato in un oggetto query
        Query<Contatto> query = session.createQuery(hql);
        // "Converto" l'oggetto query in lista e lo utilizzo. è già una lista di Contatto
        System.out.println("Contatti in Database: " + query.list().size());

        // INSERT
        // Avvio la transazione
        Transaction ts = session.beginTransaction();
        System.out.println("Transazione: " + ts.getStatus());
        // Creo un oggetto Contatto
        Contatto c = new Contatto();
        c.setNome("Alessio");
        c.setCognome("Franceschi");
        c.setCell("39250632995");
        c.setEmail("");
        // Salvo l'oggetto (fa tutto Hibernate)
        session.save(c);
        // Faccio il commit della transazione
        ts.commit();
        System.out.println("Stato transazione post commit: " + ts.getStatus());
        System.out.println("La transazione è attiva? " + ts.isActive());

        // UPDATE
        // Creo una nuova transazione. Il commit chiude la transazione.
        Transaction ts2 = session.beginTransaction();
        // Ottengo dal DB l'oggetto che intendo modificare.
        Contatto updateMe = session.get(Contatto.class, 722);
        // Modifico l'oggetto come se fosse un.. oggetto
        updateMe.setNome("Updated");
        // Lo ri-salvo. Hibernate sa che è un oggetto preso dal DB, quindi fa l'UPDATE anziché l'INSERT
        session.save(updateMe);
        ts2.commit();

        // SELECT SPECIFICO
        String hql2 = "SELECT c FROM Contatto as c WHERE nome = 'Updated'";
        Query<Contatto> query2 = session.createQuery(hql2);
        for (Contatto contatto : query2.list()){
            System.out.println(contatto);
        }

        // Chiudo la sessione

        session.close();
    }
}
