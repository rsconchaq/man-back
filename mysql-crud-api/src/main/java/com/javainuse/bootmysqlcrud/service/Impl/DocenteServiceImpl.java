package com.javainuse.bootmysqlcrud.service.Impl;

import com.javainuse.bootmysqlcrud.dto.DocenteDto;
import com.javainuse.bootmysqlcrud.repository.DocenteRepository;
import com.javainuse.bootmysqlcrud.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocenteServiceImpl implements DocenteService {
    @Autowired
    private DocenteRepository docenteRepository;

    public List<DocenteDto> listar() {
        return this.docenteRepository.listar();
    }

    public Integer registrar(DocenteDto param, String user) {
        return this.docenteRepository.registrar(param, user);
    }

    public Integer editar(DocenteDto param, String user) {
        return this.docenteRepository.editar(param, user);
    }

    public Integer eliminar(Integer idDocente) {
        return this.docenteRepository.eliminar(idDocente);
    }
}
