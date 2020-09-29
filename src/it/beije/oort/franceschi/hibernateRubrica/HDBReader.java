package it.beije.oort.franceschi.hibernateRubrica;

import it.beije.oort.franceschi.rubrica.Contatto;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class HDBReader {
    public static List<Contatto> readAll(){
        Session session = SingletonSessionFactory.openSession();
        String hqlSelectAll = "SELECT c FROM Contatto AS c";
        Query<Contatto> query = session.createQuery(hqlSelectAll);
        session.close();
        return query.list();
    }

    public static Contatto getContattoById(int id){
        Session session = SingletonSessionFactory.openSession();
        String hqlSelect = "SELECT c FROM Contatto AS c WHERE id = :id";
        /* Ritorno direttamente il contatto
        setParameter imposta i parametri "custom"
        getSingleResult ritorna direttamente ciò che gli ho chiesto, quindi un contatto
        Chiedendo di avere un contatto dall'ID, ed essendo l'id univoco, questo funziona bene
         */
        return session.createQuery(hqlSelect, Contatto.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public static List<Contatto> getContattiByIds(int... id){
        Session session = SingletonSessionFactory.openSession();
        String hqlSelect = "SELECT c FROM Contatto AS c WHERE id = :id";
        List<Contatto> contatti = new ArrayList<>();
        for (int i : id){
            Contatto c = session.createQuery(hqlSelect, Contatto.class)
                    .setParameter("id", i)
                    .getSingleResult();
            contatti.add(c);
        }
        return contatti;
    }

    public static List<Contatto> getContattiByName(String name){
        Session session = SingletonSessionFactory.openSession();
        String hqlSelect = "SELECT c FROM Contatto AS c WHERE id LIKE %" + name + "%";
        return session.createQuery(hqlSelect, Contatto.class).list();
    }

    public static List<Contatto> searchBy(String col, String searchQuery){
        Session session = SingletonSessionFactory.openSession();
        String hqlSelect = "SELECT c FROM Contatto AS c WHERE :col LIKE %" + searchQuery + "%";
        return session.createQuery(hqlSelect, Contatto.class)
                .setParameter("col", col)
                .list();
    }
}
