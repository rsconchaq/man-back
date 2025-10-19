package com.javainuse.bootmysqlcrud.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class AulaDto {
    private Integer idAula;

    private LocalDto local;

    private String descripcionAula;

    private String descripcion;

    private Integer activo;

    private String usuarioRegistro;

    private Date fechaRegistro;
}
