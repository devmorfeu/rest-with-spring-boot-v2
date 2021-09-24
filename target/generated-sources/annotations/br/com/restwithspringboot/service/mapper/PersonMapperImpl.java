package br.com.restwithspringboot.service.mapper;

import br.com.restwithspringboot.controller.request.PersonData;
import br.com.restwithspringboot.service.entity.Person;
import java.util.ArrayList;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-20T01:56:24-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Amazon.com Inc.)"
)
@Component
public class PersonMapperImpl implements PersonMapper {

    @Override
    public Person requestToEntity(PersonData request) {
        if ( request == null ) {
            return null;
        }

        Person person = new Person();

        person.setFirstName( request.getFirstName() );
        person.setLastName( request.getLastName() );
        person.setAddress( request.getAddress() );
        person.setGender( request.getGender() );

        return person;
    }

    @Override
    public PersonData entityToResponse(Person response) {
        if ( response == null ) {
            return null;
        }

        PersonData personData = new PersonData();

        personData.setPersonId( response.getId() );
        personData.setFirstName( response.getFirstName() );
        personData.setLastName( response.getLastName() );
        personData.setAddress( response.getAddress() );
        personData.setGender( response.getGender() );

        return personData;
    }

    @Override
    public Iterable<PersonData> listEntityToListResponse(Iterable<Person> list) {
        if ( list == null ) {
            return null;
        }

        ArrayList<PersonData> iterable = new ArrayList<PersonData>();
        for ( Person person : list ) {
            iterable.add( entityToResponse( person ) );
        }

        return iterable;
    }

    @Override
    public Person updatePerson(PersonData request, Long id) {
        if ( request == null && id == null ) {
            return null;
        }

        Person person = new Person();

        if ( request != null ) {
            person.setFirstName( request.getFirstName() );
            person.setLastName( request.getLastName() );
            person.setAddress( request.getAddress() );
            person.setGender( request.getGender() );
        }
        if ( id != null ) {
            person.setId( id );
        }

        return person;
    }
}
