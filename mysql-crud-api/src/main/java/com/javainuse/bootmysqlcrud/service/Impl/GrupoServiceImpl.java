package com.javainuse.bootmysqlcrud.service.Impl;

import com.javainuse.bootmysqlcrud.dto.GrupoDto;
import com.javainuse.bootmysqlcrud.repository.GrupoRepository;
import com.javainuse.bootmysqlcrud.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoServiceImpl implements GrupoService {
    @Autowired
    private GrupoRepository grupoRepository;

    public List<GrupoDto> listar() {
        return this.grupoRepository.listar();
    }

    public Integer registrar(GrupoDto param) {
        return this.grupoRepository.registrar(param);
    }

    public Integer editar(GrupoDto param) {
        return this.grupoRepository.editar(param);
    }

    public Integer eliminar(Integer idGrupo) {
        return this.grupoRepository.eliminar(idGrupo);
    }
}
