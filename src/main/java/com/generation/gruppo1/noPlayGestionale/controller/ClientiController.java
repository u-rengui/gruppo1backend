package com.generation.gruppo1.noPlayGestionale.controller;

import com.generation.gruppo1.noPlayGestionale.dao.IDaoSql;
import com.generation.gruppo1.noPlayGestionale.exception.NotFoundException;
import com.generation.gruppo1.noPlayGestionale.model.Clienti;

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

import java.util.List;

@RestController
@RequestMapping("/api/clienti")
public class ClientiController {
    @Autowired
    IDaoSql dao;

    // hello
    
    @GetMapping("/findAll")
    public ResponseEntity<List<Clienti>> getClienti() throws NotFoundException {
        List<Clienti> clienti = dao.findAllClienti();
        if(clienti == null){
            String ErrMsg = "La lista clienti è vuota";
            throw new NotFoundException(ErrMsg);
        }
        return new ResponseEntity<List<Clienti>>(clienti, HttpStatus.OK);
    }
    
    @GetMapping("/findById/{id}")
    public ResponseEntity<Clienti> getOneClienti(@PathVariable int id) throws NotFoundException {
        Clienti clienti = dao.findClienteById(id);
        if(clienti == null){
            String ErrMsg = "Non è stato trovato alcun cliente con l'id selezionato";
            throw new NotFoundException(ErrMsg);
        }
        return new ResponseEntity<Clienti>(clienti, HttpStatus.OK);
    }
    
    @PostMapping()
    public void addCliente(@RequestBody Clienti cliente) throws NotFoundException {
        dao.addCliente(cliente);
        if(cliente == null){
            String ErrMsg = "Non è stato aggiunto alcun cliente";
            throw new NotFoundException(ErrMsg);
        }
    }
    
    @DeleteMapping("/{id}")
	public void deleteCliente(@PathVariable int id) throws NotFoundException {
        dao.deleteCliente(id);
//        if(id < 0 ){
//            String ErrMsg = "Non è stato eliminato alcun cliente";
//            throw new NotFoundException(ErrMsg);
//        }
    }
    
    @PutMapping()
    public void updateCliente(@RequestBody Clienti cliente) throws NotFoundException {
        dao.updateCliente(cliente);
        if(cliente == null){
            String ErrMsg = "Non è stato possibile modificare alcun cliente";
            throw new NotFoundException(ErrMsg);
        }
    }
    
}
