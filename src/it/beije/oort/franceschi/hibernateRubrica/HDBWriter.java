package it.beije.oort.franceschi.hibernateRubrica;

import it.beije.oort.franceschi.rubrica.Contatto;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Database Writer using the Hibernate Framework
 */
public class HDBWriter {
    /**
     * Adds a Contatto object to the database
     * @param c the object to insert to the database
     */
    public static void addContatto(Contatto c){
        Session session = SingletonSessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        session.save(c);
        ts.commit();
        session.close();
    }

    /**
     * Adds an Array of Contatto to the database.
     * @param contatti an Array of Contatto to add to the Database
     */
    public static void addContatti(Contatto... contatti){
        Session session = SingletonSessionFactory.openSession();
        Transaction ts;
        for (Contatto c : contatti){
            ts = session.beginTransaction();
            session.save(c);
            ts.commit();
        }
        session.close();
    }

    /**
     * Adds a List of Contatto to the database.
     * @param contatti an Array of Contatto to add to the Database
     */
    public static void addContatti(List<Contatto> contatti){
        Session session = SingletonSessionFactory.openSession();
        Transaction ts;
        for (Contatto c : contatti){
            ts = session.beginTransaction();
            session.save(c);
            ts.commit();
        }
        session.close();
    }
}
