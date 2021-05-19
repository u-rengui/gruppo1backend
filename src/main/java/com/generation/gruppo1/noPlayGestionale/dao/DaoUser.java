package com.generation.gruppo1.noPlayGestionale.dao;

import com.generation.gruppo1.noPlayGestionale.model.Roles;
import com.generation.gruppo1.noPlayGestionale.model.Utente;
import com.generation.gruppo1.noPlayGestionale.util.BasicDao;
import com.generation.gruppo1.noPlayGestionale.util.IMappablePro;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class DaoUser extends BasicDao implements IDaoUser{
    private static final String UTENTE_WHERE_EMAIL = "SELECT * FROM utenti WHERE email = ?";
    private static final String INSERT_INTO_UTENTI = "INSERT INTO utenti (email, psw, nome, cognome, cellulare, ruolo) VALUES (?,?,?,?,?,?)";

    public DaoUser(@Value("${db.address}") String dbAddress,
                  @Value("${db.user}") String user,
                  @Value("${db.psw}") String password ) {
        super(dbAddress, user, password);
    }

    @Override
    public Utente findByEmail(String email) {
        System.out.print("\n\n ***  entro in find By email");
        Utente ris = null;
        Map<String, String> map = getOne(UTENTE_WHERE_EMAIL, email);
        if(map != null) {
            ris = IMappablePro.fromMap(Utente.class, map);
        }
        System.out.print("\n \n ** FindByEmail Ris: " + ris);
        return ris;
    }

    @Override
    public boolean save(Utente utente) {
        System.out.print("entro in save user");
        return execute(INSERT_INTO_UTENTI, utente.getEmail(), utente.getPsw(),
                utente.getNome(), utente.getCognome(), utente.getCellulare(), Roles.USER);
    }
}
