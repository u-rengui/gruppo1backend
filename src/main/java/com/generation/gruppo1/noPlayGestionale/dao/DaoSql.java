package com.generation.gruppo1.noPlayGestionale.dao;
import com.generation.gruppo1.noPlayGestionale.model.Clienti;
import com.generation.gruppo1.noPlayGestionale.model.Personale;
import com.generation.gruppo1.noPlayGestionale.model.Ruolo;
import com.generation.gruppo1.noPlayGestionale.util.BasicDao;
import com.generation.gruppo1.noPlayGestionale.util.IMappablePro;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class DaoSql extends BasicDao implements IDaoSql{

    private static final String UPDATE_RUOLI = "UPDATE ruoli SET mansione = ? WHERE id = ?";
    private static final String DELETE_FROM_RUOLI = "DELETE FROM ruoli WHERE id = ?";
    private static final String INSERT_INTO_RUOLI = "INSERT INTO ruoli (mansione) VALUES (?)";
    private static final String RUOLI_WHERE_ID = "SELECT * FROM ruoli WHERE id = ?";
    private static final String RUOLI = "SELECT * FROM ruoli";
    private static final String UPDATE_PERSONALE_WHERE_ID = "UPDATE personale SET nome = ?, cognome = ?, ddn = ?, stipendio = ?, dataassunzione = ?, idruolo = ? WHERE id = ?";
    private static final String DELETE_FROM_PERSONALE = "DELETE FROM personale WHERE id = ?";
    private static final String INSERT_INTO_PERSONALE = "INSERT INTO personale (nome,cognome,ddn,stipendio,dataassunzione,idruolo) VALUES (?,?,?,?,?,?)";
    private static final String CLIENTI_SEGUITI_DA_PERSONALE = "SELECT * FROM consulente_per INNER JOIN clienti ON idcliente = clienti.id WHERE idpersonale = ?";
    private static final String PERSONALE_WHERE_ID = "SELECT * FROM personale WHERE id = ?";
    private static final String PERSONALE = "SELECT * FROM personale";
    private static final String UPDATE_CLIENTI = "UPDATE clienti SET ragionesociale = ?, partitaiva = ?, indirizzo = ?, email = ?, ntel = ? WHERE id = ?";
    private static final String DELETE_FROM_CLIENTI = "DELETE FROM clienti WHERE id = ?";
    private static final String INSERT_INTO_CLIENTI = "INSERT INTO clienti (ragionesociale,partitaiva,indirizzo,email,ntel) VALUES (?,?,?,?,?)";
    private static final String PERSONALE_CONSULENTE_PER_CLIENTE = "SELECT * FROM consulente_per INNER JOIN personale ON idpersonale = personale.id WHERE idcliente = ?";
    private static final String CLIENTI_WHERE_ID = "SELECT * FROM clienti WHERE id = ?";
    private static final String CLIENTI = "SELECT * FROM clienti";

    public DaoSql(@Value("${db.address}") String dbAddress,
                  @Value("${db.user}") String user,
                  @Value("${db.psw}") String password ) {
        super(dbAddress, user, password);
    }
// *** CLIENTE DAO
    @Override
    public List<Clienti> findAllClienti() {
        List<Clienti> ris = new ArrayList<>();
        List<Map<String, String>> maps = getAll(CLIENTI);
        for (Map<String, String> map : maps) {
            ris.add(IMappablePro.fromMap(Clienti.class, map));
        }
        return ris;
    }
    @Override
    public Clienti findClienteById(int id) {
        Clienti ris = null;
        Map<String, String> map = getOne(CLIENTI_WHERE_ID, id);
        if(map != null) {
            ris = IMappablePro.fromMap(Clienti.class, map);
            ris.setPersonale(personale(id));
        }
        return ris;
    }
    @Override
    public boolean addCliente(Clienti cliente) {
        return execute(INSERT_INTO_CLIENTI, cliente.getRagionesociale(), cliente.getPartitaiva(),
                cliente.getIndirizzo(), cliente.getEmail(), cliente.getNtel());
    }
    @Override
    public boolean deleteCliente(int id) {
        return execute(DELETE_FROM_CLIENTI, id);
    }
    @Override
    public boolean updateCliente(Clienti cliente) {
        return execute(UPDATE_CLIENTI,
                cliente.getRagionesociale(), cliente.getPartitaiva(),
                cliente.getIndirizzo(), cliente.getEmail(), cliente.getNtel(),
                cliente.getId());
    }
// *** PERSONALE DAO
    /*METODO PER AVERE LA LISTA DEL PERSONALE ASSEGNATO A QUEL CLIENTE*/
    private List<Personale> personale(int idCliente){
        List<Personale> ris = new ArrayList<>();
        List<Map<String, String>> maps = getAll(PERSONALE_CONSULENTE_PER_CLIENTE, idCliente);
        for (Map<String, String> map : maps) {
            Personale p = new Personale();
            p.fromMap(map);
            p.setRuolo(findRuoloById(p.getId()));
            ris.add(p);
        }
        return ris;
    }
    @Override
    public List<Personale> findAllPersonale() {
        List<Personale> ris = new ArrayList<>();
        List<Map<String, String>> maps = getAll(PERSONALE);
        for (Map<String, String> map : maps) {
            Personale p = new Personale();
            p.fromMap(map);
            p.setRuolo(findRuoloById(Integer.parseInt(map.get("idruolo"))));
            ris.add(p);
        }
        return ris;
    }
    @Override
    public Personale findPersonaleById(int id) {
        Personale ris = null;
        Map<String, String> map = getOne(PERSONALE_WHERE_ID, id);
        if(map != null) {
            ris = IMappablePro.fromMap(Personale.class, map);
            ris.setClienti(clienti(id));
            ris.setRuolo(findRuoloById(id));
        }
        return ris;
    }

    // METODO PER AVERE LA LISTA DEI CLIENTI DEL SINGOLO PERSONALE
    private List<Clienti> clienti(int idPersonale){
        List<Clienti> ris = new ArrayList<>();
        List<Map<String, String>> maps = getAll(CLIENTI_SEGUITI_DA_PERSONALE, idPersonale);
        for (Map<String, String> map : maps) {
            ris.add(IMappablePro.fromMap(Clienti.class, map));
        }
        return ris;
    }

    @Override
    public void addPersonale(Personale personale) {
        execute(INSERT_INTO_PERSONALE, personale.getNome(), personale.getCognome(), personale.getDdn(),
                personale.getStipendio(), personale.getDataassunzione(),
                personale.getRuolo().getId());
    }

    @Override
    public void deletePersonale(int id) {
        execute(DELETE_FROM_PERSONALE, id);
    }

    @Override
    public void updatePersonale(Personale personale) {
        execute(UPDATE_PERSONALE_WHERE_ID,
                personale.getNome(), personale.getCognome(), personale.getDdn(),
                personale.getStipendio(), personale.getDataassunzione(),
                personale.getRuolo().getId(), personale.getId());
    }

    @Override
    public List<Ruolo> findAllRuoli() {
        List<Ruolo> ris = new ArrayList<>();

        List<Map<String, String>> maps = getAll(RUOLI);

        for (Map<String, String> map : maps) {
            ris.add(IMappablePro.fromMap(Ruolo.class, map));
        }

        return ris;
    }

    @Override
    public Ruolo findRuoloById(int id) {
        Ruolo ris = null;

        Map<String, String> map = getOne(RUOLI_WHERE_ID, id);

        if(map != null) {
            ris = IMappablePro.fromMap(Ruolo.class, map);
        }

        return ris;
    }

    @Override
    public void addRuolo(Ruolo ruolo) {
        execute(INSERT_INTO_RUOLI, ruolo.getMansione());
    }

    @Override
    public void deleteRuolo(int id) {
        execute(DELETE_FROM_RUOLI, id);
    }

    @Override
    public void updateRuolo(Ruolo ruolo) {
        execute(UPDATE_RUOLI, ruolo.getMansione());
    }



}