package com.generation.gruppo1.noPlayGestionale;

import com.generation.gruppo1.noPlayGestionale.model.Roles;
import com.generation.gruppo1.noPlayGestionale.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
// Configurazione base di SpringSecurity, andiamo a definirne uno nostro
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    // Criptare le password prima di salvarle
    private final PasswordEncoder passwordEncoder;
    // Il servizio che ci fornisce gli utenti
    private final UserService authService;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, UserService authService) {
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // la disabilito se mi serve utilizzare l'app anche come sistema rest per applicazioni "esterne"
                .httpBasic().and()
                .authorizeRequests().antMatchers("/", "index.html", "/css/**", "/js/**", "/assets/**",
                "/signup.html", "/signup",
                "/login", "/fail.html", "/forbidden.html",
                "/css/login.css"
                ).permitAll() // tutti possono accedere a questi percorsi

                // ** ACCESSO API
                .antMatchers("/api/ruoli/**").hasAnyRole(Roles.ADMIN, Roles.USER)
                .antMatchers("/api/personale/**").hasAnyRole(Roles.ADMIN, Roles.USER)
                .antMatchers("/api/clienti/**").hasAnyRole(Roles.ADMIN, Roles.USER)
                // ** ACCESSO PAGINE
                .antMatchers("/altro.html").hasAnyRole(Roles.ADMIN, Roles.USER)
                .antMatchers("/account.html").hasAnyRole(Roles.ADMIN, Roles.USER)
                .antMatchers("/accountmanager/**").hasAnyRole(Roles.ADMIN) // solo gli admin accedono a /management/**
                .anyRequest().authenticated() // tutte le (altre) richieste richiedono authenticazione
                .and()
                .exceptionHandling()
                .accessDeniedPage("/forbidden.html")
                .and()
                // SpringSecurity di base ci fornisce una pagina di default, ma andiamo a cambiarla con una nostra
                .formLogin()
                .loginPage("/login.html") // indirizzo a cui arriveranno le richieste login
                .loginProcessingUrl("/login")
                .permitAll() // Giustamente tutti devono riuscire ad accedere
                .defaultSuccessUrl("/index.html", true) // se riesce ad accedere lo rimando ad index.html
                .failureUrl("/fail.html")
                .and()
                // configuriamo anche la pagina per il logout
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/loggedout.html")// indirizzo per fare logout
                .clearAuthentication(true).logoutSuccessUrl("/index.html")
        ;
    }

    @Bean
    // questo oggetto servirà a spring security per andare a cercare gli utenti da un service
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(authService);
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

}