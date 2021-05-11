package com.generation.gruppo1.noPlayGestionale.dao;

import com.generation.gruppo1.noPlayGestionale.model.Clienti;
import com.generation.gruppo1.noPlayGestionale.model.Personale;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoSql implements IDaoSql{
    String dbAddress;
    String user;
    String password;

    public DaoSql(@Value("${db.address}") String dbAddress,
                          @Value("${db.user}") String user,
                          @Value("${db.psw}") String password) {
        this.dbAddress = dbAddress;
        this.user = user;
        this.password = password;
    }

    @Override
    public List<Clienti> findAllClienti() {
        return null;
    }

    @Override
    public Clienti findClienteById() {
        return null;
    }

    @Override
    public void addCliente(Clienti cliente) {

    }

    @Override
    public void deleteCliente(int id) {

    }

    @Override
    public void updateCliente(Clienti cliente) {

    }

    @Override
    public List<Personale> findAllPersonale() {
        return null;
    }

    @Override
    public Personale findPersonaleById() {
        return null;
    }

    @Override
    public void addPersonale(Personale personale) {

    }

    @Override
    public void deletePersonale(int id) {

    }

    @Override
    public void updatePersonale(Personale personale) {

    }
}
