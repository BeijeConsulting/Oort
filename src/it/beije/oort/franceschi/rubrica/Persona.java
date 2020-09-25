package it.beije.oort.franceschi.rubrica;

import java.util.Random;

public class Persona {
    private final String nome;
    private final String cognome;
    private final String tel;
    private final String email;
    String[] prefissi = {"345", "346", "347", "348", "349"};
    String[] suffissoMail = {"@libero.it", "@gmail.com", "@hotmail.it", "@hotmail.com", "@yahoo.com", "@tim.it", "@virgilio.it", "@alice.it"};

    public Persona(String[] nomi, String[] cognomi) {
        Random r = new Random();
        this.nome = nomi[r.nextInt(nomi.length)];
        this.cognome = cognomi[r.nextInt(cognomi.length)];
        this.tel = prefissi[r.nextInt(prefissi.length)] + getRandomCell();
        this.email = nome + "." + cognome + suffissoMail[r.nextInt(suffissoMail.length)];
    }

    private String getRandomCell() {
        StringBuilder s = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 7; i++) {
            s.append(r.nextInt(10));
        }
        return s.toString();
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }
}
