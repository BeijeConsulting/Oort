package it.beije.oort.franceschi.biblioteca.model;

import javax.persistence.*;

@Entity
@Table(name = "editori")
public class Editore implements IBibliotecaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String nome;

    @Column
    private String descrizione;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ") Nome: " + nome +
                ". Descrizione: " + descrizione;
    }
}
