package it.beije.oort.franceschi.biblioteca.controller;

import it.beije.oort.db.JPAEntityManager;
import it.beije.oort.franceschi.biblioteca.model.IBibliotecaModel;

import javax.persistence.EntityManager;

public class JPADatabaseOperationsManager {
    private final EntityManager em;

    public JPADatabaseOperationsManager(String database) {
        em = JPAEntityManager.getEntityManager(database);
    }

    public void insert(IBibliotecaModel object){
        em.getTransaction().begin();
        em.persist(object);
        em.getTransaction().commit();
    }

    public IBibliotecaModel select(Class<? extends IBibliotecaModel> classe, int id){
        return em.find(classe, id);
    }

    public void update(){
        // select(obj)
        // modify it
        // insert (obj)
    }
}
