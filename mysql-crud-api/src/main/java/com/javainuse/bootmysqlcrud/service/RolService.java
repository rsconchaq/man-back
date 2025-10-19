package com.javainuse.bootmysqlcrud.service;

import com.javainuse.bootmysqlcrud.dto.RolDto;

import java.util.List;

public interface RolService {
    List<RolDto> listar();

    Integer registrar(RolDto paramRolDto);

    Integer editar(RolDto paramRolDto);

    Integer eliminar(Integer paramInteger);
}
