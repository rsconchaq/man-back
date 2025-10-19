package com.javainuse.bootmysqlcrud.service;

import com.javainuse.bootmysqlcrud.dto.AulaDto;

import java.util.List;

public interface AulaService {
    List<AulaDto> listar();

    Integer registrar(AulaDto paramAulaDto);

    Integer editar(AulaDto paramAulaDto);

    Integer eliminar(Integer paramInteger);
}
