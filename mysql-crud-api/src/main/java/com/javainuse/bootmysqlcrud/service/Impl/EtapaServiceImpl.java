package com.javainuse.bootmysqlcrud.service.Impl;

import com.javainuse.bootmysqlcrud.dto.EtapaDto;
import com.javainuse.bootmysqlcrud.repository.EtapaRpository;
import com.javainuse.bootmysqlcrud.service.EtapaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtapaServiceImpl implements EtapaService {
    @Autowired
    private EtapaRpository etapaRepository;

    public List<EtapaDto> listar() {
        return this.etapaRepository.listar();
    }

    public Integer registrar(EtapaDto param) {
        return this.etapaRepository.registrar(param);
    }

    public Integer editar(EtapaDto param) {
        return this.etapaRepository.editar(param);
    }

    public Integer eliminar(Integer idEtapa) {
        return this.etapaRepository.eliminar(idEtapa);
    }
}
