package com.javainuse.bootmysqlcrud.service;

import com.javainuse.bootmysqlcrud.dto.DocenteDto;

import java.util.List;

public interface DocenteService {
    List<DocenteDto> listar();

    Integer registrar(DocenteDto paramDocenteDto, String paramString);

    Integer editar(DocenteDto paramDocenteDto, String paramString);

    Integer eliminar(Integer paramInteger);
}
