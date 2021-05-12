package com.generation.gruppo1.noPlayGestionale.controller;

import java.util.List;

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
import com.generation.gruppo1.noPlayGestionale.model.Personale;

@RestController
@RequestMapping("/api/personale")
public class PersonaleController {
	
	@Autowired
    IDaoSql dao;
	
	@GetMapping("/findAll")
    public ResponseEntity<List<Personale>> getPersonale() throws NotFoundException {
        List<Personale> personale = dao.findAllPersonale();
        if(personale == null){
            String ErrMsg = "La lista del personale è vuota";
            throw new NotFoundException(ErrMsg);
        }
        return new ResponseEntity<List<Personale>>(personale, HttpStatus.OK);
    }
	
	@GetMapping("/findById/{id}")
    public ResponseEntity<Personale> getOnePersonale(@PathVariable int id) throws NotFoundException {
        Personale personale = dao.findPersonaleById(id);
        if(personale == null){
            String ErrMsg = "Non è stato trovato alcun componente del personale con l'id selezionato";
            throw new NotFoundException(ErrMsg);
        }
        return new ResponseEntity<Personale>(personale, HttpStatus.OK);
    }
	
	@PostMapping()
    public void addPersonale(@RequestBody Personale personale) throws NotFoundException {
        dao.addPersonale(personale);
        if(personale == null){
            String ErrMsg = "Non è stato aggiunto alcun componente del personale";
            throw new NotFoundException(ErrMsg);
        }
    }
	
	@DeleteMapping("/{id}")
	public void deletePersonale(@PathVariable int id) throws NotFoundException {
        dao.deletePersonale(id);
//        if(id < 0 ){
//            String ErrMsg = "Non è stato eliminato alcun componente del personale";
//            throw new NotFoundException(ErrMsg);
//        }
    }

	@PutMapping()
    public void updatePersonale(@RequestBody Personale personale) throws NotFoundException {
        dao.updatePersonale(personale);
        if(personale == null){
            String ErrMsg = "Non è stato possibile modificare alcun componente del personale";
            throw new NotFoundException(ErrMsg);
        }
    }
	
}
