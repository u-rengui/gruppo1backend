package com.generation.gruppo1.noPlayGestionale.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.gruppo1.noPlayGestionale.dao.IDaoSql;
import com.generation.gruppo1.noPlayGestionale.exception.NotFoundException;
import com.generation.gruppo1.noPlayGestionale.model.Ruolo;

import java.util.List;

@RestController
@RequestMapping("/api/ruoli")
public class RuoliController {

    @Autowired
    IDaoSql dao;

    @GetMapping("/findAll")
    public ResponseEntity<List<Ruolo>> getRuoli() throws NotFoundException {
        List<Ruolo> ruoli = dao.findAllRuoli();
        if(ruoli == null){
            String ErrMsg = "La lista clienti è vuota";
            throw new NotFoundException(ErrMsg);
        }
        return new ResponseEntity<List<Ruolo>>(ruoli, HttpStatus.OK);
    }

//    @GetMapping("/findById/{id}")
//    public ResponseEntity<Ruolo> getOneRuolo(@PathVariable int id) throws NotFoundException {
//    	Ruolo ruoli = dao.findRuoloById(id);
//        if(ruoli == null){
//            String ErrMsg = "Non è stato trovato alcun ruolo con l'id selezionato";
//            throw new NotFoundException(ErrMsg);
//        }
//        return new ResponseEntity<Ruolo>(ruoli, HttpStatus.OK);
//    }

    @PostMapping()
    public void addRuolo(@RequestBody Ruolo ruolo) throws NotFoundException {
        dao.addRuolo(ruolo);
        if(ruolo == null){
            String ErrMsg = "Non è stato aggiunto alcun ruolo";
            throw new NotFoundException(ErrMsg);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteRuolo(@PathVariable int id) throws NotFoundException {
        dao.deleteRuolo(id);
        if(id < 0 ){
            String ErrMsg = "Non è stato eliminato alcun cliente";
            throw new NotFoundException(ErrMsg);
        }
    }

    @PutMapping()
    public void updateRuolo(@RequestBody Ruolo ruolo) throws NotFoundException {
        dao.updateRuolo(ruolo);
        if(ruolo == null){
            String ErrMsg = "Non è stato possibile modificare alcun ruolo";
            throw new NotFoundException(ErrMsg);
        }
    }

}