package com.generation.gruppo1.noPlayGestionale.dao;

import com.generation.gruppo1.noPlayGestionale.model.Clienti;
import com.generation.gruppo1.noPlayGestionale.model.Personale;

import java.util.List;

public interface IDaoSql {
    List<Clienti> findAllClienti();
    Clienti findClienteById();
    void addCliente(Clienti cliente);
    void deleteCliente(int id);
    void updateCliente(Clienti cliente);

    List<Personale> findAllPersonale();
    Personale findPersonaleById();
    void addPersonale(Personale personale);
    void deletePersonale(int id);
    void updatePersonale(Personale personale);
}
