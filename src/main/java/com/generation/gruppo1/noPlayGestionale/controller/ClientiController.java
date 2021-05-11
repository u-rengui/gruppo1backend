package com.generation.gruppo1.noPlayGestionale.controller;

import com.generation.gruppo1.noPlayGestionale.dao.IDaoSql;
import com.generation.gruppo1.noPlayGestionale.exception.NotFoundException;
import com.generation.gruppo1.noPlayGestionale.model.Clienti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clienti")
public class ClientiController {
    @Autowired
    IDaoSql dao;

    @GetMapping("/findAll")
    public ResponseEntity<List<Clienti>> getArticoli() throws NotFoundException {
        List<Clienti> clienti = dao.findAllClienti();
        if(clienti == null){
            String ErrMsg = "La lista articoli è vuota";
            throw new NotFoundException(ErrMsg);
        }
        return new ResponseEntity<List<Clienti>>(clienti, HttpStatus.OK);
    }
}
