package it.beije.oort.franceschi.biblioteca.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "autori")
public class Autore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String nome;

    @Column
    private String cognome;

    @Column
    private String biografia;

    @Column
    private Date data_nascita;

    @Column
    private Date data_morte;
}
