package br.com.restwithspringboot.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseError {

    private String message;

    private String code;

    private String details;
}