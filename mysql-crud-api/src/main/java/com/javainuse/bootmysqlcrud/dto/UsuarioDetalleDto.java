package com.javainuse.bootmysqlcrud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UsuarioDetalleDto {
    @JsonProperty("IdUsuario")
    private Integer idUsuario;

    @JsonProperty("LoginUsuario")
    private String loginUsuario;

    @JsonProperty("LoginClave")
    private String loginClave;

    @JsonProperty("Nombres")
    private String nombres;

    @JsonProperty("Apellidos")
    private String apellidos;

    @JsonProperty("DescripcionReferencia")
    private String descripcionReferencia;

    @JsonProperty("Activo")
    private Integer activo;

    @JsonProperty("Rol")
    private RolDto rol;

    @JsonProperty("Menus")
    private List<MenuDto> menus;
}
