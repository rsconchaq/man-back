package com.javainuse.bootmysqlcrud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RolDto {
    @JsonProperty("IdRol")
    private Integer idRol;

    @JsonProperty("Activo")
    private Integer activo;

    @JsonProperty("Descripcion")
    private String descripcion;
}
