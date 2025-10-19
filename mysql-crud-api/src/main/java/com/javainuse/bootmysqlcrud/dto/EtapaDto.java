package com.javainuse.bootmysqlcrud.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class EtapaDto {
    private Integer idEtapa;

    private LocalDto local;

    private String descripcionEtapa;

    private String descripcion;

    private Integer activo;

    private String usuarioRegistro;

    private Date fechaRegistro;

    private String usuarioActualizacion;

    private Date fechaActualizacion;
}
