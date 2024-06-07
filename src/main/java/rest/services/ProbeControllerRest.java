package rest.services;

import rest.domain.Probe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.repository.DBProbe;

import java.util.List;
@RestController
@RequestMapping("/inscrieri/probe")

public class ProbeControllerRest {
    @Autowired
    private DBProbe probeRep;

    @RequestMapping(method = RequestMethod.GET)
    public List<Probe> findAll() {
        System.out.println("Get all Probe...");
        return (List<Probe>) probeRep.findAll();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable Long id) {
        System.out.println("Get by id " + id);
        Probe proba = probeRep.findOne(id).get();
        if (proba == null)
            return new ResponseEntity<>("Proba not found", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(proba, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Probe proba) {
        System.out.println("Creating probe ...");
        probeRep.save(proba);
        return new ResponseEntity<>(proba, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Probe proba) {
        System.out.println("Update probe...");
        probeRep.update(proba);
        return new ResponseEntity<>(proba, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        System.out.println("Deleting proba... " + id);
        probeRep.delete(id);
        return new ResponseEntity<Probe>(HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userError(Exception e) {
        return e.getMessage();
    }
}

