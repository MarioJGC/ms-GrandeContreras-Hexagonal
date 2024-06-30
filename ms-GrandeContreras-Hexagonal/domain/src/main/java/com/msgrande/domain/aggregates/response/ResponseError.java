package com.msgrande.domain.aggregates.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ResponseError {
    private String message;
    private String error;
    private int status;
    private Date date;
}
