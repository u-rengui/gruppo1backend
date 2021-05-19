package com.generation.gruppo1.noPlayGestionale.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.generation.gruppo1.noPlayGestionale.dao.IDaoSql;
import com.generation.gruppo1.noPlayGestionale.exception.BindingException;
import com.generation.gruppo1.noPlayGestionale.exception.NotFoundException;
import com.generation.gruppo1.noPlayGestionale.model.Clienti;
import com.generation.gruppo1.noPlayGestionale.model.Personale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/personale")
public class PersonaleController {
    @Autowired
    IDaoSql dao;

    @GetMapping("/findAll")
    public ResponseEntity<List<Personale>> getPersonale() throws NotFoundException {
        List<Personale> personale = dao.findAllPersonale();
        if(personale == null){
            String ErrMsg = "La lista personale è vuota";
            throw new NotFoundException(ErrMsg);
        }
        return new ResponseEntity<List<Personale>>(personale, HttpStatus.OK);
    }

    @GetMapping("/findAllTest")
    public ResponseEntity<?> getPersonaleTest() throws NotFoundException {
        List<Map<String, String>> listaMappe = new ArrayList<>();
        Map<String, String> mappa = Map.of(
                "nome", "NomeProba",
                "cognome", "cognome22123",
                "ruolo", "ruolotest"
        );
        listaMappe.add(mappa);
        return new ResponseEntity<>(listaMappe, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Personale> getOnePersonale(@PathVariable int id) throws NotFoundException {
        Personale personale = dao.findPersonaleById(id);
        if(personale == null){
            String ErrMsg = "Non è stato trovato alcun personale con l'id selezionato";
            throw new NotFoundException(ErrMsg);
        }
        return new ResponseEntity<Personale>(personale, HttpStatus.OK);
    }

    @PostMapping("/addPersonale")
    public ResponseEntity<?> addPersonale(@RequestBody Personale personale) {
        dao.addPersonale(personale);
        Map<String, String> responseMap = Map.of(
                "code", HttpStatus.OK.toString(),
                "message", "Personale " + personale.getCognome() + " aggiunto con successo");
        return responseToClient(responseMap);
    }

    @DeleteMapping("/deletePersonale/{id}")
    public ResponseEntity<?> deletePersonale(@PathVariable int id) throws Exception {
        Personale personale = dao.findPersonaleById(id);
        if(personale == null) {
            String MsgErr = String.format("Personale con id: %d non presente! ", id);
            throw new NotFoundException(MsgErr);
        }
        if(dao.deletePersonale(id)){
            Map<String, String> responseMap = Map.of(
                    "code", HttpStatus.OK.toString(),
                    "message", "Personale " + personale.getId() + " eliminato con successo");
            return responseToClient(responseMap);
        }
        else{
            Map<String, String> responseMap = Map.of(
                    "code", HttpStatus.NOT_MODIFIED.toString(),
                    "message", "Personale " + personale.getId() + " eliminazione fallita.");
            return responseToClient(responseMap);
        }
    }

    @PutMapping("updatePersonale")
    public ResponseEntity<?> updatePersonale(@RequestBody Personale personale) throws NotFoundException, BindingException {
        Personale checkPersonale = dao.findPersonaleById(personale.getId());
        if(checkPersonale == null) {
            String MsgErr = String.format("Personale con id: %d non presente, niente da modificare! ", personale.getId());
            throw new NotFoundException(MsgErr);
        }
        if(!dao.updatePersonale(personale))
            throw new BindingException("Binding Error, check the correct data form");
        Map<String, String> responseMap = Map.of(
                "code", HttpStatus.OK.toString(),
                "message", "Personlae " + personale.getId() + " modificato con successo");
        return responseToClient(responseMap);
    }

    private ResponseEntity<?> responseToClient(Map<String, String> responseMap){
        HttpHeaders headers = new HttpHeaders();
        ObjectMapper mapper = new ObjectMapper();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectNode responseNode = mapper.createObjectNode();
        responseMap.forEach(responseNode::put);
        return new ResponseEntity<>(responseNode, headers, HttpStatus.CREATED);
    }
}
