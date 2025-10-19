package com.javainuse.bootmysqlcrud.service;

import com.javainuse.bootmysqlcrud.dto.ApoderadoDto;

import java.util.List;

public interface ApoderadoService {
    List<ApoderadoDto> listar(Integer paramInteger);

    Integer registrar(ApoderadoDto paramApoderadoDto, String paramString);

    Integer editar(ApoderadoDto paramApoderadoDto, String paramString);

    Integer eliminar(Integer paramInteger);
}
