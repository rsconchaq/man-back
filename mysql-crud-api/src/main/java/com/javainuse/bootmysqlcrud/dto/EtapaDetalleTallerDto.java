package com.javainuse.bootmysqlcrud.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EtapaDetalleTallerDto {
    private Integer idEtapaDetalleTaller;

    private EtapaDetalleDto etapaDetalle;

    private EtapaDto oEtapa;

    private GrupoDto oGrupo;

    private TallerDto oTaller;
}
