package com.generation.gruppo1.noPlayGestionale.dao;

import com.generation.gruppo1.noPlayGestionale.model.Clienti;
import com.generation.gruppo1.noPlayGestionale.model.Personale;
import com.generation.gruppo1.noPlayGestionale.model.Ruolo;

import java.util.List;

public interface IDaoSql {
    List<Clienti> findAllClienti();
    Clienti findClienteById(int id);
    boolean addCliente(Clienti cliente);
    boolean deleteCliente(int id);
    boolean updateCliente(Clienti cliente);

    List<Personale> findAllPersonale();
    Personale findPersonaleById(int id);
    void addPersonale(Personale personale);
    boolean deletePersonale(int id);
    boolean updatePersonale(Personale personale);

    List<Ruolo> findAllRuoli();
    Ruolo findRuoloById(int id);
    boolean addRuolo(Ruolo ruolo);
    boolean deleteRuolo(int id);
    boolean updateRuolo(Ruolo ruolo);
}
