package it.beije.oort.franceschi.biblioteca.controller;

import it.beije.oort.db.JPAEntityManager;

import javax.persistence.EntityManager;

public class Operations {
    private Operations(){}

    private final static EntityManager em = JPAEntityManager.getEntityManager("Biblioorteca");
}
