package com.javainuse.bootmysqlcrud.service.Impl;

import com.javainuse.bootmysqlcrud.dto.RolDto;
import com.javainuse.bootmysqlcrud.repository.RolRepository;
import com.javainuse.bootmysqlcrud.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {
    @Autowired
    private RolRepository rolRepository;

    public List<RolDto> listar() {
        return this.rolRepository.listar();
    }

    public Integer registrar(RolDto param) {
        return this.rolRepository.registrar(param);
    }

    public Integer editar(RolDto param) {
        return this.rolRepository.editar(param);
    }

    public Integer eliminar(Integer idRol) {
        return this.rolRepository.eliminar(idRol);
    }
}
