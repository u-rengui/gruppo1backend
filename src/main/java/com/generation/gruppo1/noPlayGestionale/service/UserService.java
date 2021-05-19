package com.generation.gruppo1.noPlayGestionale.service;

import com.generation.gruppo1.noPlayGestionale.dao.DaoUser;
import com.generation.gruppo1.noPlayGestionale.exception.NotFoundException;
import com.generation.gruppo1.noPlayGestionale.model.Roles;
import com.generation.gruppo1.noPlayGestionale.model.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private DaoUser dao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(DaoUser dao, PasswordEncoder passwordEncoder) {
        this.dao = dao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override// viene invocato dal login submit form tramite il path /login predefinito
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<? extends UserDetails> utente = Optional.ofNullable(dao.findByEmail(email));

        if (utente.isPresent())
            return utente.get();

        throw new UsernameNotFoundException("Nessun utente con username: " + email);
    }

    public void signup(String email, String password, String nome, String cognome, String cellulare) {
        System.out.print("*** entro in signup Userservice");
        Utente newUtente = new Utente();
        newUtente.setEmail(email);
        newUtente.setPsw(passwordEncoder.encode(password));
        newUtente.setNome(nome);
        newUtente.setCognome(cognome);
        newUtente.setCellulare(cellulare);
        newUtente.setRuolo(Roles.USER);
        try {
            dao.save(newUtente);
            System.out.print("**** void signup try dao.save");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}