package com.javainuse.bootmysqlcrud.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PermisoDto {
    private Integer IdPermisos;

    private String Menu;

    private String SubMenu;

    private Integer Activo;
}
