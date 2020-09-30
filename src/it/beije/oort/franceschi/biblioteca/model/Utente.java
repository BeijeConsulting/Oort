package it.beije.oort.franceschi.biblioteca.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "utenti")
public class Utente {
    @Id
    @Column
    private String codice_fiscale;

    @Column
    private String nome;

    @Column
    private String cognome;

    @Column
    private String email;

    @Column
    private String cellulare;

    @Column
    private String indirizzo;
}
