package com.generation.gruppo1.noPlayGestionale.controller;

import com.generation.gruppo1.noPlayGestionale.model.Utente;
import com.generation.gruppo1.noPlayGestionale.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/accountmanager")
    public String get() {
        return "<h1>Welcome account manager</h1>";
    }

    @GetMapping("/detail")
    public Utente test(@AuthenticationPrincipal Utente utente) {
        return utente;
    }

    @GetMapping("/secured")
    public Utente detail(@AuthenticationPrincipal Utente utente) {
        return utente;
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String email, @RequestParam String password,
                         @RequestParam String nome, @RequestParam String cognome, @RequestParam String cellulare) {
        System.out.print("**** entro in signup api controller");
        userService.signup(email, password, nome, cognome, cellulare);
        System.out.print("**** esco in signup api controller");
        return "OK";
    }
}
