package com.generation.gruppo1.noPlayGestionale.model;

import com.generation.gruppo1.noPlayGestionale.util.IMappablePro;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Utente implements IMappablePro, UserDetails {
    private String email;
    private String psw;
    private String nome;
    private String cognome;
    private String cellulare;
    private String ruolo;

    private static final Map<String, Collection<? extends GrantedAuthority>> AUTHORITIES = new HashMap<>();
    {
        AUTHORITIES.put(Roles.ADMIN, Arrays.asList(new GrantedAuthority[] { new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("management"), }));
        AUTHORITIES.put(Roles.USER, Arrays.asList(new GrantedAuthority[] { new SimpleGrantedAuthority("ROLE_USER") }));

    }

    public static Collection<? extends GrantedAuthority> getAuthorityOfRole(String role) {
        return AUTHORITIES.get(role);
    }

    /**
     *
     */
    private static final long serialVersionUID = 1237489217380966710L;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getAuthorityOfRole(this.ruolo);
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPsw() {
        return psw;
    }
    public void setPsw(String psw) {
        this.psw = psw;
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
    public String getCellulare() {
        return cellulare;
    }
    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }
    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "email='" + email + '\'' +
                ", psw='" + psw + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", cellulare='" + cellulare + '\'' +
                '}';
    }

    @Override
    public String getPassword() {
        return psw;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
