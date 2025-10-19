package com.javainuse.bootmysqlcrud.service.Impl;

import com.javainuse.bootmysqlcrud.dto.AulaDto;
import com.javainuse.bootmysqlcrud.repository.AulaRepository;
import com.javainuse.bootmysqlcrud.service.AulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AulaServiceImpl implements AulaService {
    @Autowired
    private AulaRepository aulaRepository;

    public List<AulaDto> listar() {
        return this.aulaRepository.listar();
    }

    public Integer registrar(AulaDto param) {
        return this.aulaRepository.registrar(param);
    }

    public Integer editar(AulaDto param) {
        return this.aulaRepository.editar(param);
    }

    public Integer eliminar(Integer idAula) {
        return this.aulaRepository.eliminar(idAula);
    }
}
