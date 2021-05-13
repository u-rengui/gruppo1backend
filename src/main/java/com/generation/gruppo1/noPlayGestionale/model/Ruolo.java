package com.generation.gruppo1.noPlayGestionale.model;
import com.generation.gruppo1.noPlayGestionale.util.IMappablePro;

import java.util.HashMap;
import java.util.Map;

public class Ruolo implements IMappablePro{

    private int id;
    private String mansione;

    public Ruolo(int id, String mansione) {
        super();
        this.id = id;
        this.mansione = mansione;
    }

    public Ruolo() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMansione() {
        return mansione;
    }

    public void setMansione(String mansione) {
        this.mansione = mansione;
    }

    @Override
    public String toString() {
        return "{id:" + id + ", mansione:" + mansione + "}";
    }

    public Map<String, String> isValidForStorage(){
        Map<String, String> validationMap = new HashMap<>(Map.of("status", "success"));
        if(mansione == null || mansione.equals("")) {
            validationMap.replace("status", "error");
            validationMap.put("message", "Field: 'mansione' not valid or null or not present");
        }
        return validationMap;
    }
}