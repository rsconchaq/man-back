package com.javainuse.bootmysqlcrud.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SeguimientoSesionDto {
    private int idMatricula;

    private int idSeguimientoSesion;

    private String asistencia;

    private String observacion;

    private Date fechaRegistro;

    private int idAperturaTallerDet;

    private String descripcionSesion;

    private Date fechaInicio;

    private Date fechaFin;

    private String taller;

    private String descripcionGrupo;

    private String descripcionEtapa;

    private int idAlumno;

    private String nombres;

    private String apellidos;

    private int seguimiento1;

    private int seguimiento2;

    private int seguimiento3;

    private int seguimiento4;

    private int seguimiento5;

    private int seguimiento6;

    private int seguimiento7;

    private int seguimiento8;

    private int seguimiento9;

    private int seguimiento10;
}
