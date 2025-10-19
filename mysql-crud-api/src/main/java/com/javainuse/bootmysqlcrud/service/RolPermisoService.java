package com.javainuse.bootmysqlcrud.service;

import com.javainuse.bootmysqlcrud.dto.PermisoDto;

import java.util.List;

public interface RolPermisoService {
    List<PermisoDto> obtener(Integer paramInteger);

    Integer actualizar(List<PermisoDto> paramList);
}
