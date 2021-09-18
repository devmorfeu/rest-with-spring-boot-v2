package br.com.restwithspringboot.controller;

import br.com.restwithspringboot.repository.PersonRepository;
import br.com.restwithspringboot.service.entity.Person;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase
@DataJpaTest
@ActiveProfiles("test")
@ContextConfiguration(classes = PersonRepository.class)
//@PropertyMapping("src/main/resource/application-test.properties")
public class PersonControllerTest {

    @Autowired
    private PersonRepository repository = null;

    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        repository.save(createPerson());
    }

    private Person createPerson() {
        return new Person(1L,"RONALDO","FENOMENO","BRASIL","M");
    }

    @Test
    public void when_method_get_then_return_http_status_ok(){

        when()
                .request("GET", "v1/person/1")
                .then()
                .log()
                .all()
                .statusCode(OK.value())
                .body(is(not(nullValue())))
                .body(containsString("RONALDO"))
                .body(containsString("FENOMENO"))
                .body(containsString("MACHO"))
                .body(containsString("VILA DOS MAMADORES DA DONA MARIA"));
    }
}
