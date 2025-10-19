package com.javainuse.bootmysqlcrud.service;

import com.javainuse.bootmysqlcrud.dto.EtapaDetalleDto;
import com.javainuse.bootmysqlcrud.dto.GrupoDto;

import java.util.List;

public interface EtapaGrupoDetalleService {
    List<EtapaDetalleDto> listar();

    Integer registrar(List<GrupoDto> paramList);
}
