package br.com.restwithspringboot.service.mapper;

import br.com.restwithspringboot.controller.request.PersonData;
import br.com.restwithspringboot.service.entity.Person;
import java.util.ArrayList;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-13T21:32:42-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Amazon.com Inc.)"
)
@Component
public class PersonMapperImpl implements PersonMapper {

    @Override
    public Person requestToEntity(PersonData request) {
        if ( request == null ) {
            return null;
        }

        Long id = null;
        String firstName = null;
        String lastName = null;
        String address = null;
        String gender = null;

        id = request.getId();
        firstName = request.getFirstName();
        lastName = request.getLastName();
        address = request.getAddress();
        gender = request.getGender();

        Person person = new Person( id, firstName, lastName, address, gender );

        return person;
    }

    @Override
    public PersonData entityToResponse(Person response) {
        if ( response == null ) {
            return null;
        }

        PersonData personData = new PersonData();

        personData.setId( response.getId() );
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

        String firstName = null;
        String lastName = null;
        String address = null;
        String gender = null;
        if ( request != null ) {
            firstName = request.getFirstName();
            lastName = request.getLastName();
            address = request.getAddress();
            gender = request.getGender();
        }
        Long id1 = null;
        if ( id != null ) {
            id1 = id;
        }

        Person person = new Person( id1, firstName, lastName, address, gender );

        return person;
    }
}
