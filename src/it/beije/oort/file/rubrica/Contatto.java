package it.beije.oort.file.rubrica;

import it.beije.oort.file.rubrica.utils.GeneraMail;
import it.beije.oort.file.rubrica.utils.GeneraNumero;
import it.beije.oort.file.rubrica.utils.Valori;

import java.util.ArrayList;
import java.util.Random;

public class Contatto {
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    private int ID;
    private String nome;
    private String cognome;
    private String cell = "";
    private String email = "";

    public Contatto() {
    }

    public Contatto(boolean random) {
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
    public String toFormattedString() {
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
        return hash;
    }


    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Contatto c = (Contatto) obj;

        return (this.email == null) ? (c.getEmail() == null) : this.email.equals(c.getEmail());
    }
}


