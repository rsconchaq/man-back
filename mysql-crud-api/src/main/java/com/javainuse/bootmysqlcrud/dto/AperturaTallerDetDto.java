package com.javainuse.bootmysqlcrud.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AperturaTallerDetDto {
    private Integer idAperturaTallerDet;

    private Integer idAperturaTaller;

    private String descripcion;

    private String fechaInicio;

    private String fechaFin;
}
