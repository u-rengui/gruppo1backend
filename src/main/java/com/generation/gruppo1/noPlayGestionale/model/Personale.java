package com.generation.gruppo1.noPlayGestionale.model;

import java.util.List;

import com.generation.gruppo1.noPlayGestionale.util.IMappablePro;

public class Personale implements IMappablePro {
    private int id;
    private String nome;
    private String cognome;
    private String ddn;
    private double stipendio;
    private String dataassunzione;
    private Ruolo ruolo;
    private List<Clienti> clienti;

    public Personale(int id, String nome, String cognome, String ddn, double stipendio, String dataassunzione, Ruolo ruolo, List<Clienti> clienti) {
        super();
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.ddn = ddn;
        this.stipendio = stipendio;
        this.dataassunzione = dataassunzione;
        this.ruolo = ruolo;
        this.clienti = clienti;
    }

    public Personale() {
        super();
    }

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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getDdn() {
        return ddn;
    }

    public void setDdn(String ddn) {
        this.ddn = ddn;
    }

    public double getStipendio() {
        return stipendio;
    }

    public void setStipendio(double stipendio) {
        this.stipendio = stipendio;
    }

    public String getDataassunzione() {
        return dataassunzione;
    }

    public void setDataassunzione(String dataassunzione) {
        this.dataassunzione = dataassunzione;
    }

    public Ruolo getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }

    public List<Clienti> getClienti() {
        return clienti;
    }

    public void setClienti(List<Clienti> clienti) {
        this.clienti = clienti;
    }

    @Override
    public String toString() {
        return "{id:" + id + ", nome:" + nome + ", cognome:" + cognome + ", ddn:" + ddn + ", stipendio:" + stipendio
                + ", dataassunzione:" + dataassunzione + ", ruolo:" + ruolo + ", clienti:" + clienti + "}";
    }

}