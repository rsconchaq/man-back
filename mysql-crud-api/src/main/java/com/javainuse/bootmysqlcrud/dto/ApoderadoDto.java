package com.javainuse.bootmysqlcrud.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ApoderadoDto {
    private Integer idApoderado;

    private String TipoRelacion;

    private String Nombres;

    private String Apellidos;

    private String DocumentoIdentidad;

    private Date FechaNacimiento;

    private String Sexo;

    private String Telefono1;

    private String Telefono2;

    private String Email;

    private String EstadoCivil;

    private String Ciudad;

    private String Direccion;

    private Integer Activo;

    private Date FechaRegistro;

    private Integer IdAlumno;
}
