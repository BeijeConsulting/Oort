package it.beije.oort.franceschi.hibernateRubrica;

import it.beije.oort.franceschi.rubrica.Contatto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SingletonSessionFactory {
    private SingletonSessionFactory(){}

    private static Session session;

    public static Session openSession(){
        if (session == null){
            // Inizializzo la configurazione base di Hibernate con file
            Configuration configuration = new Configuration().configure()
                    .addAnnotatedClass(Contatto.class);
            // Creo la SessionFactory
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            // Apro una nuova sessione
            session = sessionFactory.openSession();
        }
        return session;
    }
}
