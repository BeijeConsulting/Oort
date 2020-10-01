package it.beije.oort.franceschi.biblioteca.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="libri")
public class Libro implements IBibliotecaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String titolo;

    @Column
    private String descrizione;

    @Column
    private Date anno_pubblicazione;

    @Column
    private int id_autore;

    @Column
    private int id_editore;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Date getAnno_pubblicazione() {
        return anno_pubblicazione;
    }

    public void setAnno_pubblicazione(Date anno_pubblicazione) {
        this.anno_pubblicazione = anno_pubblicazione;
    }

    public int getId_autore() {
        return id_autore;
    }

    public void setId_autore(int id_autore) {
        this.id_autore = id_autore;
    }

    public int getId_editore() {
        return id_editore;
    }

    public void setId_editore(int id_editore) {
        this.id_editore = id_editore;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ") Titolo: " + titolo +
                ". Anno: " + anno_pubblicazione +
                ". ID Autore: " + id_autore +
                ". ID Editore: " + id_editore +
                ".\n\tDescrizione: " + descrizione;
    }
}
