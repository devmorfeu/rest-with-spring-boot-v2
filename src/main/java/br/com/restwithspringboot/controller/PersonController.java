package br.com.restwithspringboot.controller;

import br.com.restwithspringboot.controller.request.PersonData;
import br.com.restwithspringboot.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/v1/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService service;

    @GetMapping(value = "/{personId}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<PersonData> findById(@PathVariable("personId") Long personId){

        var response = service.findPersonById(personId);

        response.add(linkTo(methodOn(PersonController.class).findById(personId)).withSelfRel());

        return status(OK).body(response);
    }

    @GetMapping(value = "/all", produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<Iterable<PersonData>> findAll(){

        var response = service.findAllPerson();

        response.forEach(p -> p
                .add(linkTo(methodOn(PersonController.class)
                .findById(p.getPersonId()))
                .withSelfRel()));

        return status(OK).body(response);
    }

    @PostMapping(value = "/create", produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<?> create(@RequestBody PersonData request) {

        service.create(request);

        return status(CREATED).build();
    }

    @PutMapping(value = "/update/{id}", produces = {"application/json", "application/xml", "application/x-yaml"}, consumes = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<PersonData> update(@PathVariable(value = "id") Long id, @RequestBody PersonData request) {

        var response = service.update(id,request);

        response.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

        return status(CREATED).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        service.delete(id);

        return status(NO_CONTENT).build();
    }
}