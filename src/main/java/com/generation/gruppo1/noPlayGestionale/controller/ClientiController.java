package com.generation.gruppo1.noPlayGestionale.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.generation.gruppo1.noPlayGestionale.dao.IDaoSql;
import com.generation.gruppo1.noPlayGestionale.exception.BindingException;
import com.generation.gruppo1.noPlayGestionale.exception.NotFoundException;
import com.generation.gruppo1.noPlayGestionale.model.Clienti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/findById/{id}")
    public ResponseEntity<Clienti> getOneClienti(@PathVariable int id) throws NotFoundException {
        Clienti clienti = dao.findClienteById(id);
        if(clienti == null){
            String ErrMsg = "Non è stato trovato alcun cliente con l'id selezionato";
            throw new NotFoundException(ErrMsg);
        }
        return new ResponseEntity<Clienti>(clienti, HttpStatus.OK);
    }

    @PostMapping("/addCliente")
    public ResponseEntity<?> addCliente(@RequestBody Clienti cliente) throws BindingException {
        Map<String, String> validationMap = cliente.isValidForStorage();
        if(!validationMap.get("status").equals("success") || !dao.addCliente(cliente))
            throw new BindingException("Binding Error, check the correct data form, detail: " + validationMap.get("message"));
        Map<String, String> responseMap = Map.of(
                "code", HttpStatus.OK.toString(),
                "message", "Cliente " + cliente.getRagionesociale() + " aggiunto con successo");
        return responseToClient(responseMap);
    }

    @DeleteMapping("/deleteCliente/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable int id) throws Exception {
        Clienti cliente = dao.findClienteById(id);
        if(cliente == null) {
            String MsgErr = String.format("Cliente con id: %d non presente! ", id);
            throw new NotFoundException(MsgErr);
        }
        if(dao.deleteCliente(id)){
        Map<String, String> responseMap = Map.of(
                "code", HttpStatus.OK.toString(),
                "message", "Cliente " + cliente.getRagionesociale() + " eliminato con successo");
        return responseToClient(responseMap);
        }
        else{
            Map<String, String> responseMap = Map.of(
                    "code", HttpStatus.NOT_MODIFIED.toString(),
                    "message", "Cliente " + cliente.getId() + " eliminazione fallita.");
            return responseToClient(responseMap);
        }
    }

    @PutMapping("updateCliente")
    public ResponseEntity<?> updateCliente(@RequestBody Clienti cliente) throws NotFoundException, BindingException {
        Clienti checkCliente = dao.findClienteById(cliente.getId());
        if(checkCliente == null) {
            String MsgErr = String.format("Cliente con id: %d non presente, niente da modificare! ", cliente.getId());
            throw new NotFoundException(MsgErr);
        }
        Map<String, String> validationMap = cliente.isValidForStorage();
        if(!validationMap.get("status").equals("success") || !dao.updateCliente(cliente))
            throw new BindingException("Binding Error, check the correct data form, detail: " + validationMap.get("message"));
        Map<String, String> responseMap = Map.of(
                "code", HttpStatus.OK.toString(),
                "message", "Cliente " + cliente.getRagionesociale() + " modificato con successo");
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
