package com.javainuse.bootmysqlcrud.service.Impl;

import com.javainuse.bootmysqlcrud.dto.LocalDto;
import com.javainuse.bootmysqlcrud.repository.LocalRepository;
import com.javainuse.bootmysqlcrud.service.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalServiceImpl implements LocalService {
    @Autowired
    private LocalRepository localRepository;

    public List<LocalDto> listar() {
        return this.localRepository.listar();
    }

    public Integer registrar(LocalDto param) {
        return this.localRepository.registrar(param);
    }

    public Integer editar(LocalDto param) {
        return this.localRepository.editar(param);
    }

    public Integer eliminar(Integer idLocal) {
        return this.localRepository.eliminar(idLocal);
    }
}
