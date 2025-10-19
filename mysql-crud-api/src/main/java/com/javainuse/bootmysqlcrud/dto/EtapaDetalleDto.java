package com.javainuse.bootmysqlcrud.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EtapaDetalleDto {
    private Integer idEtapaDetalle;

    private EtapaDto etapa;

    private GrupoDto grupo;

    private Integer activo;
}
