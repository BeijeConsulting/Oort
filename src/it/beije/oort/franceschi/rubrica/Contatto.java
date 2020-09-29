package it.beije.oort.franceschi.rubrica;

import it.beije.oort.franceschi.rubrica.utils.GeneraMail;
import it.beije.oort.franceschi.rubrica.utils.GeneraNumero;
import it.beije.oort.franceschi.rubrica.utils.Valori;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Random;

@Entity
@Table(name = "rubrica")
public class Contatto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "telefono")
    private String cell = "";

    @Column(name = "email")
    private String email = "";

    public Contatto() {
    }

    public Contatto(boolean isRandom) {
        Random r = new Random();
        this.nome = Valori.getNomi().get(r.nextInt(Valori.getNomi().size()));
        this.cognome = Valori.getCognomi().get(r.nextInt(Valori.getCognomi().size()));
        this.cell = GeneraNumero.generaNumero();
        this.email = GeneraMail.generaMail(nome, cognome);
    }

    public Contatto(ArrayList<String> nomi, ArrayList<String> cognomi) {
        Random r = new Random();
        this.nome = nomi.get(r.nextInt(nomi.size()));
        this.cognome = cognomi.get(r.nextInt(cognomi.size()));
        this.cell = GeneraNumero.generaNumero();
        this.email = GeneraMail.generaMail(nome, cognome);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCell() {
        return cell;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the formatted string following the CSV convention with quotes.
     */
    public String toCSVString() {
        return "\"" + cognome + "\";\"" + nome + "\";\"" + email +
                "\";\"" + cell + "\";\"" +
                "\"";
    }

    public String toString() {
        return "Nome: " + this.getNome() + ". Cognome: " + this.getCognome() + ". Email: "
                + this.getEmail() + ". Telefono: " + this.getCell();
    }

    public String toNakedString(){
        return this.getNome() + "\t\t" + this.getCognome() + "\t\t"
                + this.getEmail() + "\t\t" + this.getCell();
    }

    public static String getContattoFormattatoToString(ArrayList<Contatto> rubrica, int i, Random r) {
        StringBuilder s = new StringBuilder();
        Contatto c = rubrica.get(i);
        if ((r.nextInt(3) + 1) != 1) {
            s.append(c.getCognome());
        }
        s.append(";");
        if ((r.nextInt(5) + 1) != 1) {
            s.append(c.getNome());
        }
        s.append(";").append(c.getCell()).append(";").append(c.getEmail());
        return s.toString();
    }

    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.email != null ? this.email.hashCode() : 0);
        hash += 53 * hash + (this.cell != null ? this.cell.hashCode() : 0);
        hash += 53 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash += 53 * hash + (this.cognome != null ? this.cognome.hashCode() : 0);
        return hash;
    }


    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;

        final Contatto c = (Contatto) obj;
        return (this.email == null) ? (c.getEmail() == null) : this.email.equals(c.getEmail());
    }
}


