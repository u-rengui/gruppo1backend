package com.generation.gruppo1.noPlayGestionale.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.generation.gruppo1.noPlayGestionale.util.IMappablePro;
import org.springframework.http.HttpStatus;

public class Clienti implements IMappablePro{

    private int id;
    private String ragionesociale;
    private String partitaiva;
    private String indirizzo;
    private String email;
    private String ntel;
    private List<Personale> personale;

    public Clienti(int id, String ragionesociale, String partitaiva, String indirizzo, String email, String ntel, List<Personale> personale) {
        super();
        this.id = id;
        this.ragionesociale = ragionesociale;
        this.partitaiva = partitaiva;
        this.indirizzo = indirizzo;
        this.email = email;
        this.ntel = ntel;
        this.personale = personale;
    }

    public Clienti() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRagionesociale() {
        return ragionesociale;
    }

    public void setRagionesociale(String ragionesociale) {
        this.ragionesociale = ragionesociale;
    }

    public String getPartitaiva() {
        return partitaiva;
    }

    public void setPartitaiva(String partitaiva) {
        this.partitaiva = partitaiva;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNtel() {
        return ntel;
    }

    public void setNtel(String ntel) {
        this.ntel = ntel;
    }

    public List<Personale> getPersonale() {
        return personale;
    }

    public void setPersonale(List<Personale> personale) {
        this.personale = personale;
    }

    @Override
    public String toString() {
        return "{id:" + id + ", ragionesociale:" + ragionesociale + ", partitaiva:" + partitaiva + ", indirizzo:"
                + indirizzo + ", email:" + email + ", ntel:" + ntel + ", personale:" + personale + "}";
    }

    public Map<String, String> isValidForStorage(){
        System.out.println("start validation");
        String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

        Map<String, String> validationMap = new HashMap<>(Map.of("status", "success"));
        if(ragionesociale == null || ragionesociale.equals("")) {
            validationMap.replace("status", "error");
            validationMap.put("message", "ragione sociale not valid or null");
        }
        else if(email == null || !email.matches(emailRegex)){
            validationMap.replace("status", "error");
            validationMap.put("message", "email not valid or null");
        }
        return validationMap;
    }
}
