package com.javainuse.bootmysqlcrud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class UsuarioDto {
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

    @JsonProperty("IdReferencia")
    private Integer idReferencia;

    @JsonProperty("Activo")
    private Integer activo;

    @JsonProperty("IdRol")
    private Integer idRol;

    @JsonProperty("Rol")
    private RolDto rol;

    @JsonProperty("Menus")
    private List<MenuDto> menus;
}
