package com.javainuse.bootmysqlcrud.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class AlumnoDto {
    private Integer idAlumno;

    private String codigo;

    private String nombres;

    private String apellidos;

    private String documentoIdentidad;

    private Date fechaNacimiento;

    private String sexo;

    private String ciudad;

    private String direccion;

    private Integer activo;

    private String telefono;

    private String email;

    private Integer edad;

    private String diagnostico;

    private String observaciones;

    private String usuarioRegistro;

    private Date fechaRegistro;

    private String usuarioModificacion;

    private Date fechaModificacion;

    private List<ApoderadoDto> apoderados;
}
