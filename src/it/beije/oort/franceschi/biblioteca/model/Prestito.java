package it.beije.oort.franceschi.biblioteca.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "prestiti")
public class Prestito implements IBibliotecaModel {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData_inizio() {
        return data_inizio;
    }

    public void setData_inizio(Date data_inizio) {
        this.data_inizio = data_inizio;
    }

    public Date getData_fine() {
        return data_fine;
    }

    public void setData_fine(Date data_fine) {
        this.data_fine = data_fine;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCf_utente() {
        return cf_utente;
    }

    public void setCf_utente(String cf_utente) {
        this.cf_utente = cf_utente;
    }

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }
}
