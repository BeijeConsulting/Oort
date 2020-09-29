package it.beije.oort.franceschi.hibernateRubrica;

import it.beije.oort.franceschi.rubrica.Contatto;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Database Reader using the Hibernate Framework
 */
public class HDBReader {
    /**
     * Returns every Contatto in the Database
     * @return a list containing every Contatto in the Database
     */
    public static List<Contatto> readAll(){
        Session session = SingletonSessionFactory.openSession();
        String hqlSelectAll = "SELECT c FROM Contatto AS c";
        Query<Contatto> query = session.createQuery(hqlSelectAll, Contatto.class);
        session.close();
        return query.list();
    }

    /**
     * Returns a contatto given an ID
     * @param id the id of the contact to search
     * @return the Contatto object with the corresponding ID
     */
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

    /**
     * Returns a list of Contatto given an array of IDs
     * @param id The array of IDs to search
     * @return a List containing all the Contatto retrieved by ID
     */
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

    /**
     * Search the Database by a Column and a Parameter
     * @param col The column to search
     * @param value The query to search in the column
     * @return a List containing all the query results
     */
    public static List<Contatto> searchBy(String col, String value){
        // Dopo tre ore (letteralmente) di ricerca e test, questo è l'unico metodo che funziona.
        // Nota: Funziona solamente con le String. Testato con int, non va.
        // Segue spiegazione dettagliata
        // Nota2: apparentemente funziona anche con %, ma mi ero dimenticato di mettere gli apici e non andava nulla :)

        // Creo la sessione
        Session session = SingletonSessionFactory.openSession();

        // Creo un oggetto CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Da lì creo un CriteriaQuery e creo una query relativa alla classe Contatto
        CriteriaQuery<Contatto> criteria = builder.createQuery(Contatto.class);

        // Questo non so cosa faccia, ma penso definisca l'oggetto di base su cui si lavorerà
        Root<Contatto> contattoRoot = criteria.from(Contatto.class);
        /*
         Creo il predicato che definisce l'operazione LIKE
         contattoRoot.get(col) seleziona la colonna/field dall'oggetto/database
         il secondo argomento è il valore che deve "cercare" nella colonna che gli dico con il primo parametro
         */
        Predicate likeRestriction = builder.like(contattoRoot.get(col), "%" + value + "%");

        // Qui in pratica gli dico "SELECT _cosa_ WHERE _la condizione_
        criteria.select(contattoRoot).where(likeRestriction);

        // Qui creo la Query. Non so perché TypedQuery, ma SQLQuery è deprecata e NativeQuery non funziona.
        TypedQuery<Contatto> query = session.createQuery(criteria);

        // Ritorno il risultato sotto forma di list.
        return query.getResultList();
    }

    // Questo è un search che funziona senza fare Criteria, Predicate, TypedQuery e quant altro
    // Mi ero dimenticato gli apici, quindi ormai tengo entrambe le versioni
    public static List<Contatto> easySearchBy(String col, String value){
        Session session = SingletonSessionFactory.openSession();
        String likeValue = "'%" + value + "%'";
        String hql = "SELECT c FROM Contatto as c WHERE " + col + " LIKE " + likeValue;
        Query<Contatto> query = session.createQuery(hql, Contatto.class);
        return query.list();
    }
}
