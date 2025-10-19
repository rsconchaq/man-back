package com.javainuse.bootmysqlcrud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubMenuDto {
    @JsonProperty("id")
    private String idSubMenu;

    @JsonProperty("parentId")
    private String idMenu;

    @JsonProperty("label")
    private String nombreSubMenu;

    @JsonProperty("link")
    private String nombreFormulario;

    @JsonProperty("accion")
    private String accion;

    @JsonProperty("Activo")
    private Integer activo;
}
