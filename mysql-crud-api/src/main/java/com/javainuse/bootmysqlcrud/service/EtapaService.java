package com.javainuse.bootmysqlcrud.service;

import com.javainuse.bootmysqlcrud.dto.EtapaDto;

import java.util.List;

public interface EtapaService {
    List<EtapaDto> listar();

    Integer registrar(EtapaDto paramEtapaDto);

    Integer editar(EtapaDto paramEtapaDto);

    Integer eliminar(Integer paramInteger);
}
