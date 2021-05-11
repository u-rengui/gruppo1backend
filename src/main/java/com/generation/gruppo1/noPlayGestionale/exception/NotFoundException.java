package com.generation.gruppo1.noPlayGestionale.exception;

public class NotFoundException  extends Exception {
    private String messaggio = "Elemento Ricercato Non Trovato!";

    public NotFoundException() {
        super();
    }

    public NotFoundException(String Messaggio) {
        super(Messaggio);
        this.messaggio = Messaggio;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }
}
