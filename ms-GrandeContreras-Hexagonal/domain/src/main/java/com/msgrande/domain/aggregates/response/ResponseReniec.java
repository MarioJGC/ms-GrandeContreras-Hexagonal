package com.msgrande.domain.aggregates.response;


import lombok.Getter;

@Getter
public class ResponseReniec {

    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String tipoDocumento;
    private String numeroDocumento;
    private String digitoVerificador;

}
