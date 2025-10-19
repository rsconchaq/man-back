package com.javainuse.bootmysqlcrud.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class TallerDto {
    private Integer idTaller;

    private String descripcion;
    private String descripcionCorta;

    private Integer activo;

    private Integer asignado;

    private String usuarioRegistro;

    private Date fechaRegistro;

    private String usuarioActualizacion;

    private Date fechaActualizacion;

    private Integer idEtapa;

    private Integer idGrupo;
}
