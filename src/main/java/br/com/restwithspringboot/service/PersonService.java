package br.com.restwithspringboot.service;

import br.com.restwithspringboot.controller.request.PersonData;
import br.com.restwithspringboot.repository.PersonRepository;
import br.com.restwithspringboot.service.mapper.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository repository;

    private final PersonMapper mapper;

    public PersonData findPersonById(Long id) {

        var response = repository.findById(id).orElseThrow(() -> new RuntimeException("NOT FOUND ID"));

        return mapper.entityToResponse(response);
    }

    public Iterable<PersonData> findAllPerson() {

        var list = repository.findAll();

        return mapper.listEntityToListResponse(list);
    }

    public void create(PersonData request) {

        repository.save(mapper.requestToEntity(request));
    }

    public PersonData update(Long id, PersonData request) {

        repository.findById(id).orElseThrow(() -> new RuntimeException("NOT FOUND ID"));

        var person = mapper.updatePerson(request, id);

        repository.save(person);

        return mapper.entityToResponse(person);
    }

    public void delete(Long request) {
        repository.deleteById(request);
    }
}