package it.beije.oort.franceschi.hibernateRubrica;

import it.beije.oort.franceschi.rubrica.Contatto;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HDBWriter {
    public static void addContatto(Contatto c){
        Session session = SingletonSessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        session.save(c);
        ts.commit();
        session.close();
    }

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
}
