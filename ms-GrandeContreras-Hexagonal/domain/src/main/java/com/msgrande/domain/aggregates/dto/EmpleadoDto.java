package com.msgrande.domain.aggregates.dto;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@Getter
@Setter
public class EmpleadoDto {

    private Long id;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int edad;
    private String cargo;
    private String tipoDoc;
    private String numDoc;
    private String departamento;
    private double salario;
    private String telefono;
    private String correo;
    private boolean estado;
    private String direccion;
    private Timestamp dateCreate;
    private String usuaCrea;
    private Timestamp dateUdpate;
    private String usuaUpdate;
    private Timestamp dateDelete;
    private String usuaDelete;

}