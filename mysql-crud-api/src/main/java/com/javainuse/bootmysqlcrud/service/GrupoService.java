package com.javainuse.bootmysqlcrud.service;

import com.javainuse.bootmysqlcrud.dto.GrupoDto;

import java.util.List;

public interface GrupoService {
    List<GrupoDto> listar();

    Integer registrar(GrupoDto paramGrupoDto);

    Integer editar(GrupoDto paramGrupoDto);

    Integer eliminar(Integer paramInteger);
}
