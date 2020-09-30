package it.beije.oort.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class JPAEntityManager {
    private JPAEntityManager(){}

    private static final Map<String, EntityManager> managerMap = new HashMap<>();

    public static EntityManager getEntityManager(String database){
        if (managerMap.get(database) == null){
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(database);
            EntityManager em = entityManagerFactory.createEntityManager();
            managerMap.put(database, em);
        }
        return managerMap.get(database);
    }
}