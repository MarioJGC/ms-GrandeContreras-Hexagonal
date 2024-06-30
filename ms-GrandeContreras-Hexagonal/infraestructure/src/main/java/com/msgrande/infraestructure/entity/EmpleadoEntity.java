package com.msgrande.infraestructure.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "empleado")
@Getter
@Setter
public class EmpleadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombres", nullable = false, length = 20)
    private String nombres;
    @Column(name = "apellido_paterno", nullable = false, length = 20)
    private String apellidoPaterno;
    @Column(name = "apellido_materno", nullable = false, length = 20)
    private String apellidoMaterno;
    @Column(name = "edad", nullable = true, length = 3)
    private int edad;
    @Column(name = "cargo", nullable = true, length = 20)
    private String cargo;
    @Column(name = "tipo_documento", nullable = false, length = 1)
    private String tipoDoc;
    @Column(name = "numero_documento", nullable = false, length = 8, unique = true)
    private String numDoc;
    @Column(name = "departamento", nullable = true, length = 50)
    private String departamento;
    @Column(name = "salario", nullable = true)
    private double salario;
    @Column(name = "telefono", nullable = true, length = 15)
    private String telefono;
    @Column(name = "correo", nullable = true, length = 50)
    private String correo;
    @Column(name = "direccion", nullable = true, length = 100)
    private String direccion;
    @Column(name = "estado", nullable = false)
    private Integer estado;
    @Column(name = "usua_crea", length = 45)
    private String usuaCrea;
    @Column(name = "date_create")
    private Timestamp dateCreate;
    @Column(name = "usua_modif", length = 45)
    private String usuaUpdate;
    @Column(name = "date_modif")
    private Timestamp dateUdpate;
    @Column(name = "usua_delet", length = 45)
    private String usuaDelete;
    @Column(name = "date_delet")
    private Timestamp dateDelete;
}
