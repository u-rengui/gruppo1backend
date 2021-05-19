package com.generation.gruppo1.noPlayGestionale.dao;

import com.generation.gruppo1.noPlayGestionale.model.Utente;

public interface IDaoUser {
    Utente findByEmail(String email);
    boolean save(Utente utente);
}
