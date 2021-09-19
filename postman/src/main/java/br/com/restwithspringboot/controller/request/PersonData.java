package br.com.restwithspringboot.controller.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class PersonData extends RepresentationModel<PersonData> {

    private Long personId;

    private String firstName;

    private String lastName;

    private String address;

    private String gender;
}
