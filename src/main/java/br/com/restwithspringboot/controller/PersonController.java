package br.com.restwithspringboot.controller;

import br.com.restwithspringboot.controller.request.PersonData;
import br.com.restwithspringboot.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/v1/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonData> findById(@PathVariable("id") Long id){

        var response = service.findPersonById(id);

        return status(OK).body(response);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Iterable<PersonData>> findAll(){

        var response = service.findAllPerson();

        return status(OK).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PersonData request) {

        service.create(request);

        return status(CREATED).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PersonData> update(@PathVariable(value = "id") Long id, @RequestBody PersonData request) {

        var response = service.update(id,request);

        return status(CREATED).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        service.delete(id);

        return status(NO_CONTENT).build();
    }
}