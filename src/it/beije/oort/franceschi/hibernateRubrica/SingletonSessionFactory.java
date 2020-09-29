package it.beije.oort.franceschi.hibernateRubrica;

import it.beije.oort.franceschi.rubrica.Contatto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SingletonSessionFactory {
    private SingletonSessionFactory(){}

    private static SessionFactory sessionFactory;

    public static Session openSession(){
        if (sessionFactory == null){
            // Inizializzo la configurazione base di Hibernate con file
            Configuration configuration = new Configuration().configure()
                    .addAnnotatedClass(Contatto.class);
            // Creo la SessionFactory
            sessionFactory = configuration.buildSessionFactory();
        }
        // Creo e ritorno una nuova sessione
        return sessionFactory.openSession();
    }
}
