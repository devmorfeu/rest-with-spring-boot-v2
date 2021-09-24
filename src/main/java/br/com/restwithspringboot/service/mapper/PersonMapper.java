package br.com.restwithspringboot.service.mapper;

import br.com.restwithspringboot.controller.request.PersonData;
import br.com.restwithspringboot.service.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person requestToEntity(PersonData request);

    @Mapping(target = "personId",   source = "id")
    PersonData entityToResponse(Person response);

    Iterable<PersonData> listEntityToListResponse(Iterable<Person> list);

    @Mapping(target = "id",         source = "id")
    @Mapping(target = "firstName",  source = "request.firstName")
    @Mapping(target = "lastName",   source = "request.lastName")
    @Mapping(target = "address",    source = "request.address")
    @Mapping(target = "gender",     source = "request.gender")
    Person updatePerson(PersonData request, Long id);
}
