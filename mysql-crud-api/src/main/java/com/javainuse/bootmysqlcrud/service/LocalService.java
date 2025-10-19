package com.javainuse.bootmysqlcrud.service;

import com.javainuse.bootmysqlcrud.dto.LocalDto;

import java.util.List;

public interface LocalService {
    List<LocalDto> listar();

    Integer registrar(LocalDto paramLocalDto);

    Integer editar(LocalDto paramLocalDto);

    Integer eliminar(Integer paramInteger);
}
