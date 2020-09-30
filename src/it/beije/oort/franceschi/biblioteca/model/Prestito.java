package it.beije.oort.franceschi.biblioteca.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "prestiti")
public class Prestito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private Date data_inizio;

    @Column
    private Date data_fine;

    @Column
    private String note;

    @Column
    private String cf_utente;

    @Column
    private int id_libro;
}
