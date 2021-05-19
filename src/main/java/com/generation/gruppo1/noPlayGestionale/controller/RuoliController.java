package com.generation.gruppo1.noPlayGestionale.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.generation.gruppo1.noPlayGestionale.exception.BindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.generation.gruppo1.noPlayGestionale.dao.IDaoSql;
import com.generation.gruppo1.noPlayGestionale.exception.NotFoundException;
import com.generation.gruppo1.noPlayGestionale.model.Ruolo;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ruoli")
public class RuoliController {

    @Autowired
    IDaoSql dao;

    @GetMapping("/findAll")
    public ResponseEntity<List<Ruolo>> getRuoli() throws NotFoundException {
        List<Ruolo> ruoli = dao.findAllRuoli();
        if(ruoli == null){
            String ErrMsg = "La lista ruoli è vuota";
            throw new NotFoundException(ErrMsg);
        }
        return new ResponseEntity<List<Ruolo>>(ruoli, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Ruolo> getOneRuolo(@PathVariable int id) throws NotFoundException {
    	Ruolo ruoli = dao.findRuoloById(id);
    	if(ruoli == null){
            String ErrMsg = "Non è stato trovato alcun ruolo con l'id selezionato";
            throw new NotFoundException(ErrMsg);
        }
        return new ResponseEntity<Ruolo>(ruoli, HttpStatus.OK);
    }

    @PostMapping("/addRuolo")
    public ResponseEntity<?> addRuolo(@RequestBody Ruolo ruolo) throws BindingException {
        Map<String, String> validationMap = ruolo.isValidForStorage();
        if(!validationMap.get("status").equals("success") || !dao.addRuolo(ruolo))
            throw new BindingException("Binding Error, check the correct data form, detail: " + validationMap.get("message"));
        Map<String, String> responseMap = Map.of(
                "code", HttpStatus.OK.toString(),
                "message", "Ruolo " + ruolo.getMansione() + " aggiunto con successo");
        return responseToClient(responseMap);
    }

    @DeleteMapping("/deleteRuolo/{id}")
    public ResponseEntity<?> deleteRuolo(@PathVariable int id) throws NotFoundException {
        Ruolo ruolo = dao.findRuoloById(id);
        if(ruolo == null) {
            String MsgErr = String.format("Ruolo con id: %d non presente! ", id);
            throw new NotFoundException(MsgErr);
        }
        if(dao.deleteRuolo(id)) {
            Map<String, String> responseMap = Map.of(
                    "code", HttpStatus.OK.toString(),
                    "message", "Ruolo " + ruolo.getId() + " eliminato con successo");
            return responseToClient(responseMap);
        }
        else{
            Map<String, String> responseMap = Map.of(
                    "code", HttpStatus.NOT_MODIFIED.toString(),
                    "message", "Ruolo " + ruolo.getId() + " eliminazione fallita.");
            return responseToClient(responseMap);
        }
    }

    @PutMapping("/updateRuolo")
    public ResponseEntity<?> updateRuolo(@RequestBody Ruolo ruolo) throws NotFoundException, BindingException {
        Ruolo checkRuolo = dao.findRuoloById(ruolo.getId());
        if(checkRuolo == null){
            String MsgErr = String.format("Ruolo con id: %d non presente, niente da modificare! ", ruolo.getId());
            throw new NotFoundException(MsgErr);
        }
        Map<String, String> validationMap = ruolo.isValidForStorage();
        if(!validationMap.get("status").equals("success") || !dao.updateRuolo(ruolo))
            throw new BindingException("Binding Error, check the correct data form, detail: " + validationMap.get("message"));
        Map<String, String> responseMap = Map.of(
                "code", HttpStatus.OK.toString(),
                "message", "Ruolo con id:" + ruolo.getId() + " modificato con successo");
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