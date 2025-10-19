package com.javainuse.bootmysqlcrud.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DocenteDto {
    private Integer idDocente;

    private String codigo;

    private String documentoIdentidad;

    private String nombres;

    private String apellidos;

    private Date fechaNacimiento;

    private String sexo;

    private String gradoEstudio;

    private String ciudad;

    private String direccion;

    private String email;

    private String numeroTelefono;

    private Integer activo;

    private Date fechaRegistro;
}
