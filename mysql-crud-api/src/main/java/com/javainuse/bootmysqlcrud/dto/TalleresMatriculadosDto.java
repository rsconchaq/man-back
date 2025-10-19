package com.javainuse.bootmysqlcrud.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class TalleresMatriculadosDto {
    private Integer IdEtapa;

    private Integer IdGrupo;

    private Integer IdAlumno;

    private Integer IdMatricula;

    private String DescripcionLocal;

    private String DescripcionEtapa;

    private String DescripcionGrupo;

    private String NombresAlumno;

    private String ApellidosAlumno;

    private String Descripcion;

    private Date FechaInicio;

    private Date FechaFin;
}
