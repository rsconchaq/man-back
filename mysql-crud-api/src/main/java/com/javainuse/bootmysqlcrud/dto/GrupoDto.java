package com.javainuse.bootmysqlcrud.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GrupoDto {
    private Integer idGrupo;

    private String descripcionGrupo;

    private String descripcion;

    private Integer activo;
    private Integer Edad1;
    private Integer Edad2;

    private Integer asignado;

    private String usuarioRegistro;

    private Date fechaRegistro;

    private String usuarioActualizacion;

    private Date fechaActualizacion;

    private TallerDto listaTaller;

    private Integer idEtapa;
}
