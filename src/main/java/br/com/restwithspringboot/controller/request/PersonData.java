package br.com.restwithspringboot.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonData {

    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    private String gender;
}
