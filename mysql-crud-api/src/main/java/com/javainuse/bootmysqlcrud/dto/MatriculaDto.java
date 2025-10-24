package com.javainuse.bootmysqlcrud.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MatriculaDto {
    private Integer idMatricula;

    private String codigo;

    private String situacion;

    private Integer idAlumno;

    private Integer idAperturaTaller;

    private Integer idApoderado;

    private Integer tipo;
    private Integer pago;

    private Integer activo;
}
