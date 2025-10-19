package com.javainuse.bootmysqlcrud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MenuDto {
    @JsonProperty("id")
    private Integer idMenu;

    @JsonProperty("label")
    private String nombreMenu;

    @JsonProperty("icon")
    private String icono;

    @JsonProperty("subItems")
    private List<SubMenuDto> subMenus;

    @JsonProperty("Activo")
    private Character activo;
}
