package com.javainuse.bootmysqlcrud.service.Impl;

import com.javainuse.bootmysqlcrud.dto.EtapaDetalleDto;
import com.javainuse.bootmysqlcrud.dto.GrupoDto;
import com.javainuse.bootmysqlcrud.repository.EtapaGrupoDetalleRepository;
import com.javainuse.bootmysqlcrud.service.EtapaGrupoDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtapaGrupoDetalleServiceImpl implements EtapaGrupoDetalleService {
    @Autowired
    private EtapaGrupoDetalleRepository etapaGrupoDetalleRepository;

    public List<EtapaDetalleDto> listar() {
        return this.etapaGrupoDetalleRepository.listar();
    }

    public Integer registrar(List<GrupoDto> param) {
        return this.etapaGrupoDetalleRepository.registrar(param);
    }
}
