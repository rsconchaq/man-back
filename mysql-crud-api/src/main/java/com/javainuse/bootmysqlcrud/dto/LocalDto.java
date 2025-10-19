package com.javainuse.bootmysqlcrud.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LocalDto {
    private Integer idLocal;

    private String descripcion;

    private String direccion;

    private Integer activo;
}
