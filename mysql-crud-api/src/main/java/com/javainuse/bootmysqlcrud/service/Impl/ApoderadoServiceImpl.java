package com.javainuse.bootmysqlcrud.service.Impl;

import com.javainuse.bootmysqlcrud.dto.ApoderadoDto;
import com.javainuse.bootmysqlcrud.repository.ApoderadoRepository;
import com.javainuse.bootmysqlcrud.service.ApoderadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApoderadoServiceImpl implements ApoderadoService {
    @Autowired
    private ApoderadoRepository apoderadoRepository;

    public List<ApoderadoDto> listar(Integer idAlumno) {
        return this.apoderadoRepository.listar(idAlumno);
    }

    public Integer registrar(ApoderadoDto param, String user) {
        return this.apoderadoRepository.registrar(param, user);
    }

    public Integer editar(ApoderadoDto param, String user) {
        return this.apoderadoRepository.editar(param, user);
    }

    public Integer eliminar(Integer idAlumno) {
        return this.apoderadoRepository.eliminar(idAlumno);
    }
}
