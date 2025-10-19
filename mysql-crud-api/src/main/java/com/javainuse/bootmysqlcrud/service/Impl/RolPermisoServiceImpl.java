package com.javainuse.bootmysqlcrud.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javainuse.bootmysqlcrud.dto.PermisoDto;
import com.javainuse.bootmysqlcrud.repository.PermisoRepository;
import com.javainuse.bootmysqlcrud.service.RolPermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolPermisoServiceImpl implements RolPermisoService {
    @Autowired
    private PermisoRepository permisoRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<PermisoDto> obtener(Integer idRol) {
        return this.permisoRepository.obtener(idRol);
    }

    public Integer actualizar(List<PermisoDto> detalle) {
        Integer resultado = Integer.valueOf(0);
        for (PermisoDto permiso : detalle)
            resultado = this.permisoRepository.actualizar(permiso.getActivo(), permiso.getIdPermisos());
        return resultado;
    }
}
