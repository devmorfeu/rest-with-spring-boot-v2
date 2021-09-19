package br.com.restwithspringboot.controller;

import br.com.restwithspringboot.repository.PersonRepository;
import br.com.restwithspringboot.service.entity.Person;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.*;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureTestDatabase
@SpringBootTest(
        webEnvironment = RANDOM_PORT,
        properties = {"isTest=true"}
)
public class PersonControllerTest {

    @Autowired
    private PersonRepository repository;

    @LocalServerPort
    protected int port;

    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        this.repository.save(createPerson());
    }

    private Person createPerson() {
        return new Person(1L, "RONALDO", "FENOMENO", "BRASIL", "M");
    }

    @Test
    public void when_method_get_then_return_person() {

        when().request("GET", "v1/person/1")
                .then().log().all().statusCode(OK.value())
                    .body(is(not(nullValue())))
                    .body(containsString("RONALDO"))
                    .body(containsString("FENOMENO"))
                    .body(containsString("M"))
                    .body(containsString("BRASIL"));
    }

    @Test
    public void when_method_get_then_return_id_not_found() {

        when().request("GET", "v1/person/15")
                .then().statusCode(404);
    }

    @Test
    public void when_method_delete_then_return_person_deleted() {

        repository.save(new Person(2L, "RONALDO", "FENOMENO", "BRASIL", "M"));

        when().delete("v1/person/delete/{id}", 2L)
                .then().log().all().statusCode(NO_CONTENT.value());
    }

    @Test
    public void when_method_get_then_return_all_person() {

        when().request("GET", "v1/person/all")
                .then().log().all().statusCode(OK.value()).body(is(not(nullValue())));
    }

    @Test
    public void when_method_post_then_return_person_created() {
        var payloadJson = new File("src/test/resources/payload/PersonCreate.json");

        given().contentType(JSON).body(payloadJson)
                .when().request("POST", "v1/person/create")
                    .then().log().all().statusCode(CREATED.value());
    }

    @Test
    public void when_method_put_then_return_person_updated() {

        var payloadJson = new File("src/test/resources/payload/PersonUpdate.json");

        given().contentType(JSON).body(payloadJson)
                .when().request("PUT", "v1/person/update/{id}", 1L)
                    .then().log().all().statusCode(CREATED.value());
    }

}

